package com.joh.mms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.mms.dao.ExpenseDAO;
import com.joh.mms.dao.IncomeDAO;
import com.joh.mms.model.Expense;
import com.joh.mms.model.Income;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseDAO expenseDAO;

	@Override
	public List<Expense> findAllByTimeBetween(Date from, Date to) {
		return expenseDAO.findAllByTimeBetween(from, to);
	}

	@Override
	public Expense save(Expense expense) {
		return expenseDAO.save(expense);
	}

	@Override
	public void delete(int id) {
		expenseDAO.delete(id);
	}

	@Override
	public Expense findOne(int id) {
		return expenseDAO.findOne(id);
	}
}
