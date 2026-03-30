package com.eDecree.model;

import java.util.Date;

public class OrderReportSub {
	
	
	private Long if_id ;
	
	private String ord_consignment_no;
	
	private String ord_remark;
	
	private Long sd_fd_mid;
	
	
	private Integer at_id;
	
	private String sd_submitted_date;
	
	
	private Integer sd_document_no;
	
	
	private Integer sd_document_year;
	
	

	

	public Integer getSd_document_no() {
		return sd_document_no;
	}

	public void setSd_document_no(Integer sd_document_no) {
		this.sd_document_no = sd_document_no;
	}

	public Integer getSd_document_year() {
		return sd_document_year;
	}

	public void setSd_document_year(Integer sd_document_year) {
		this.sd_document_year = sd_document_year;
	}

	public String getSd_submitted_date() {
		return sd_submitted_date;
	}

	public void setSd_submitted_date(String sd_submitted_date) {
		this.sd_submitted_date = sd_submitted_date;
	}

	public Integer getAt_id() {
		return at_id;
	}

	public void setAt_id(Integer at_id) {
		this.at_id = at_id;
	}

	public Long getIf_id() {
		return if_id;
	}

	public void setIf_id(Long if_id) {
		this.if_id = if_id;
	}

	public String getOrd_consignment_no() {
		return ord_consignment_no;
	}

	public void setOrd_consignment_no(String ord_consignment_no) {
		this.ord_consignment_no = ord_consignment_no;
	}

	public String getOrd_remark() {
		return ord_remark;
	}

	public void setOrd_remark(String ord_remark) {
		this.ord_remark = ord_remark;
	}

	public Long getSd_fd_mid() {
		return sd_fd_mid;
	}

	public void setSd_fd_mid(Long sd_fd_mid) {
		this.sd_fd_mid = sd_fd_mid;
	}

	@Override
	public String toString() {
		return "OrderReportSub [if_id=" + if_id + ", ord_consignment_no=" + ord_consignment_no + ", ord_remark="
				+ ord_remark + ", sd_fd_mid=" + sd_fd_mid + "]";
	}
	
	
	

}
