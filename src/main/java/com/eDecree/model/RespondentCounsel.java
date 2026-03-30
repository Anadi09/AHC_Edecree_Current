package com.eDecree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "respondent_counsel")
public class RespondentCounsel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rc_seq")
	@SequenceGenerator(name = "rc_seq", sequenceName = "rc_seq", allocationSize = 1)
	@Column(name = "rc_id")
	private Long rc_id;

	@Column(name = "rc_name")
	private String rc_name;

	@Column(name = "rc_rec_status")
	private Integer rc_rec_status;

	@Column(name = "rc_fd_mid")
	private Long rc_fd_mid;

	@Column(name = "rc_sequence")
	private Integer rc_sequence;

	@Column(name="rc_cr_by")
	private Long rc_cr_by;
	
	@Column(name="rc_cr_date")
	private Date rc_cr_date;	
	
	@Column(name="rc_mod_by")
	private Long rc_mod_by;	
	
	@Column(name="rc_mod_date")
	private Date rc_mod_date;
	
	
	public Long getRc_id() {
		return rc_id;
	}

	public void setRc_id(Long rc_id) {
		this.rc_id = rc_id;
	}

	public String getRc_name() {
		return rc_name;
	}

	public void setRc_name(String rc_name) {
		this.rc_name = rc_name;
	}

	public Integer getRc_rec_status() {
		return rc_rec_status;
	}

	public void setRc_rec_status(Integer rc_rec_status) {
		this.rc_rec_status = rc_rec_status;
	}

	public Long getRc_fd_mid() {
		return rc_fd_mid;
	}

	public void setRc_fd_mid(Long rc_fd_mid) {
		this.rc_fd_mid = rc_fd_mid;
	}

	public Integer getRc_sequence() {
		return rc_sequence;
	}

	public void setRc_sequence(Integer rc_sequence) {
		this.rc_sequence = rc_sequence;
	}

	public Long getRc_cr_by() {
		return rc_cr_by;
	}

	public void setRc_cr_by(Long rc_cr_by) {
		this.rc_cr_by = rc_cr_by;
	}

	public Date getRc_cr_date() {
		return rc_cr_date;
	}

	public void setRc_cr_date(Date rc_cr_date) {
		this.rc_cr_date = rc_cr_date;
	}

	public Long getRc_mod_by() {
		return rc_mod_by;
	}

	public void setRc_mod_by(Long rc_mod_by) {
		this.rc_mod_by = rc_mod_by;
	}

	public Date getRc_mod_date() {
		return rc_mod_date;
	}

	public void setRc_mod_date(Date rc_mod_date) {
		this.rc_mod_date = rc_mod_date;
	}

	
	
	
}
