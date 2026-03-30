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

@Entity
@Table(name="download_files")
public class DownloadFile {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="download_files_seq")
	@SequenceGenerator(name="download_files_seq", sequenceName="download_files_seq", allocationSize=1)
	@Column(name="df_id")
	private Long df_id;
	
	@Column(name="df_dr_mid")
	private Long df_dr_mid;
	
	@Column(name="df_sd_mid")
	private Long df_sd_mid;
	
	@Column(name="df_ord_mid")
	private Long df_ord_mid;
	
	@Column(name="df_submitted_date")
	private Date df_submitted_date;
	
	@Column(name="df_pages")
	private Integer df_pages;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "df_sd_mid",insertable = false, updatable = false)
	private SubDocument subDocument;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "df_ord_mid",insertable = false, updatable = false)
	private OrderReport ordeReport;
	
	public Long getDf_id() {
		return df_id;
	}

	public void setDf_id(Long df_id) {
		this.df_id = df_id;
	}

	public Long getDf_dr_mid() {
		return df_dr_mid;
	}

	public void setDf_dr_mid(Long df_dr_mid) {
		this.df_dr_mid = df_dr_mid;
	}

	public Long getDf_sd_mid() {
		return df_sd_mid;
	}

	public void setDf_sd_mid(Long df_sd_mid) {
		this.df_sd_mid = df_sd_mid;
	}

	public Long getDf_ord_mid() {
		return df_ord_mid;
	}

	public void setDf_ord_mid(Long df_ord_mid) {
		this.df_ord_mid = df_ord_mid;
	}

	public Integer getDf_pages() {
		return df_pages;
	}

	public void setDf_pages(Integer df_pages) {
		this.df_pages = df_pages;
	}

	public Date getDf_submitted_date() {
		return df_submitted_date;
	}

	public void setDf_submitted_date(Date df_submitted_date) {
		this.df_submitted_date = df_submitted_date;
	}

	public SubDocument getSubDocument() {
		return subDocument;
	}

	public void setSubDocument(SubDocument subDocument) {
		this.subDocument = subDocument;
	}

	public OrderReport getOrdeReport() {
		return ordeReport;
	}

	public void setOrdeReport(OrderReport ordeReport) {
		this.ordeReport = ordeReport;
	}
	
	
}
