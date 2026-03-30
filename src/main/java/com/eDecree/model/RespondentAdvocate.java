package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="respondent_advocates")
public class RespondentAdvocate {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="resadv_seq")
	@SequenceGenerator(name="resadv_seq", sequenceName="resadv_seq", allocationSize=1)
	@Column(name = "ra_id")
	private Long ra_id;
		
	@Column(name = "ra_name")
	private String ra_name;
	
	@Column(name = "ra_cl_mid")
	private Long ra_cl_mid;

	public Long getRa_id() {
		return ra_id;
	}

	public void setRa_id(Long ra_id) {
		this.ra_id = ra_id;
	}

	public String getRa_name() {
		return ra_name;
	}

	public void setRa_name(String ra_name) {
		this.ra_name = ra_name;
	}

	public Long getRa_cl_mid() {
		return ra_cl_mid;
	}

	public void setRa_cl_mid(Long ra_cl_mid) {
		this.ra_cl_mid = ra_cl_mid;
	}
	
	
}
