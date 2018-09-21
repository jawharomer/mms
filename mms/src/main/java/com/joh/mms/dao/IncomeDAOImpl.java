package com.joh.mms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.joh.mms.domain.model.RevenueD;

public class IncomeDAOImpl implements IncomeDAOExt {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<RevenueD> findAllRevenue() {
		List<RevenueD> revenueDs = new ArrayList<>();
		Query query = em.createNativeQuery(
				"SELECT JDATE,IFNULL(SUM(EXPENSE_AMOUNT),0) EXPENSE ,IFNULL(SUM(INCOME_AMOUNT),0)  INCOME\n" + "FROM \n"
						+ "(\n" + "SELECT EXPENSE_AMOUNT,DATE(EXPENSE_TIME) JDATE  FROM EXPENSES) E\n"
						+ "INNER JOIN (\n"
						+ "SELECT INCOME_AMOUNT,DATE(INCOME_TIME) JDATE  FROM INCOMES ) I USING (JDATE)\n"
						+ "GROUP BY JDATE\n" + "ORDER BY JDATE DESC");

		List<Object[]> resultList = query.getResultList();

		for (Object columns[] : resultList) {

			RevenueD revenueD = new RevenueD();

			revenueD.setDate((Date) columns[0]);
			revenueD.setTotalExpense(Double.parseDouble("" + columns[1]));
			revenueD.setTotalIncome(Double.parseDouble("" + columns[2]));

			revenueDs.add(revenueD);
		}
		return revenueDs;
	}

	@Override
	public RevenueD getCurrentRevenue() {

		Query query = em.createNativeQuery("SELECT IFNULL(SUM(INCOME_AMOUNT),0),IFNULL(SUM(EXPENSE_AMOUNT),0)\n"
				+ "FROM INCOMES\n" + "JOIN EXPENSES");

		Object[] columns = (Object[]) query.getSingleResult();

		RevenueD revenueD = new RevenueD();
		revenueD.setTotalIncome(Double.parseDouble("" + columns[0]));
		revenueD.setTotalExpense(Double.parseDouble("" + columns[1]));

		return revenueD;
	}

}
