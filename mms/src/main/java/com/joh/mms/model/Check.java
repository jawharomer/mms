package com.joh.mms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import com.joh.mms.validator.ExpenseValidator;
import com.joh.mms.validator.IncomeValidator;

@Entity
@Table(name = "CHECKS")
public class Check {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_CHECK")
	private Integer id;

	@Column(name = "CHECK_AMOUNT", nullable = false)
	private Double amount;

	@Column(name = "NOTE", length = 500)
	private String note;

	@Column(name = "CHECK_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp()
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Check [id=" + id + ", amount=" + amount + ", note=" + note + ", time=" + time + "]";
	}

}
