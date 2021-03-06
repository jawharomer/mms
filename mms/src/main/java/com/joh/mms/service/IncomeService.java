package com.joh.mms.service;

import java.util.Date;
import java.util.List;

import com.joh.mms.domain.model.RevenueD;
import com.joh.mms.model.Income;

public interface IncomeService {

	Income save(Income income);

	List<Income> findAllByTimeBetween(Date from, Date to);

	void delete(int id);

	Income findOne(int id);

	RevenueD getCurrentRevenue();

	List<RevenueD> findAllRevenue(Date from, Date to);

}
