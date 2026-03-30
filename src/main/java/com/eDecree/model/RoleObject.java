package com.eDecree.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "role_object")
public class RoleObject {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="role_object_seq")
	@SequenceGenerator(name="role_object_seq", sequenceName="role_object_seq", allocationSize=1)
	@Column(name = "ro_id")
	private Long ro_id;
	
	@Column(name = "ro_role_id")
	private Long ro_role_id;
	
	@Column(name = "ro_om_mid")
	private Long ro_om_mid;
	
	@Column(name = "ro_cr_by")
	private Long ro_cr_by;

	@Column(name = "ro_cr_date")
	private Date ro_cr_date;
	
	@Column(name = "ro_mod_by")
	private Long ro_mod_by;
	
	@Column(name = "ro_mod_date")
	private Date ro_mod_date;
	
	@Column(name = "ro_rec_status")
	private Integer ro_rec_status;

	@Transient
	private List<RoleObject> cfdList;
	
	@Transient
	private Boolean checkbox;
	
	public Long getRo_id() {
		return ro_id;
	}

	public void setRo_id(Long ro_id) {
		this.ro_id = ro_id;
	}

	public Long getRo_role_id() {
		return ro_role_id;
	}

	public void setRo_role_id(Long ro_role_id) {
		this.ro_role_id = ro_role_id;
	}

	public Long getRo_om_mid() {
		return ro_om_mid;
	}

	public void setRo_om_mid(Long ro_om_mid) {
		this.ro_om_mid = ro_om_mid;
	}

	public Long getRo_cr_by() {
		return ro_cr_by;
	}

	public void setRo_cr_by(Long ro_cr_by) {
		this.ro_cr_by = ro_cr_by;
	}

	public Date getRo_cr_date() {
		return ro_cr_date;
	}

	public void setRo_cr_date(Date ro_cr_date) {
		this.ro_cr_date = ro_cr_date;
	}

	public Long getRo_mod_by() {
		return ro_mod_by;
	}

	public void setRo_mod_by(Long ro_mod_by) {
		this.ro_mod_by = ro_mod_by;
	}

	public Date getRo_mod_date() {
		return ro_mod_date;
	}

	public void setRo_mod_date(Date ro_mod_date) {
		this.ro_mod_date = ro_mod_date;
	}

	public Integer getRo_rec_status() {
		return ro_rec_status;
	}

	public void setRo_rec_status(Integer ro_rec_status) {
		this.ro_rec_status = ro_rec_status;
	}

	public List<RoleObject> getCfdList() {
		return cfdList;
	}

	public void setCfdList(List<RoleObject> cfdList) {
		this.cfdList = cfdList;
	}

	public Boolean getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(Boolean checkbox) {
		this.checkbox = checkbox;
	}
				
}
