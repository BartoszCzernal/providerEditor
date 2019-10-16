package com.providerEditor.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.providerEditor.entity.Date;

public interface DateRepository extends JpaRepository<Date, Integer> {

	List<Date> findByDayOrderByTime(String day);
}
