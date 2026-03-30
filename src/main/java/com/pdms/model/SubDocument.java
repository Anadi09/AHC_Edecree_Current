package com.pdms.model;

import java.util.Date;

/*import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;*/

//@Entity
//@Table(name = "sub_documents")
public class SubDocument {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	 * "sub_documents_seq")
	 * 
	 * @SequenceGenerator(name = "sub_documents_seq", sequenceName =
	 * "sub_documents_seq", allocationSize = 1)
	 * 
	 * @Column(name = "sd_id")
	 */
	public Long sd_id;

	// @Column(name = "sd_fd_mid")
	public Long sd_fd_mid;

	// @Column(name = "sd_if_mid")
	public Long sd_if_mid;

	// @Column(name = "sd_document_name")
	public String sd_document_name;

	// @Column(name = "sd_no_of_pages")
	public int sd_no_of_pages;

	// @Column(name = "sd_version")
	public int sd_version;

	// @Column(name = "sd_document_id")
	public Integer sd_document_id;

	// @Column(name = "sd_document_no")
	public Integer sd_document_no;

	// @Column(name = "sd_document_year")
	public Integer sd_document_year;

	// @Column(name = "sd_party")
	public String sd_party;

	// @Column(name = "sd_description")
	public String sd_description;

	// @Column(name = "sd_submitted_date")
	public Date sd_submitted_date;

	// @Column(name = "sd_cr_by")
	public Long sd_cr_by;

	// @Column(name = "sd_cr_date")
	public Date sd_cr_date;

	// @Column(name = "sd_rec_status")
	public int sd_rec_status;

	// @Column(name = "sd_major_sequence")
	public int sd_major_sequence;

	// @Column(name = "sd_minor_sequence")
	public int sd_minor_sequence;

	// @Column(name = "sd_judgement_id")
	public Long sd_judgement_id;

	// @Column(name = "sd_counsel")
	public String sd_counsel;

	// @Column(name = "sd_status")
	public Long sd_status;

	// @Column(name = "sd_date")
	public Date sd_date;

	// @Column(name = "sd_mod_by")
	public Long sd_mod_by;

	// @Column(name = "sd_mod_date")
	public Date sd_mod_date;

	// @Transient
	public boolean checked;

	// @Transient
	public Long fileSize;

	// @Transient
	public String documentType;

	private String createdDate;

	private String submittedDate;

	private String sdModifiedDate;

	// @OneToOne(cascade = CascadeType.PERSIST)
	// @JoinColumn(name = "sd_if_mid", insertable = false, updatable = false)
	public IndexField indexField;

	public IndexField getIndexField() {
		return indexField;
	}

	public void setIndexField(IndexField indexField) {
		this.indexField = indexField;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Long getSd_id() {
		return sd_id;
	}

	public void setSd_id(Long sd_id) {
		this.sd_id = sd_id;
	}

	public Long getSd_fd_mid() {
		return sd_fd_mid;
	}

	public void setSd_fd_mid(Long sd_fd_mid) {
		this.sd_fd_mid = sd_fd_mid;
	}

	public Long getSd_if_mid() {
		return sd_if_mid;
	}

	public void setSd_if_mid(Long sd_if_mid) {
		this.sd_if_mid = sd_if_mid;
	}

	public String getSd_document_name() {
		return sd_document_name;
	}

	public void setSd_document_name(String sd_document_name) {
		this.sd_document_name = sd_document_name;
	}

	public int getSd_no_of_pages() {
		return sd_no_of_pages;
	}

	public void setSd_no_of_pages(int sd_no_of_pages) {
		this.sd_no_of_pages = sd_no_of_pages;
	}

	public int getSd_version() {
		return sd_version;
	}

	public void setSd_version(int sd_version) {
		this.sd_version = sd_version;
	}

	public Integer getSd_document_id() {
		return sd_document_id;
	}

	public void setSd_document_id(Integer sd_document_id) {
		this.sd_document_id = sd_document_id;
	}

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

	public String getSd_party() {
		return sd_party;
	}

	public void setSd_party(String sd_party) {
		this.sd_party = sd_party;
	}

	public String getSd_description() {
		return sd_description;
	}

	public void setSd_description(String sd_description) {
		this.sd_description = sd_description;
	}

	public Date getSd_submitted_date() {
		return sd_submitted_date;
	}

	public void setSd_submitted_date(Date sd_submitted_date) {
		this.sd_submitted_date = sd_submitted_date;
	}

	public Long getSd_cr_by() {
		return sd_cr_by;
	}

	public void setSd_cr_by(Long sd_cr_by) {
		this.sd_cr_by = sd_cr_by;
	}

	public Date getSd_cr_date() {
		return sd_cr_date;
	}

	public void setSd_cr_date(Date sd_cr_date) {
		this.sd_cr_date = sd_cr_date;
	}

	public int getSd_rec_status() {
		return sd_rec_status;
	}

	public void setSd_rec_status(int sd_rec_status) {
		this.sd_rec_status = sd_rec_status;
	}

	public int getSd_major_sequence() {
		return sd_major_sequence;
	}

	public void setSd_major_sequence(int sd_major_sequence) {
		this.sd_major_sequence = sd_major_sequence;
	}

	public int getSd_minor_sequence() {
		return sd_minor_sequence;
	}

	public void setSd_minor_sequence(int sd_minor_sequence) {
		this.sd_minor_sequence = sd_minor_sequence;
	}

	public String getSd_counsel() {
		return sd_counsel;
	}

	public void setSd_counsel(String sd_counsel) {
		this.sd_counsel = sd_counsel;
	}

	public Long getSd_judgement_id() {
		return sd_judgement_id;
	}

	public void setSd_judgement_id(Long sd_judgement_id) {
		this.sd_judgement_id = sd_judgement_id;
	}

	public Long getSd_status() {
		return sd_status;
	}

	public void setSd_status(Long sd_status) {
		this.sd_status = sd_status;
	}

	public Date getSd_date() {
		return sd_date;
	}

	public void setSd_date(Date sd_date) {
		this.sd_date = sd_date;
	}

	public Long getSd_mod_by() {
		return sd_mod_by;
	}

	public void setSd_mod_by(Long sd_mod_by) {
		this.sd_mod_by = sd_mod_by;
	}

	public Date getSd_mod_date() {
		return sd_mod_date;
	}

	public void setSd_mod_date(Date sd_mod_date) {
		this.sd_mod_date = sd_mod_date;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getSdModifiedDate() {
		return sdModifiedDate;
	}

	public void setSdModifiedDate(String sdModifiedDate) {
		this.sdModifiedDate = sdModifiedDate;
	}

	@Override
	public String toString() {
		return "SubDocument [sd_id=" + sd_id + ", sd_fd_mid=" + sd_fd_mid + ", sd_if_mid=" + sd_if_mid
				+ ", sd_document_name=" + sd_document_name + ", sd_no_of_pages=" + sd_no_of_pages + ", sd_version="
				+ sd_version + ", sd_document_id=" + sd_document_id + ", sd_document_no=" + sd_document_no
				+ ", sd_document_year=" + sd_document_year + ", sd_party=" + sd_party + ", sd_description="
				+ sd_description + ", sd_submitted_date=" + sd_submitted_date + ", sd_cr_by=" + sd_cr_by
				+ ", sd_cr_date=" + sd_cr_date + ", sd_rec_status=" + sd_rec_status + ", sd_major_sequence="
				+ sd_major_sequence + ", sd_minor_sequence=" + sd_minor_sequence + ", sd_judgement_id="
				+ sd_judgement_id + ", sd_counsel=" + sd_counsel + ", sd_status=" + sd_status + ", sd_date=" + sd_date
				+ ", sd_mod_by=" + sd_mod_by + ", sd_mod_date=" + sd_mod_date + ", checked=" + checked + ", fileSize="
				+ fileSize + ", documentType=" + documentType + ", createdDate=" + createdDate + ", submittedDate="
				+ submittedDate + ", sdModifiedDate=" + sdModifiedDate + ", indexField=" + indexField + "]";
	}

}
