package com.joh.mms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.mms.dao.ExpenseCategoryDAO;
import com.joh.mms.dao.IncomeCategoryDAO;
import com.joh.mms.dao.IncomeDAO;
import com.joh.mms.exception.CusDataIntegrityViolationException;
import com.joh.mms.model.ExpenseCategory;
import com.joh.mms.model.Income;
import com.joh.mms.model.IncomeCategory;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

	@Autowired
	private ExpenseCategoryDAO expenseCategoryDAO;

	@Override
	public Iterable<ExpenseCategory> findAll() {
		return expenseCategoryDAO.findAll();
	}

	@Override
	public ExpenseCategory save(ExpenseCategory expenseCategory) {
		return expenseCategoryDAO.save(expenseCategory);
	}

	@Override
	public ExpenseCategory findOne(int id) {
		return expenseCategoryDAO.findOne(id);
	}

	@Override
	public ExpenseCategory update(ExpenseCategory expenseCategory) {
		if (expenseCategoryDAO.findOne(expenseCategory.getId()) == null) {
			throw new CusDataIntegrityViolationException("Could not find it");
		}
		return expenseCategoryDAO.save(expenseCategory);
	}

	@Override
	public void delete(int id) {
		expenseCategoryDAO.delete(id);
	}
}
