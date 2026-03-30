package com.pdms.model;



public class Judgement {
	private String dfd_barcode;
	private String dfd_judgement_date;
	private Integer dfd_page_count;
	private Long judgementFileSize;

	/*
	 * public Judgement() { // TODO Auto-generated constructor stub }
	 * 
	 * public Judgement(String dfd_barcode) { super(); this.dfd_barcode =
	 * dfd_barcode; }
	 */

	public String getDfd_barcode() {
		return dfd_barcode;
	}

	/*
	 * public Judgement setDfd_barcode(String dfd_barcode) { this.dfd_barcode =
	 * dfd_barcode; return new Judgement(this.dfd_barcode); }
	 */

	public String getDfd_judgement_date() {
		return dfd_judgement_date;
	}

	/*
	 * public Judgement setDfd_judgement_date(String dfd_judgement_date) {
	 * this.dfd_judgement_date = dfd_judgement_date; return new
	 * Judgement(this.dfd_judgement_date); }
	 */
	public Integer getDfd_page_count() {
		return dfd_page_count;
	}

	public void setDfd_page_count(Integer dfd_page_count) {
		this.dfd_page_count = dfd_page_count;
	}

	public void setDfd_barcode(String dfd_barcode) {
		this.dfd_barcode = dfd_barcode;
	}

	public void setDfd_judgement_date(String dfd_judgement_date) {
		this.dfd_judgement_date = dfd_judgement_date;
	}

	public Long getJudgementFileSize() {
		return judgementFileSize;
	}

	public void setJudgementFileSize(Long judgementFileSize) {
		this.judgementFileSize = judgementFileSize;
	}

	@Override
	public String toString() {
		return "Judgement [dfd_barcode=" + dfd_barcode + ", dfd_judgement_date=" + dfd_judgement_date
				+ ", dfd_page_count=" + dfd_page_count + ", judgementFileSize=" + judgementFileSize + "]";
	}
	
	

}
