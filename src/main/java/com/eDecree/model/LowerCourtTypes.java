package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="lower_court_types")
public class LowerCourtTypes {
	  
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="lctypes_seq")
	@SequenceGenerator(name="lctypes_seq", sequenceName="lctypes_seq", allocationSize=1)
	
	@Column(name="lct_id")
	private Integer lct_id;
	
	
	@Column(name="lct_name")
	private String lct_name;


	public Integer getLct_id() {
		return lct_id;
	}


	public void setLct_id(Integer lct_id) {
		this.lct_id = lct_id;
	}


	public String getLct_name() {
		return lct_name;
	}


	public void setLct_name(String lct_name) {
		this.lct_name = lct_name;
	}
	
}
