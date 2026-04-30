package com.eDecree.model;

import java.util.Date;

public class DecreeExamDTO {

    private Long df_fd_mid;
    private String ct_label;
    private String fd_case_no;
    private Integer fd_case_year;
    private String df_remark;
    private Date df_cr_date;   // FIXED
    private boolean df_locked;

    public DecreeExamDTO(Long df_fd_mid,
            String ct_label,
            String fd_case_no,
            Integer fd_case_year,
            String df_remark,
            boolean df_locked,
            Date df_cr_date) {

this.df_fd_mid = df_fd_mid;
this.ct_label = ct_label;
this.fd_case_no = fd_case_no;
this.fd_case_year = fd_case_year;
this.df_remark = df_remark;
this.df_locked = df_locked;
this.df_cr_date = df_cr_date;
}

    // getters & setters

    public boolean isDf_locked() {
		return df_locked;
	}

	public void setDf_locked(boolean df_locked) {
		this.df_locked = df_locked;
	}

	public Long getDf_fd_mid() {
        return df_fd_mid;
    }

    public void setDf_fd_mid(Long df_fd_mid) {
        this.df_fd_mid = df_fd_mid;
    }

    public String getCt_label() {
        return ct_label;
    }

    public void setCt_label(String ct_label) {
        this.ct_label = ct_label;
    }

    public String getFd_case_no() {
        return fd_case_no;
    }

    public void setFd_case_no(String fd_case_no) {
        this.fd_case_no = fd_case_no;
    }

    public Integer getFd_case_year() {
        return fd_case_year;
    }

    public void setFd_case_year(Integer fd_case_year) {
        this.fd_case_year = fd_case_year;
    }

    public String getDf_remark() {
        return df_remark;
    }

    public void setDf_remark(String df_remark) {
        this.df_remark = df_remark;
    }

    public Date getDf_cr_date() {   // 
        return df_cr_date;
    }

    public void setDf_cr_date(Date df_cr_date) {
        this.df_cr_date = df_cr_date;
    }
}