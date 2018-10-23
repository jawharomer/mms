package com.joh.mms.service;

import com.joh.mms.model.Check;

public interface CheckService {

	Iterable<Check> findAll();

	Check save(Check check);

	void delete(int id);

}
