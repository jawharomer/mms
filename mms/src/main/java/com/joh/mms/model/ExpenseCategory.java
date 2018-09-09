package com.joh.mms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.joh.mms.validator.ExpenseValidator;
import com.joh.mms.validator.IncomeValidator;

@Entity
@Table(name = "EXPENSE_CATEGORIES")
public class ExpenseCategory {

	@NotNull(message = "Expense Category is null", groups = { ExpenseValidator.Insert.class })
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_EXPENSE_CATEGORY")
	private Integer id;

	@NotBlank(message = "name is blank")
	@Column(name = "CATEGORY_NAME", nullable = false, unique = true)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ExpenseCategory [id=" + id + ", name=" + name + "]";
	}

}
