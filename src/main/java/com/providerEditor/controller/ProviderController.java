package com.providerEditor.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.providerEditor.entity.Provider;
import com.providerEditor.service.ProviderService;

@Controller
public class ProviderController {

	private ProviderService nameService;
	
	public ProviderController(ProviderService nameService) {
		this.nameService = nameService;
	}
	
	@GetMapping("/showFormForAddCode")
	public String showFormForAddCode(Model model) {
		Provider provider = new Provider();
		model.addAttribute("provider", provider);
		return "provider-form";
	}
	
	@PostMapping("/saveCode")
	public String save(@Valid @ModelAttribute("provider") Provider provider,
						BindingResult result, RedirectAttributes redirAttr) {
		if (result.hasErrors()) {
			return "provider-form";
		}
		redirAttr.addFlashAttribute("providerAdded", provider);
		nameService.save(provider);
		
		return "redirect:/";
	}
	
}
