package com.joh.mms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.mms.dao.IncomeCategoryDAO;
import com.joh.mms.dao.IncomeDAO;
import com.joh.mms.exception.CusDataIntegrityViolationException;
import com.joh.mms.model.Income;
import com.joh.mms.model.IncomeCategory;

@Service
public class IncomeCategoryServiceImpl implements IncomeCategoryService {

	@Autowired
	private IncomeCategoryDAO incomeCategoryDAO;

	@Override
	public Iterable<IncomeCategory> findAll() {
		return incomeCategoryDAO.findAll();
	}

	@Override
	public IncomeCategory save(IncomeCategory incomeCategory) {
		return incomeCategoryDAO.save(incomeCategory);
	}

	@Override
	public IncomeCategory findOne(int id) {
		return incomeCategoryDAO.findOne(id);
	}

	@Override
	public IncomeCategory update(IncomeCategory incomeCategory) {
		if (incomeCategoryDAO.findOne(incomeCategory.getId()) == null) {
			throw new CusDataIntegrityViolationException("Could not find it");
		}
		return incomeCategoryDAO.save(incomeCategory);
	}

	@Override
	public void delete(int id) {
		incomeCategoryDAO.delete(id);
	}
}
