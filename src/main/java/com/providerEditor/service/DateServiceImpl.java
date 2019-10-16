package com.providerEditor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.providerEditor.dao.DateRepository;
import com.providerEditor.entity.Date;
import com.providerEditor.entity.Provider;

@Service
public class DateServiceImpl implements DateService {

	private DateRepository dateRepository;
	
	public DateServiceImpl(DateRepository dateRepository) {
		this.dateRepository = dateRepository;
	}
	
	@Override
	public List<Date> findAll() {
		return dateRepository.findAll();
	}

	@Override
	public Date findById(int id) {
		Optional<Date> result = dateRepository.findById(id);
		
		Date date = null;
		
		if (result.isPresent()) {
			date = result.get();
		} else {
			throw new RuntimeException("Did not find date id - " + id);
		}
		return date;
	}

	@Override
	public void save(Date date) {
		dateRepository.save(date);
	}

	@Override
	public void deleteById(int id) {
		dateRepository.deleteById(id);
	}

	@Override
	public List<Date> findByDay(String day) {
		return dateRepository.findByDayOrderByTime(day);
	}

	private int findMaxColumns(List<Date> dates) { 
		int max = 0;
		for (Date date : dates) {
			if (date.getProviders() == null) {
				continue;
			}
			if (max < date.getProviders().size()) {
				max = date.getProviders().size();
			}
		}
		return max;
	}

	@Override
	public List<String> getHeaders(List<Date> dates) {
		List<String> headers = new ArrayList<>();
		int max = findMaxColumns(dates);
		for (int i = 1; i <= max; i++) { 
			headers.add(i + "");
		}
		return headers;
	}

	@Override
	public Date trimProviders(Date date) {
		List<Provider> providersToDelete = new ArrayList<>();
		for (Provider provider : date.getProviders()) {
				if (provider.getCode() == null || provider.getCode().trim().isEmpty()) {
					providersToDelete.add(provider);
					// I cannot delete empty providers here because it would throw ConcurrentModificationException
					// That's why i save Providers that i want to delete later
				}
		}
		
		for(Provider provider : providersToDelete) {
			date.removeProvider(provider);
		}
		
		return date;
	}

	@Override
	public Date prepareDateForForm(Date date, int maxProviders) {
		for (int i = 0; i < maxProviders; i++) {
			date.addProvider(new Provider());
		}
		return date;
	}

	@Override
	public List<Provider> compareChanges(Date dateDb, @Valid Date date) {
		List<Provider> providers = new ArrayList<>(date.getProviders());
		providers.removeAll(dateDb.getProviders());

		return providers;
	}

}
