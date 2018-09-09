package com.joh.mms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.joh.mms.validator.IncomeValidator;

@Entity()
@Table(name = "INCOMES")
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="I_INCOME")
	private Integer id;

	@NotNull(message = "Amount is null", groups = { IncomeValidator.Insert.class })
	@Column(name = "INCOME_AMOUNT", nullable = false)
	private Double amount;

	@Column(name = "INCOME_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp()
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date time;

	@Column(name = "RECEIVED_FROM")
	private String receivedFrom;

	@Column(name = "REFERENCE")
	private String reference;

	
	@NotNull(message="Income Category is null",groups = { IncomeValidator.Insert.class })
    @Valid()
	@ManyToOne()
	@JoinColumn(name = "I_INCOME_CATEGORY", nullable = false)
	private IncomeCategory incomeCategory;

	@Column(name = "NOTE")
	private String note;

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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public IncomeCategory getIncomeCategory() {
		return incomeCategory;
	}

	public void setIncomeCategory(IncomeCategory incomeCategory) {
		this.incomeCategory = incomeCategory;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Income [id=" + id + ", amount=" + amount + ", time=" + time + ", receivedFrom=" + receivedFrom
				+ ", reference=" + reference + ", incomeCategory=" + incomeCategory + ", note=" + note + "]";
	}

}
