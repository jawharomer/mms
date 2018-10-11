package com.joh.mms.dao;

import java.util.Date;
import java.util.List;

import com.joh.mms.domain.model.RevenueD;

public interface IncomeDAOExt {

	RevenueD getCurrentRevenue();

	List<RevenueD> findAllRevenue(Date from, Date to);
}
