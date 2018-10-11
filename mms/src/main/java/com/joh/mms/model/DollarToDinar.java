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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "DOLLAR_TO_DINARS")
public class DollarToDinar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_DOLAR_TO_DINAR")
	private Integer id;

	@Min(value = 0)
	@Max(value = 1)
	@Column(name = "DOLLAR")
	private Integer dollar;

	@Column(name = "DINAR")
	private Double dinar;

	@Column(name = "INSERT_TIME", nullable = false)
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

	public Integer getDollar() {
		return dollar;
	}

	public void setDollar(Integer dollar) {
		this.dollar = dollar;
	}

	public Double getDinar() {
		return dinar;
	}

	public void setDinar(Double dinar) {
		this.dinar = dinar;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "DollarToDinar [id=" + id + ", dollar=" + dollar + ", dinar=" + dinar + ", time=" + time + "]";
	}

}
