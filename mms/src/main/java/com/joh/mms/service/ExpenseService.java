package com.joh.mms.service;

import java.util.Date;
import java.util.List;

import com.joh.mms.model.Expense;

public interface ExpenseService {

	void delete(int id);

	List<Expense> findAllByTimeBetween(Date from, Date to);

	Expense save(Expense expense);

	Expense findOne(int id);

}
