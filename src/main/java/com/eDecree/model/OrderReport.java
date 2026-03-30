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
@Table(name="order_report_data")
public class OrderReport {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="ord_seq")
	@SequenceGenerator(name="ord_seq", sequenceName="ord_seq", allocationSize=1)
	@Column(name="ord_id")
	private Long ord_id;
	
	@Column(name="ord_remark")
	private  String ord_remark;
	
	private transient Integer cl_flag =0;
	
	@Column(name="ord_created")
	private Date ord_created;
	
	@Column(name="ord_created_by")
	private Long ord_created_by;
	
	@Column(name="ord_fd_mid")
	private Long ord_fd_mid;
	
	@Column(name="ord_sd_mid")
	private Long ord_sd_mid;
	
	@Column(name="ord_rec_status")
	private Integer ord_rec_status;
	
	@Column (name = "ord_mod_by")
	private Long ord_mod_by;
	
	@Column (name = "ord_mod_date")
	private Date ord_mod_date;
	
	@Column(name ="ord_consignment_no")
	private String ord_consignment_no;
	
	@Column(name ="ord_submitted_date")
	private Date ord_submitted_date;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ord_sd_mid",insertable = false, updatable = false)
	private SubDocument subDocument;
	
	@Transient
	private boolean checked;
	
	
	

	

	public Integer getCl_flag() {
		return cl_flag;
	}

	public void setCl_flag(Integer cl_flag) {
		this.cl_flag = cl_flag;
	}

	public Long getOrd_id() {
		return ord_id;
	}

	public void setOrd_id(Long ord_id) {
		this.ord_id = ord_id;
	}

	public String getOrd_remark() {
		return ord_remark;
	}

	public void setOrd_remark(String ord_remark) {
		this.ord_remark = ord_remark;
	}

	public Date getOrd_created() {
		return ord_created;
	}

	public void setOrd_created(Date ord_created) {
		this.ord_created = ord_created;
	}

	public Long getOrd_fd_mid() {
		return ord_fd_mid;
	}

	public void setOrd_fd_mid(Long ord_fd_mid) {
		this.ord_fd_mid = ord_fd_mid;
	}

	public Long getOrd_sd_mid() {
		return ord_sd_mid;
	}

	public void setOrd_sd_mid(Long ord_sd_mid) {
		this.ord_sd_mid = ord_sd_mid;
	}

	public SubDocument getSubDocument() {
		return subDocument;
	}

	public void setSubDocument(SubDocument subDocument) {
		this.subDocument = subDocument;
	}

	public Long getOrd_created_by() {
		return ord_created_by;
	}

	public void setOrd_created_by(Long ord_created_by) {
		this.ord_created_by = ord_created_by;
	}

	public Integer getOrd_rec_status() {
		return ord_rec_status;
	}

	public void setOrd_rec_status(Integer ord_rec_status) {
		this.ord_rec_status = ord_rec_status;
	}

	public Long getOrd_mod_by() {
		return ord_mod_by;
	}

	public void setOrd_mod_by(Long ord_mod_by) {
		this.ord_mod_by = ord_mod_by;
	}

	public Date getOrd_mod_date() {
		return ord_mod_date;
	}

	public void setOrd_mod_date(Date ord_mod_date) {
		this.ord_mod_date = ord_mod_date;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getOrd_consignment_no() {
		return ord_consignment_no;
	}

	public void setOrd_consignment_no(String ord_consignment_no) {
		this.ord_consignment_no = ord_consignment_no;
	}

	public Date getOrd_submitted_date() {
		return ord_submitted_date;
	}

	public void setOrd_submitted_date(Date ord_submitted_date) {
		this.ord_submitted_date = ord_submitted_date;
	}
	
	
	
	
}
