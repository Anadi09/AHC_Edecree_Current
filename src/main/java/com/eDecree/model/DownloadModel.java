package com.eDecree.model;

import java.util.List;

public class DownloadModel {
	
	private List<SubDocument> subDocuments;
	
	private List<OrderReport> orderReports;
	
	private Long fd_id;
	
	public List<SubDocument> getSubDocuments() {
		return subDocuments;
	}

	public void setSubDocuments(List<SubDocument> subDocuments) {
		this.subDocuments = subDocuments;
	}

	public List<OrderReport> getOrderReports() {
		return orderReports;
	}

	public void setOrderReports(List<OrderReport> orderReports) {
		this.orderReports = orderReports;
	}

	public Long getFd_id() {
		return fd_id;
	}

	public void setFd_id(Long fd_id) {
		this.fd_id = fd_id;
	}

	
}
