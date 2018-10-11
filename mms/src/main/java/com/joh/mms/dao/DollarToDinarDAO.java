package com.joh.mms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.mms.model.DollarToDinar;

public interface DollarToDinarDAO extends CrudRepository<DollarToDinar, Integer> {

	List<DollarToDinar> findTop10ByOrderByIdDesc();

	DollarToDinar findTop1ByOrderByIdDesc();

}
