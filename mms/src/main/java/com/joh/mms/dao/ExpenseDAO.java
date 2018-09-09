package com.joh.mms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.mms.model.Expense;
import com.joh.mms.model.Income;

public interface ExpenseDAO extends CrudRepository<Expense, Integer> {
	List<Expense> findAllByTimeBetween(Date from, Date to);
}
