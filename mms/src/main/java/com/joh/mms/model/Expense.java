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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joh.mms.validator.ExpenseValidator;
import com.joh.mms.validator.IncomeValidator;

@Entity()
@Table(name = "EXPENSES")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_EXPENSE")
	private Integer id;

	@NotNull(message = "Amount is null", groups = { IncomeValidator.Insert.class })
	@Column(name = "EXPENSE_AMOUNT", nullable = false)
	private Double amount;

	@Column(name = "EXPENSE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ColumnDefault("CURRENT_TIMESTAMP")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "ar-IQ",timezone = "Asia/Baghdad")
	private Date time;

	@Column(name = "RECEIVED_BY")
	private String receivedBy;

	@Column(name = "REFERENCE")
	private String reference;

	@NotNull(message = "Expense Category is null", groups = { ExpenseValidator.Insert.class })
	@Valid()
	@ManyToOne()
	@JoinColumn(name = "I_EXPENSE_CATEGORY", nullable = false)
	private ExpenseCategory expenseCategory;

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

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public ExpenseCategory getExpenseCategory() {
		return expenseCategory;
	}

	public void setExpenseCategory(ExpenseCategory expenseCategory) {
		this.expenseCategory = expenseCategory;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", amount=" + amount + ", time=" + time + ", receivedBy=" + receivedBy
				+ ", reference=" + reference + ", expenseCategory=" + expenseCategory + ", note=" + note + "]";
	}

}
