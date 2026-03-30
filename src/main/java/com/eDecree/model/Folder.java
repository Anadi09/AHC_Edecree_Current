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
@Table(name = "FOLDERS")
public class Folder {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="folder_seq")
	@SequenceGenerator(name="folder_seq", sequenceName="folder_seq", allocationSize=1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DISPLAY_NAME")
	private String display_name;
	
	@Column(name = "FOLDER_NAME")
	private String folder_name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATED_BY")
	private Long created_by;
	
	@Column(name = "UPDATED_BY")
	private Long updated_by;
	
	@Column(name = "CREATED")
	private Date created;
	
	@Column(name = "UPDATED")
	private Date updated;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "REP_ID")
	private Long rep_id;
	
	@Column(name = "PARENT_ID")
	private Long parent_id;
	
	@Column(name = "level")
	private Integer level;

	@OneToMany(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID",insertable = false, updatable = false)	
	private List<Folder> childrens;
	
	
	@Transient
	private Long caseTypeId;
	
	@Transient
	private Long benchCodeId;
	
	@Transient
	private Integer noofdocs;
	
	@Transient
	private String sizeondisk;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getFolder_name() {
		return folder_name;
	}

	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getRep_id() {
		return rep_id;
	}

	public void setRep_id(Long rep_id) {
		this.rep_id = rep_id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public List<Folder> getChildrens() {
		
		return childrens;
	}

	public void setChildrens(List<Folder> childrens) {
		this.childrens = childrens;
	}

	public Long getCaseTypeId() {
		return caseTypeId;
	}

	public void setCaseTypeId(Long caseTypeId) {
		this.caseTypeId = caseTypeId;
	}

	public Long getBenchCodeId() {
		return benchCodeId;
	}

	public void setBenchCodeId(Long benchCodeId) {
		this.benchCodeId = benchCodeId;
	}

	public Integer getNoofdocs() {
		return noofdocs;
	}

	public void setNoofdocs(Integer noofdocs) {
		this.noofdocs = noofdocs;
	}


	public String getSizeondisk() {
		return sizeondisk;
	}

	public void setSizeondisk(String sizeondisk) {
		this.sizeondisk = sizeondisk;
	}
	
	
}
