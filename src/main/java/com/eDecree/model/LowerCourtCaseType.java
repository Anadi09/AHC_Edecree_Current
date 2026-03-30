package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="lower_court_case_types")
public class LowerCourtCaseType {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="lccasetypes_seq")
	@SequenceGenerator(name="lccasetypes_seq", sequenceName="lccasetypes_seq", allocationSize=1)
	
	@Column(name="ct_id")
	private Long ct_id;
	
	@Column(name="ct_name")
	private String ct_name;

	public Long getCt_id() {
		return ct_id;
	}

	public void setCt_id(Long ct_id) {
		this.ct_id = ct_id;
	}

	public String getCt_name() {
		return ct_name;
	}

	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}
	
	
}

