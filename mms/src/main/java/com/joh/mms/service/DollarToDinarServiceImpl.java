package com.joh.mms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.mms.dao.DollarToDinarDAO;
import com.joh.mms.model.DollarToDinar;

@Service
public class DollarToDinarServiceImpl implements DollarToDinarService {

	@Autowired
	private DollarToDinarDAO dollarToDinarDAO;

	@Override
	public DollarToDinar save(DollarToDinar dollarToDinar) {
		return dollarToDinarDAO.save(dollarToDinar);
	}

	@Override
	public List<DollarToDinar> findTop10() {
		return dollarToDinarDAO.findTop10ByOrderByIdDesc();
	}
	
	@Override
	public DollarToDinar findCurrentDollarToDinar() {
		return dollarToDinarDAO.findTop1ByOrderByIdDesc();
	}
}
