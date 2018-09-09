package com.joh.mms.service;

import com.joh.mms.model.ExpenseCategory;

public interface ExpenseCategoryService {

	ExpenseCategory update(ExpenseCategory expenseCategory);

	ExpenseCategory findOne(int id);

	ExpenseCategory save(ExpenseCategory expenseCategory);

	Iterable<ExpenseCategory> findAll();

	void delete(int id);

}
