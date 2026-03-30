package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "petitioner_advocates")
public class PetitionerAdvocate {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="petadv_seq")
	@SequenceGenerator(name="petadv_seq", sequenceName="petadv_seq", allocationSize=1)
	@Column(name = "pa_id")
	private Long pa_id;
		
	@Column(name = "pa_name")
	private String pa_name;
	
	@Column(name = "pa_cl_mid")
	private Long pa_cl_mid;

	public Long getPa_id() {
		return pa_id;
	}

	public void setPa_id(Long pa_id) {
		this.pa_id = pa_id;
	}

	public String getPa_name() {
		return pa_name;
	}

	public void setPa_name(String pa_name) {
		this.pa_name = pa_name;
	}

	public Long getPa_cl_mid() {
		return pa_cl_mid;
	}

	public void setPa_cl_mid(Long pa_cl_mid) {
		this.pa_cl_mid = pa_cl_mid;
	}
	
	
}
