package com.pdms.model;

import java.math.BigInteger;

public class PdmsMetaData {

	private BigInteger md_id;

	private String mf_label;

	private BigInteger md_fd_mid;

	private String md_value;

	private String jg_name;

	private String lk_longname_district;

	private String ps_name_police_station;

	public BigInteger getMd_id() {
		return md_id;
	}

	public void setMd_id(BigInteger md_id) {
		this.md_id = md_id;
	}

	public String getMf_label() {
		return mf_label;
	}

	public void setMf_label(String mf_label) {
		this.mf_label = mf_label;
	}

	public BigInteger getMd_fd_mid() {
		return md_fd_mid;
	}

	public void setMd_fd_mid(BigInteger md_fd_mid) {
		this.md_fd_mid = md_fd_mid;
	}

	public String getMd_value() {
		return md_value;
	}

	public void setMd_value(String md_value) {
		this.md_value = md_value;
	}

	public String getJg_name() {
		return jg_name;
	}

	public void setJg_name(String jg_name) {
		this.jg_name = jg_name;
	}

	public String getLk_longname_district() {
		return lk_longname_district;
	}

	public void setLk_longname_district(String lk_longname_district) {
		this.lk_longname_district = lk_longname_district;
	}

	public String getPs_name_police_station() {
		return ps_name_police_station;
	}

	public void setPs_name_police_station(String ps_name_police_station) {
		this.ps_name_police_station = ps_name_police_station;
	}

}
