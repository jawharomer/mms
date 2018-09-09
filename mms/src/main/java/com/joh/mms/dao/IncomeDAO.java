package com.joh.mms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.mms.model.Income;

public interface IncomeDAO extends CrudRepository<Income, Integer> {
	List<Income> findAllByTimeBetween(Date from, Date to);
}
