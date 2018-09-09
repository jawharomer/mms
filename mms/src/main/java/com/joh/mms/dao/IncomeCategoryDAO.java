package com.joh.mms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.mms.model.IncomeCategory;

public interface IncomeCategoryDAO extends CrudRepository<IncomeCategory, Integer> {
}
