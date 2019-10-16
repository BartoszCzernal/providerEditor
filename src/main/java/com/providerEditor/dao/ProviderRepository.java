package com.providerEditor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.providerEditor.entity.Provider;

public interface ProviderRepository extends JpaRepository<Provider, String> {

}
