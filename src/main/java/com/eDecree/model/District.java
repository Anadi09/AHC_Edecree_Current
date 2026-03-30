package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="district")
public class District {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_seq")
	@SequenceGenerator(name = "district_seq", sequenceName = "district_seq", allocationSize = 1)
	@Column(name = "dt_id")
	private Long dt_id;
	
	@Column(name="dt_name")
	private String dt_name;

	public Long getDt_id() {
		return dt_id;
	}

	public void setDt_id(Long dt_id) {
		this.dt_id = dt_id;
	}

	public String getDt_name() {
		return dt_name;
	}

	public void setDt_name(String dt_name) {
		this.dt_name = dt_name;
	}
	
	
	
	
	
	

}
