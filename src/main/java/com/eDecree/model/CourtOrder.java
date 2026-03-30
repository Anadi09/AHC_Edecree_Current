package com.eDecree.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "court_orders")
public class CourtOrder {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="co_seq")
	@SequenceGenerator(name="co_seq", sequenceName="co_seq", allocationSize=1)
	@Column(name = "co_id")
	private Long co_id;
	
	
	@Column(name = "co_document_name")
	private String co_document_name;
	
	@Column(name = "co_cr_date")
	private Date co_cr_date;
	
	@Column(name = "co_cr_by")
	private Long co_cr_by;

	@Column(name = "co_court_no")
	private Integer co_court_no;

	public Long getCo_id() {
		return co_id;
	}

	public void setCo_id(Long co_id) {
		this.co_id = co_id;
	}

	public String getCo_document_name() {
		return co_document_name;
	}

	public void setCo_document_name(String co_document_name) {
		this.co_document_name = co_document_name;
	}

	public Date getCo_cr_date() {
		return co_cr_date;
	}

	public void setCo_cr_date(Date co_cr_date) {
		this.co_cr_date = co_cr_date;
	}

	public Long getCo_cr_by() {
		return co_cr_by;
	}

	public void setCo_cr_by(Long co_cr_by) {
		this.co_cr_by = co_cr_by;
	}

	public Integer getCo_court_no() {
		return co_court_no;
	}

	public void setCo_court_no(Integer co_court_no) {
		this.co_court_no = co_court_no;
	}
	
}
