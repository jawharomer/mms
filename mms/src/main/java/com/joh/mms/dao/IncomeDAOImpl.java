package com.joh.mms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.joh.mms.domain.model.RevenueD;

public class IncomeDAOImpl implements IncomeDAOExt {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<RevenueD> findAllRevenue(Date from, Date to) {
		List<RevenueD> revenueDs = new ArrayList<>();
		Query query = em.createNativeQuery("SELECT JDATE,IFNULL(EXPENSE,0) EXPENSE ,IFNULL(INCOME,0) INCOME FROM\n"
				+ "(\n" + "SELECT JDATE FROM (\n"
				+ "SELECT DATE(EXPENSE_TIME) JDATE FROM EXPENSES WHERE EXPENSE_TIME BETWEEN :from AND :to\n"
				+ "UNION \n"
				+ "SELECT DATE(INCOME_TIME) JDATE FROM INCOMES WHERE INCOME_TIME BETWEEN :from AND :to )DT) DDT\n"
				+ "LEFT OUTER JOIN (\n" + "SELECT IFNULL(SUM(EXPENSE_AMOUNT),0) EXPENSE,DATE(EXPENSE_TIME) JDATE  \n"
				+ "FROM EXPENSES \n" + "WHERE EXPENSE_TIME BETWEEN :from AND :to\n"
				+ "GROUP BY JDATE ) E  USING (JDATE) LEFT OUTER JOIN (\n"
				+ "SELECT IFNULL(SUM(INCOME_AMOUNT),0) INCOME,DATE(INCOME_TIME) JDATE \n" + "FROM INCOMES \n"
				+ "WHERE INCOME_TIME BETWEEN :from AND :to\n" + "GROUP BY JDATE ) I USING (JDATE) \n"
				+ "ORDER BY JDATE DESC;");

		query.setParameter("from", from, TemporalType.DATE);
		query.setParameter("to", to, TemporalType.DATE);
		// query.setParameter(3, from, TemporalType.DATE);
		// query.setParameter(4, to, TemporalType.DATE);

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

		Query query = em.createNativeQuery("SELECT (SELECT IFNULL(SUM(INCOME_AMOUNT),0) FROM INCOMES) INCOME\n"
				+ ",(SELECT IFNULL(SUM(EXPENSE_AMOUNT),0) FROM EXPENSES) EXPENSE");

		Object[] columns = (Object[]) query.getSingleResult();

		RevenueD revenueD = new RevenueD();
		revenueD.setTotalIncome(Double.parseDouble("" + columns[0]));
		revenueD.setTotalExpense(Double.parseDouble("" + columns[1]));

		return revenueD;
	}

}
