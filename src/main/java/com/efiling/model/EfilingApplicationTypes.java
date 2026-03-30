package com.efiling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_types")
public class EfilingApplicationTypes 
{
	@Id
	@Column(name="at_id")
	private Integer at_id;
	
	@Column(name="at_type")
	private String at_type;
	
	@Column(name="at_name")
	private String at_name;
	
	@Column(name="at_description")
	private String at_description;
	
	@Column(name="at_parent")
	private Long at_parent;
	
	@Column(name="at_rec_status")
	private Integer at_rec_status;
	
	@Column(name="at_sequence")
	private Integer at_sequence;

	

	public Integer getAt_id() {
		return at_id;
	}

	public void setAt_id(Integer at_id) {
		this.at_id = at_id;
	}

	public String getAt_type() {
		return at_type;
	}

	public void setAt_type(String at_type) {
		this.at_type = at_type;
	}

	public String getAt_name() {
		return at_name;
	}

	public void setAt_name(String at_name) {
		this.at_name = at_name;
	}

	public String getAt_description() {
		return at_description;
	}

	public void setAt_description(String at_description) {
		this.at_description = at_description;
	}

	public Long getAt_parent() {
		return at_parent;
	}

	public void setAt_parent(Long at_parent) {
		this.at_parent = at_parent;
	}

	public Integer getAt_rec_status() {
		return at_rec_status;
	}

	public void setAt_rec_status(Integer at_rec_status) {
		this.at_rec_status = at_rec_status;
	}

	public Integer getAt_sequence() {
		return at_sequence;
	}

	public void setAt_sequence(Integer at_sequence) {
		this.at_sequence = at_sequence;
	}

	

}
