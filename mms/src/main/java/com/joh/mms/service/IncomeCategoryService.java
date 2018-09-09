package com.joh.mms.service;

import com.joh.mms.model.IncomeCategory;

public interface IncomeCategoryService {

	Iterable<IncomeCategory> findAll();

	IncomeCategory save(IncomeCategory incomeCategory);

	IncomeCategory findOne(int id);

	IncomeCategory update(IncomeCategory incomeCategory);

	void delete(int id);

}
