package com.eDecree.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "FOLDER_CASETYPE_MAPPING")
public class FolderCaseTypeMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="folder_casetypeseq")
	@SequenceGenerator(name="folder_casetypeseq", sequenceName="folder_casetypeseq", allocationSize=1)
	@Column(name = "FCM_ID")
	private Long fcm_id;
	
	@Column(name = "FOLDER_ID")
	private Long folder_id;
	
	@Column(name = "CASE_TYPE_ID")
	private Long case_type_id;
	
	@Column(name = "BENCH_TYPE_ID")
	private Long bench_type_id;

	public Long getFcm_id() {
		return fcm_id;
	}

	public void setFcm_id(Long fcm_id) {
		this.fcm_id = fcm_id;
	}

	public Long getFolder_id() {
		return folder_id;
	}

	public void setFolder_id(Long folder_id) {
		this.folder_id = folder_id;
	}

	public Long getCase_type_id() {
		return case_type_id;
	}

	public void setCase_type_id(Long case_type_id) {
		this.case_type_id = case_type_id;
	}

	public Long getBench_type_id() {
		return bench_type_id;
	}

	public void setBench_type_id(Long bench_type_id) {
		this.bench_type_id = bench_type_id;
	}
	
	
}
