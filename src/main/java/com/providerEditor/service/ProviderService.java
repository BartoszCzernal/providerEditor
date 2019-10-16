package com.providerEditor.service;

import java.util.List;

import com.providerEditor.entity.Provider;

public interface ProviderService {

	public List<Provider> findAll();
	public Provider findByCode(String Code);
	public void save(Provider provider);
	public void deleteByCode(String provider);
	
}
