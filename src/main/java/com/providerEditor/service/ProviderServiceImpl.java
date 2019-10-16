package com.providerEditor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.providerEditor.dao.ProviderRepository;
import com.providerEditor.entity.Provider;

@Service
public class ProviderServiceImpl implements ProviderService {
	
	private ProviderRepository providerRepository;
	
	public ProviderServiceImpl(ProviderRepository providerRepository) {
		this.providerRepository = providerRepository;
	}

	@Override
	public List<Provider> findAll() {
		return providerRepository.findAll();
	}

	@Override
	public Provider findByCode(String code) {
		Optional<Provider> result = providerRepository.findById(code);
		
		Provider provider = null;
		
		if (result.isPresent()) {
			provider = result.get();
		} else {
			throw new RuntimeException("Did not find name code - " + code);
		}
		return provider;
	}

	@Override
	public void save(Provider provider) {
		providerRepository.save(provider);
	}

	@Override
	public void deleteByCode(String code) {
		providerRepository.deleteById(code);
	}

}
