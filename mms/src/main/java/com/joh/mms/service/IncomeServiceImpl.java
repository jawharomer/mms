package com.joh.mms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.mms.dao.IncomeDAO;
import com.joh.mms.model.Income;

@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeDAO incomeDAO;

	@Override
	public List<Income> findAllByTimeBetween(Date from, Date to) {
		return incomeDAO.findAllByTimeBetween(from, to);
	}

	@Override
	public Income save(Income income) {
		return incomeDAO.save(income);
	}
	
	
	@Override
	public Income findOne(int id) {
		return incomeDAO.findOne(id);
	}

	@Override
	public void delete(int id) {
		incomeDAO.delete(id);
	}
}
