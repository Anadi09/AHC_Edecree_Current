package com.eDecree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "decree_sms_log")
public class DecreeSmsLog {

	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "decree_sms_log_seq")
	    @SequenceGenerator(name = "decree_sms_log_seq", sequenceName = "decree_sms_log_seq", allocationSize = 1)
	    @Column(name = "dsl_id")
	    private Long dslId;

    @Column(name = "dsl_case_type", nullable = true)
    private String dslCaseType;

    @Column(name = "dsl_case_no", length = 50, nullable = true)
    private String dslCaseNo;

    @Column(name = "dsl_case_year", nullable = true)
    private Integer dslCaseYear;

    @Column(name = "dsl_aor", length = 100, nullable = false)
    private String dslAor;

    @Column(name = "dsl_mobile", length = 15, nullable = false)
    private String dslMobile;

    @Column(name = "dsl_send_date")
    private Date dslSendDate;

    @Column(name = "dsl_send_by", nullable = false)
    private Long dslSendBy;
    
    @Column(name="dsl_till_date")
    private Date  dslTillDate;
    
    @Column(name="dsl_sms_text")
    private String smsText;
    
    @Column(name="dsl_sms_status", nullable = false)
    private boolean dslSmsStatus;

    // ---------------- GETTERS & SETTERS ----------------

   

	public boolean isDslSmsStatus() {
		return dslSmsStatus;
	}

	public void setDslSmsStatus(boolean dslSmsStatus) {
		this.dslSmsStatus = dslSmsStatus;
	}

	public String getSmsText() {
		return smsText;
	}

	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}

	public Long getDslId() {
        return dslId;
    }

    public Date getDslTillDate() {
		return dslTillDate;
	}

	public void setDslTillDate(Date dslTillDate) {
		this.dslTillDate = dslTillDate;
	}

	public void setDslId(Long dslId) {
        this.dslId = dslId;
    }

    public String getDslCaseType() {
        return dslCaseType;
    }

    public void setDslCaseType(String dslCaseType) {
        this.dslCaseType = dslCaseType;
    }

    public String getDslCaseNo() {
        return dslCaseNo;
    }

    public void setDslCaseNo(String dslCaseNo) {
        this.dslCaseNo = dslCaseNo;
    }

    public Integer getDslCaseYear() {
        return dslCaseYear;
    }

    public void setDslCaseYear(Integer dslCaseYear) {
        this.dslCaseYear = dslCaseYear;
    }

    public String getDslAor() {
        return dslAor;
    }

    public void setDslAor(String dslAor) {
        this.dslAor = dslAor;
    }

    public String getDslMobile() {
        return dslMobile;
    }

    public void setDslMobile(String dslMobile) {
        this.dslMobile = dslMobile;
    }

    public Date getDslSendDate() {
        return dslSendDate;
    }

    public void setDslSendDate(Date dslSendDate) {
        this.dslSendDate = dslSendDate;
    }

    public Long getDslSendBy() {
        return dslSendBy;
    }

    public void setDslSendBy(Long dslSendBy) {
        this.dslSendBy = dslSendBy;
    }
    @Override
    public String toString() {  
    return "DecreeSmsLog[" +
    "dslId=" + dslId +
    ", dslCaseType=" + dslCaseType +
    ", dslCaseNo='" + dslCaseNo + 
    ", dslCaseYear=" + dslCaseYear +
    ", dslAor='" + dslAor +
    ", dslMobile='" + dslMobile + 
    ", dslSendDate=" + dslSendDate +
    ", dslSendBy=" + dslSendBy +
    ", dslTillDate=" + dslTillDate +
    ']';
    }
}


