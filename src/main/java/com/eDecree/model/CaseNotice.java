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
@Table(name = "case_notice")
public class CaseNotice {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="case_notice_seq")
	@SequenceGenerator(name="case_notice_seq", sequenceName="case_notice_seq", allocationSize=1)
	@Column(name = "cn_id")
	private Long cn_id;
	@Column(name = "cn_nf_mid")
	private Long cn_nf_mid; 
	
	@Column(name="cn_cr_date")
	private Date cn_cr_date;
	
	@Column(name="cn_mod_date")
	private Date cn_mod_date;
	
	@Column(name="cn_rec_status")
	private int cn_rec_status;
	
	@Column(name = "cn_a1",columnDefinition="TEXT")
	private String a1;
	@Column(name = "cn_a2",columnDefinition="TEXT")
	private String a2;
	@Column(name = "cn_a3",columnDefinition="TEXT")
	private String a3;
	@Column(name = "cn_a4",columnDefinition="TEXT")
	private String a4;
	@Column(name = "cn_a5",columnDefinition="TEXT")
	private String a5;
	@Column(name = "cn_a6",columnDefinition="TEXT")
	private String a6;
	@Column(name = "cn_a7",columnDefinition="TEXT")
	private String a7;
	@Column(name = "cn_a8",columnDefinition="TEXT")
	private String a8;
	@Column(name = "cn_a9",columnDefinition="TEXT")
	private String a9;
	@Column(name = "cn_a10",columnDefinition="TEXT")
	private String a10;
	@Column(name = "cn_a11",columnDefinition="TEXT")
	private String a11;
	@Column(name = "cn_a12",columnDefinition="TEXT")
	private String a12;
	@Column(name = "cn_a13",columnDefinition="TEXT")
	private String a13;
	@Column(name = "cn_a14",columnDefinition="TEXT")
	private String a14;
	@Column(name = "cn_a15",columnDefinition="TEXT")
	private String a15;
	@Column(name = "cn_a16",columnDefinition="TEXT")
	private String a16;
	@Column(name = "cn_a17",columnDefinition="TEXT")
	private String a17;
	@Column(name = "cn_a18",columnDefinition="TEXT")
	private String a18;
	@Column(name = "cn_a19",columnDefinition="TEXT")
	private String a19;
	@Column(name = "cn_a20",columnDefinition="TEXT")
	private String a20;
	@Column(name = "cn_a21",columnDefinition="TEXT")
	private String a21;
	@Column(name = "cn_a22",columnDefinition="TEXT")
	private String a22;
	
	@Column(name="cn_cr_by")
	private Long cn_cr_by;
	
	
	
	
	
	
	public Long getCn_cr_by() {
		return cn_cr_by;
	}
	public void setCn_cr_by(Long cn_cr_by) {
		this.cn_cr_by = cn_cr_by;
	}
	public Long getCn_nf_mid() {
		return cn_nf_mid;
	}
	public void setCn_nf_mid(Long cn_nf_mid) {
		this.cn_nf_mid = cn_nf_mid;
	}
	public Date getCn_cr_date() {
		return cn_cr_date;
	}
	public void setCn_cr_date(Date cn_cr_date) {
		this.cn_cr_date = cn_cr_date;
	}
	public Date getCn_mod_date() {
		return cn_mod_date;
	}
	public void setCn_mod_date(Date cn_mod_date) {
		this.cn_mod_date = cn_mod_date;
	}
	public int getCn_rec_status() {
		return cn_rec_status;
	}
	public void setCn_rec_status(int cn_rec_status) {
		this.cn_rec_status = cn_rec_status;
	}
	public Long getCn_id() {
		return cn_id;
	}
	public void setCn_id(Long cn_id) {
		this.cn_id = cn_id;
	}
	public String getA1() {
		return a1;
	}
	public void setA1(String a1) {
		this.a1 = a1;
	}
	public String getA2() {
		return a2;
	}
	public void setA2(String a2) {
		this.a2 = a2;
	}
	public String getA3() {
		return a3;
	}
	public void setA3(String a3) {
		this.a3 = a3;
	}
	public String getA4() {
		return a4;
	}
	public void setA4(String a4) {
		this.a4 = a4;
	}
	public String getA5() {
		return a5;
	}
	public void setA5(String a5) {
		this.a5 = a5;
	}
	public String getA6() {
		return a6;
	}
	public void setA6(String a6) {
		this.a6 = a6;
	}
	public String getA7() {
		return a7;
	}
	public void setA7(String a7) {
		this.a7 = a7;
	}
	public String getA8() {
		return a8;
	}
	public void setA8(String a8) {
		this.a8 = a8;
	}
	public String getA9() {
		return a9;
	}
	public void setA9(String a9) {
		this.a9 = a9;
	}
	public String getA10() {
		return a10;
	}
	public void setA10(String a10) {
		this.a10 = a10;
	}
	public String getA11() {
		return a11;
	}
	public void setA11(String a11) {
		this.a11 = a11;
	}
	public String getA12() {
		return a12;
	}
	public void setA12(String a12) {
		this.a12 = a12;
	}
	public String getA13() {
		return a13;
	}
	public void setA13(String a13) {
		this.a13 = a13;
	}
	public String getA14() {
		return a14;
	}
	public void setA14(String a14) {
		this.a14 = a14;
	}
	public String getA15() {
		return a15;
	}
	public void setA15(String a15) {
		this.a15 = a15;
	}
	public String getA16() {
		return a16;
	}
	public void setA16(String a16) {
		this.a16 = a16;
	}
	public String getA17() {
		return a17;
	}
	public void setA17(String a17) {
		this.a17 = a17;
	}
	public String getA18() {
		return a18;
	}
	public void setA18(String a18) {
		this.a18 = a18;
	}
	public String getA19() {
		return a19;
	}
	public void setA19(String a19) {
		this.a19 = a19;
	}
	public String getA20() {
		return a20;
	}
	public void setA20(String a20) {
		this.a20 = a20;
	}
	public String getA21() {
		return a21;
	}
	public void setA21(String a21) {
		this.a21 = a21;
	}
	public String getA22() {
		return a22;
	}
	public void setA22(String a22) {
		this.a22 = a22;
	}
	
	
}
