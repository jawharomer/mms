package com.joh.mms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.mms.model.Check;

public interface CheckDAO extends CrudRepository<Check, Integer> {
}
