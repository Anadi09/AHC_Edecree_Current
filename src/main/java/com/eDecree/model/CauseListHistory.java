package com.eDecree.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cause_list_history")
public class CauseListHistory {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="clh_seq")
	@SequenceGenerator(name="clh_seq", sequenceName="clh_seq", allocationSize=1)
	@Column(name = "clh_id")
	private Long clh_id;
	
	@Column(name = "clh_dol")
	private Date clh_dol;
	
	@Column(name = "clh_fd_mid")
	private Long clh_fd_mid;
	
	
	@Column(name = "clh_rec_status")
	private Integer cll_rec_status;
	
	@Column(name = "clh_cr_date")
	private Date clh_cr_date;

	public Long getClh_id() {
		return clh_id;
	}

	public void setClh_id(Long clh_id) {
		this.clh_id = clh_id;
	}

	public Date getClh_dol() {
		return clh_dol;
	}

	public void setClh_dol(Date clh_dol) {
		this.clh_dol = clh_dol;
	}

	public Long getClh_fd_mid() {
		return clh_fd_mid;
	}

	public void setClh_fd_mid(Long clh_fd_mid) {
		this.clh_fd_mid = clh_fd_mid;
	}

	public Integer getCll_rec_status() {
		return cll_rec_status;
	}

	public void setCll_rec_status(Integer cll_rec_status) {
		this.cll_rec_status = cll_rec_status;
	}

	public Date getClh_cr_date() {
		return clh_cr_date;
	}

	public void setClh_cr_date(Date clh_cr_date) {
		this.clh_cr_date = clh_cr_date;
	}

	
	

}
