package com.joh.mms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.mms.dao.CheckDAO;
import com.joh.mms.dao.ExpenseCategoryDAO;
import com.joh.mms.dao.IncomeCategoryDAO;
import com.joh.mms.dao.IncomeDAO;
import com.joh.mms.exception.CusDataIntegrityViolationException;
import com.joh.mms.model.Check;
import com.joh.mms.model.ExpenseCategory;
import com.joh.mms.model.Income;
import com.joh.mms.model.IncomeCategory;

@Service
public class CheckServiceImpl implements CheckService {

	@Autowired
	private CheckDAO checkDAO;

	@Override
	public Iterable<Check> findAll() {
		return checkDAO.findAll();
	}

	@Override
	public Check save(Check check) {
		return checkDAO.save(check);
	}
}
