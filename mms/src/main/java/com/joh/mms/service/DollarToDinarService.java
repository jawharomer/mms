package com.joh.mms.service;

import java.util.List;

import com.joh.mms.model.DollarToDinar;

public interface DollarToDinarService {

	DollarToDinar save(DollarToDinar dollarToDinar);
	List<DollarToDinar> findTop10();
	DollarToDinar findCurrentDollarToDinar();

}
