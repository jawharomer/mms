package com.joh.mms.dao;

import java.util.List;

import com.joh.mms.domain.model.RevenueD;

public interface IncomeDAOExt {

	List<RevenueD> findAllRevenue();

	RevenueD getCurrentRevenue();
}
