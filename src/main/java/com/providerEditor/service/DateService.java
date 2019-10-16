package com.providerEditor.service;

import java.util.List;

import javax.validation.Valid;

import com.providerEditor.entity.Date;
import com.providerEditor.entity.Provider;

public interface DateService {

	public List<Date> findAll();
	public Date findById(int id);
	public void save(Date date);
	public void deleteById(int id);
	public List<Date> findByDay(String day);
	public List<String> getHeaders(List<Date> dates);
	public Date trimProviders(Date date);
	public Date prepareDateForForm(Date date, int maxProviders);
	public List<Provider> compareChanges(Date dateDb, @Valid Date date);
}
