package com.joh.mms.exception;

public class ItemExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ItemExistsException(String messgae) {
		super(messgae);
	}
}
