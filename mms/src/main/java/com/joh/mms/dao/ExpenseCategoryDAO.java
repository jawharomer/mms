package com.joh.mms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.mms.model.ExpenseCategory;

public interface ExpenseCategoryDAO extends CrudRepository<ExpenseCategory, Integer> {
}
