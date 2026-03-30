package com.eDecree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="notes")
public class Notes {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="notes_seq")
	@SequenceGenerator(name="notes_seq", sequenceName="notes_seq", allocationSize=1)
	
	@Column(name="nt_id")
	private Long nt_id;
	
	@Column(name="nt_fd_mid")
	private Long nt_fd_mid;
	
	@Column(name="nt_cr_by")
	private Long nt_cr_by;
	
	@Column(name="nt_notes")
	private String nt_notes;
	
	@Column(name="nt_cr_date")
	private Date nt_cr_date;
	
	@Column(name="nt_mod_date")
	private Date nt_mod_date;
	
	@ManyToOne
    @JoinColumn(name="nt_cr_by",referencedColumnName="jg_id",insertable = false, updatable = false)
    private Judge judge;
	
	
	public Judge getJudge() {
		return judge;
	}

	public void setJudge(Judge judge) {
		this.judge = judge;
	}

	public Long getNt_id() {
		return nt_id;
	}

	public void setNt_id(Long nt_id) {
		this.nt_id = nt_id;
	}

	public Long getNt_fd_mid() {
		return nt_fd_mid;
	}

	public void setNt_fd_mid(Long nt_fd_mid) {
		this.nt_fd_mid = nt_fd_mid;
	}

	public Long getNt_cr_by() {
		return nt_cr_by;
	}

	public void setNt_cr_by(Long nt_cr_by) {
		this.nt_cr_by = nt_cr_by;
	}

	public String getNt_notes() {
		return nt_notes;
	}

	public void setNt_notes(String nt_notes) {
		this.nt_notes = nt_notes;
	}

	public Date getNt_cr_date() {
		return nt_cr_date;
	}

	public void setNt_cr_date(Date nt_cr_date) {
		this.nt_cr_date = nt_cr_date;
	}

	public Date getNt_mod_date() {
		return nt_mod_date;
	}

	public void setNt_mod_date(Date nt_mod_date) {
		this.nt_mod_date = nt_mod_date;
	}

	@Override
	public String toString() {
		return "Notes [nt_id=" + nt_id + ", nt_fd_mid=" + nt_fd_mid + ", nt_cr_by=" + nt_cr_by + ", nt_notes="
				+ nt_notes + ", nt_cr_date=" + nt_cr_date + ", nt_mod_date=" + nt_mod_date + ", judge=" + judge + "]";
	}


	
	

}
