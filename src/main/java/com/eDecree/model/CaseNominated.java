package com.eDecree.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name="case_nominated")
public class CaseNominated {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="case_nominated_seq")
	@SequenceGenerator(name="case_nominated_seq", sequenceName="case_nominated_seq", allocationSize=1)
	@Column(name = "cn_id")
	private Long cn_id;
	
	@Column(name ="cn_fd_mid")
	private Long cn_fd_mid;
	
	@Column(name ="cn_start_date")
	private Date cn_start_date;
	
	@Column(name ="cn_start_cm_mid")
	private Long cn_start_cm_mid;
	
	@Column(name ="cn_from_date")
	private Date cn_from_date;
	
	@Column(name ="cn_from_clt_mid")
	private Long cn_from_clt_mid;
	
	@Column(name ="cn_from_cm_mid")
	private Long cn_from_cm_mid;
	
	@Column(name ="cn_from_jg_mid")
	private Long cn_from_jg_mid;
	
	@Column(name ="cn_todate")
	private Date cn_todate;
	
	@Column(name ="cn_tocm_mid")
	private Long cn_tocm_mid;
	
	@Column(name ="cn_toclt_mid")
	private Long cn_toclt_mid;
	
	@Column(name ="cn_by_um_mid")
	private Long cn_by_um_mid;
	
	@Column(name ="cn_nominated_date")
	private Date cn_nominated_date;
	
	@Column(name ="cn_nominated_tocm_mid")
	private Long cn_nominated_tocm_mid;
	
	@Column(name ="cn_nominated_tojg_mid")
	private Long cn_nominated_tojg_mid;
	
	@Column(name ="cn_nominated_toclt_mid")
	private Long cn_nominated_toclt_mid;
	
	
	@Column(name ="cn_nominated_by_um_mid")
	private Long cn_nominated_by_um_mid;
	
	@Column(name ="cn_tojg_mid")
	private Long cn_tojg_mid;
	
	@Column(name ="cn_nominated_stage")
	private Long cn_nominated_stage;
	
	@Column(name ="cn_rec_status")
	private Integer cn_rec_status;

	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cn_from_cm_mid",insertable = false, updatable = false)
	private CourtMaster fromCourtMaster;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cn_from_jg_mid",insertable = false, updatable = false)
	private Judge fromJudge;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cn_tocm_mid",insertable = false, updatable = false)
	private CourtMaster toCourtMaster;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cn_tojg_mid",insertable = false, updatable = false)
	private Judge toJudge;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cn_toclt_mid",insertable = false, updatable = false)
	private CauseListType toCauseListType;
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cn_by_um_mid",insertable = false, updatable = false)
	private User byFromUser;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cn_nominated_by_um_mid",insertable = false, updatable = false)
	private User byNominatedUser;
	
	
	public User getByFromUser() {
		return byFromUser;
	}

	public void setByFromUser(User byFromUser) {
		this.byFromUser = byFromUser;
	}

	public User getByNominatedUser() {
		return byNominatedUser;
	}

	public void setByNominatedUser(User byNominatedUser) {
		this.byNominatedUser = byNominatedUser;
	}

	public CauseListType getToCauseListType() {
		return toCauseListType;
	}

	public void setToCauseListType(CauseListType toCauseListType) {
		this.toCauseListType = toCauseListType;
	}

	public Judge getFromJudge() {
		return fromJudge;
	}

	public void setFromJudge(Judge fromJudge) {
		this.fromJudge = fromJudge;
	}

	public Judge getToJudge() {
		return toJudge;
	}

	public void setToJudge(Judge toJudge) {
		this.toJudge = toJudge;
	}

	public CourtMaster getFromCourtMaster() {
		return fromCourtMaster;
	}

	public void setFromCourtMaster(CourtMaster fromCourtMaster) {
		this.fromCourtMaster = fromCourtMaster;
	}

	public CourtMaster getToCourtMaster() {
		return toCourtMaster;
	}

	public void setToCourtMaster(CourtMaster toCourtMaster) {
		this.toCourtMaster = toCourtMaster;
	}

	public Long getCn_nominated_stage() {
		return cn_nominated_stage;
	}

	public void setCn_nominated_stage(Long cn_nominated_stage) {
		this.cn_nominated_stage = cn_nominated_stage;
	}

	public Integer getCn_rec_status() {
		return cn_rec_status;
	}

	public void setCn_rec_status(Integer cn_rec_status) {
		this.cn_rec_status = cn_rec_status;
	}

	public Long getCn_fd_mid() {
		return cn_fd_mid;
	}

	public void setCn_fd_mid(Long cn_fd_mid) {
		this.cn_fd_mid = cn_fd_mid;
	}

	public Long getCn_tojg_mid() {
		return cn_tojg_mid;
	}

	public void setCn_tojg_mid(Long cn_tojg_mid) {
		this.cn_tojg_mid = cn_tojg_mid;
	}

	public Long getCn_id() {
		return cn_id;
	}

	public void setCn_id(Long cn_id) {
		this.cn_id = cn_id;
	}

	public Date getCn_start_date() {
		return cn_start_date;
	}

	public void setCn_start_date(Date cn_start_date) {
		this.cn_start_date = cn_start_date;
	}

	public Long getCn_start_cm_mid() {
		return cn_start_cm_mid;
	}

	public void setCn_start_cm_mid(Long cn_start_cm_mid) {
		this.cn_start_cm_mid = cn_start_cm_mid;
	}

	public Date getCn_from_date() {
		return cn_from_date;
	}

	public void setCn_from_date(Date cn_from_date) {
		this.cn_from_date = cn_from_date;
	}

	public Long getCn_from_cm_mid() {
		return cn_from_cm_mid;
	}

	public void setCn_from_cm_mid(Long cn_from_cm_mid) {
		this.cn_from_cm_mid = cn_from_cm_mid;
	}

	public Long getCn_from_jg_mid() {
		return cn_from_jg_mid;
	}

	public void setCn_from_jg_mid(Long cn_from_jg_mid) {
		this.cn_from_jg_mid = cn_from_jg_mid;
	}

	public Date getCn_todate() {
		return cn_todate;
	}

	public void setCn_todate(Date cn_todate) {
		this.cn_todate = cn_todate;
	}

	public Long getCn_tocm_mid() {
		return cn_tocm_mid;
	}

	public void setCn_tocm_mid(Long cn_tocm_mid) {
		this.cn_tocm_mid = cn_tocm_mid;
	}

	public Long getCn_by_um_mid() {
		return cn_by_um_mid;
	}

	public void setCn_by_um_mid(Long cn_by_um_mid) {
		this.cn_by_um_mid = cn_by_um_mid;
	}

	public Date getCn_nominated_date() {
		return cn_nominated_date;
	}

	public void setCn_nominated_date(Date cn_nominated_date) {
		this.cn_nominated_date = cn_nominated_date;
	}

	public Long getCn_nominated_tocm_mid() {
		return cn_nominated_tocm_mid;
	}

	public void setCn_nominated_tocm_mid(Long cn_nominated_tocm_mid) {
		this.cn_nominated_tocm_mid = cn_nominated_tocm_mid;
	}

	public Long getCn_nominated_tojg_mid() {
		return cn_nominated_tojg_mid;
	}

	public void setCn_nominated_tojg_mid(Long cn_nominated_tojg_mid) {
		this.cn_nominated_tojg_mid = cn_nominated_tojg_mid;
	}

	public Long getCn_nominated_by_um_mid() {
		return cn_nominated_by_um_mid;
	}

	public void setCn_nominated_by_um_mid(Long cn_nominated_by_um_mid) {
		this.cn_nominated_by_um_mid = cn_nominated_by_um_mid;
	}

	public Long getCn_from_clt_mid() {
		return cn_from_clt_mid;
	}

	public void setCn_from_clt_mid(Long cn_from_clt_mid) {
		this.cn_from_clt_mid = cn_from_clt_mid;
	}

	public Long getCn_toclt_mid() {
		return cn_toclt_mid;
	}

	public void setCn_toclt_mid(Long cn_toclt_mid) {
		this.cn_toclt_mid = cn_toclt_mid;
	}

	public Long getCn_nominated_toclt_mid() {
		return cn_nominated_toclt_mid;
	}

	public void setCn_nominated_toclt_mid(Long cn_nominated_toclt_mid) {
		this.cn_nominated_toclt_mid = cn_nominated_toclt_mid;
	}
	
	

}
