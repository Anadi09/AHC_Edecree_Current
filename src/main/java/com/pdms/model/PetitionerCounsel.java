package com.pdms.model;

import java.util.Date;

public class PetitionerCounsel {

	private Long pc_id;

	private String pc_name;

	private Integer pc_rec_status;

	private Long pc_fd_mid;

	private Integer pc_sequence;

	private Long pc_cr_by;

	private Date pc_cr_date;

	private Long pc_mod_by;

	private Date pc_mod_date;

	public Long getPc_id() {
		return pc_id;
	}

	public void setPc_id(Long pc_id) {
		this.pc_id = pc_id;
	}

	public String getPc_name() {
		return pc_name;
	}

	public void setPc_name(String pc_name) {
		this.pc_name = pc_name;
	}

	public Integer getPc_rec_status() {
		return pc_rec_status;
	}

	public void setPc_rec_status(Integer pc_rec_status) {
		this.pc_rec_status = pc_rec_status;
	}

	public Long getPc_fd_mid() {
		return pc_fd_mid;
	}

	public void setPc_fd_mid(Long pc_fd_mid) {
		this.pc_fd_mid = pc_fd_mid;
	}

	public Integer getPc_sequence() {
		return pc_sequence;
	}

	public void setPc_sequence(Integer pc_sequence) {
		this.pc_sequence = pc_sequence;
	}

	public Long getPc_cr_by() {
		return pc_cr_by;
	}

	public void setPc_cr_by(Long pc_cr_by) {
		this.pc_cr_by = pc_cr_by;
	}

	public Date getPc_cr_date() {
		return pc_cr_date;
	}

	public void setPc_cr_date(Date pc_cr_date) {
		this.pc_cr_date = pc_cr_date;
	}

	public Long getPc_mod_by() {
		return pc_mod_by;
	}

	public void setPc_mod_by(Long pc_mod_by) {
		this.pc_mod_by = pc_mod_by;
	}

	public Date getPc_mod_date() {
		return pc_mod_date;
	}

	public void setPc_mod_date(Date pc_mod_date) {
		this.pc_mod_date = pc_mod_date;
	}

}
