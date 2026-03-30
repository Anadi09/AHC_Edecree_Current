package com.pdms.model;

public class FileUploadStatusHandler {

	String status;
	Long subDocumentId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSubDocumentId() {
		return subDocumentId;
	}

	public void setSubDocumentId(Long subDocumentId) {
		this.subDocumentId = subDocumentId;
	}

	@Override
	public String toString() {
		return "FileUploadStatusHandler [status=" + status + ", subDocumentId=" + subDocumentId + "]";
	}

}
