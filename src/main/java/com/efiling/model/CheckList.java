package com.efiling.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="checklist")
public class CheckList 
{
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="checklist_seq")
	@SequenceGenerator(name="checklist_seq", sequenceName="checklist_seq", allocationSize=1)
	@Column(name="c_id")
	private Long c_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="cr_by")
	private Long cr_by;

	@Column(name="cr_date")
	private Date cr_date;

	@Column(name="mod_by")
	private Long mod_by;

	@Column(name="mod_date")
	private Date mod_date;
	
	@Column(name="c_remark")
	private String c_remark;

	@Column(name="type")
	private String type;

	
	@Transient
	private Boolean checkbox=false;

	public Long getC_id() {
		return c_id;
	}

	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCr_by() {
		return cr_by;
	}

	public void setCr_by(Long cr_by) {
		this.cr_by = cr_by;
	}

	public Date getCr_date() {
		return cr_date;
	}

	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}

	public Long getMod_by() {
		return mod_by;
	}

	public void setMod_by(Long mod_by) {
		this.mod_by = mod_by;
	}

	public Date getMod_date() {
		return mod_date;
	}

	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}

	public Boolean getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(Boolean checkbox) {
		this.checkbox = checkbox;
	}

	public String getC_remark() {
		return c_remark;
	}

	public void setC_remark(String c_remark) {
		this.c_remark = c_remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
