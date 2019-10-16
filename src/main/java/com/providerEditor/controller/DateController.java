package com.providerEditor.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.providerEditor.entity.Date;
import com.providerEditor.entity.Provider;
import com.providerEditor.service.DateService;
import com.providerEditor.service.ProviderService;

@Controller
public class DateController {
	
	private DateService dateService;
	private ProviderService providerService;
	private final int maxProviders = 10;
	
	@Autowired
	public DateController(DateService dateService, ProviderService providerService) {
		this.dateService = dateService;
		this.providerService = providerService;
	}
	
	@GetMapping("/")
	public String start(Model model) {
		return "index";
	}
	
	@GetMapping("/list/{day}")
	public String listDates(@PathVariable("day") String day, Model model) {
		List<Date> dates = dateService.findByDay(day);
		List<String> headers = dateService.getHeaders(dates);
		
		model.addAttribute("headers", headers);
		model.addAttribute("dates", dates);
		return "list-dates";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("dateId") int id,
									Model model) {
		Date date = dateService.findById(id);
		date = dateService.prepareDateForForm(date, maxProviders - date.getProviders().size());
		List<Provider> allProviders = providerService.findAll();
		model.addAttribute("date", date);
		model.addAttribute("day", date.getDay());
		model.addAttribute("allProviders", allProviders);
		return "date-form";
	}
	
	@GetMapping("/showFormForAddTime/{day}")
	public String showFormForAddTime(@PathVariable("day") String day,Model model) {
		Date date = new Date(day);
		model.addAttribute("date", date);
		return "time-form";
	}
	
	@PostMapping("/saveTime")
	public String saveTime(@Valid @ModelAttribute("date") Date date, BindingResult result) {
		
		if (result.hasErrors()) {
			return "time-form";
		}
		dateService.save(date);
		return "redirect:/list/" + date.getDay();
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("date") Date date, BindingResult result,
						RedirectAttributes redirAttr) {
		date = dateService.trimProviders(date);
		if (result.hasErrors()) {
			return "date-form";
		}
		Date dateDb = dateService.findById(date.getId());
		List<Provider> providersChanged = dateService.compareChanges(dateDb, date);
		dateService.save(date);
		redirAttr.addFlashAttribute("providersChanged", providersChanged);
		redirAttr.addFlashAttribute("timeChanged", date.getTime());
	
		return "redirect:/list/" + date.getDay();
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("dateId") int id, @RequestParam("day") String day) {
		
		dateService.deleteById(id);
		
		return "redirect:/list/" + day;
	}
	
	
}
