package com.eDecree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "process_notice_fee")
public class ProcessNoticeFee {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="process_notice_fee_seq")
	@SequenceGenerator(name="process_notice_fee_seq", sequenceName="process_notice_fee_seq", allocationSize=1)
	@Column(name = "pnf_id")
	private Long pnf_id;
	
	
	@Column(name = "pnf_fd_mid")
	private Long pnf_fd_mid; 
	
	
	
	@Column(name="pnf_cr_date")
	private Date pnf_cr_date;
	
	@Column(name="pnf_mod_date")
	private Date pnf_mod_date;
	
	@Column(name="pnf_rec_status")
	private Integer pnf_rec_status;
	
	@Column(name = "pnf_document_name",columnDefinition="TEXT")
	private String pnf_document_name;
	
	@Column(name="pnf_cr_by")
	private Long pnf_cr_by;
	
	
	@Column(name = "pnf_receipt_no",columnDefinition="TEXT")
	private String pnf_receipt_no;
	
	@Column(name="pnf_amount")
	private Integer pnf_amount;

	public Long getPnf_id() {
		return pnf_id;
	}

	public void setPnf_id(Long pnf_id) {
		this.pnf_id = pnf_id;
	}

	public Long getPnf_fd_mid() {
		return pnf_fd_mid;
	}

	public void setPnf_fd_mid(Long pnf_fd_mid) {
		this.pnf_fd_mid = pnf_fd_mid;
	}

	public Date getPnf_cr_date() {
		return pnf_cr_date;
	}

	public void setPnf_cr_date(Date pnf_cr_date) {
		this.pnf_cr_date = pnf_cr_date;
	}

	public Date getPnf_mod_date() {
		return pnf_mod_date;
	}

	public void setPnf_mod_date(Date pnf_mod_date) {
		this.pnf_mod_date = pnf_mod_date;
	}

	public Integer getPnf_rec_status() {
		return pnf_rec_status;
	}

	public void setPnf_rec_status(Integer pnf_rec_status) {
		this.pnf_rec_status = pnf_rec_status;
	}

	public String getPnf_document_name() {
		return pnf_document_name;
	}

	public void setPnf_document_name(String pnf_document_name) {
		this.pnf_document_name = pnf_document_name;
	}

	public Long getPnf_cr_by() {
		return pnf_cr_by;
	}

	public void setPnf_cr_by(Long pnf_cr_by) {
		this.pnf_cr_by = pnf_cr_by;
	}

	public String getPnf_receipt_no() {
		return pnf_receipt_no;
	}

	public void setPnf_receipt_no(String pnf_receipt_no) {
		this.pnf_receipt_no = pnf_receipt_no;
	}

	public Integer getPnf_amount() {
		return pnf_amount;
	}

	public void setPnf_amount(Integer pnf_amount) {
		this.pnf_amount = pnf_amount;
	}

	
	
	
	
	
	
	
}
