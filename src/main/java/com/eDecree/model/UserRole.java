package com.eDecree.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE")
public class UserRole {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="user_role_seq")
	@SequenceGenerator(name="user_role_seq", sequenceName="user_role_seq", allocationSize=1)
	@Column(name = "ur_id")
	private Long ur_id;
	
	@Column(name = "ur_role_id")
	private Long ur_role_id;
	
	@Column(name = "ur_um_mid")
	private Long ur_um_mid;
	
	@Column(name = "ur_cr_by")
	private Long ur_cr_by;

	@Column(name = "ur_cr_date")
	private Date ur_cr_date;
	
	@Column(name = "ur_mod_by")
	private Long ur_mod_by;
	
	@Column(name = "ur_mod_date")
	private Date ur_mod_date;
	
	@Column(name = "ur_rec_status")
	private Integer ur_rec_status;

	@ManyToOne
    @JoinColumn(name="ur_role_id",referencedColumnName="lk_id",insertable = false, updatable = false)
    private Lookup lk;	
	
//	@OneToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "ur_um_mid",insertable = false, updatable = false)
//	private User user;
	
	public Long getUr_id() {
		return ur_id;
	}

	public void setUr_id(Long ur_id) {
		this.ur_id = ur_id;
	}

	public Long getUr_role_id() {
		return ur_role_id;
	}

	public void setUr_role_id(Long ur_role_id) {
		this.ur_role_id = ur_role_id;
	}

	public Long getUr_um_mid() {
		return ur_um_mid;
	}

	public void setUr_um_mid(Long ur_um_mid) {
		this.ur_um_mid = ur_um_mid;
	}

	public Long getUr_cr_by() {
		return ur_cr_by;
	}

	public void setUr_cr_by(Long ur_cr_by) {
		this.ur_cr_by = ur_cr_by;
	}

	public Date getUr_cr_date() {
		return ur_cr_date;
	}

	public void setUr_cr_date(Date ur_cr_date) {
		this.ur_cr_date = ur_cr_date;
	}

	public Long getUr_mod_by() {
		return ur_mod_by;
	}

	public void setUr_mod_by(Long ur_mod_by) {
		this.ur_mod_by = ur_mod_by;
	}

	public Date getUr_mod_date() {
		return ur_mod_date;
	}

	public void setUr_mod_date(Date ur_mod_date) {
		this.ur_mod_date = ur_mod_date;
	}

	public Integer getUr_rec_status() {
		return ur_rec_status;
	}

	public void setUr_rec_status(Integer ur_rec_status) {
		this.ur_rec_status = ur_rec_status;
	}

	public Lookup getLk() {
		return lk;
	}

	public void setLk(Lookup lk) {
		this.lk = lk;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	
	
	
}
