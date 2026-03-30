package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findmetadata", query = "select m from MetaDataView m ")
public class MetaDataView {
	
	@Id
	@Column(name = "md_id")
	private Long md_id;
	
	@Column(name = "md_mf_mid")
	private Long md_mf_mid;
	
	@Column(name = "md_fd_mid")
	private Long md_fd_mid;
	
	@Column(name = "metafield")
	private String metafield;
	
	@Column(name = "value")
	private String value;
	
	
	
	public Long getMd_id() {
		return md_id;
	}

	public void setMd_id(Long md_id) {
		this.md_id = md_id;
	}

	public Long getMd_mf_mid() {
		return md_mf_mid;
	}

	public void setMd_mf_mid(Long md_mf_mid) {
		this.md_mf_mid = md_mf_mid;
	}

	public Long getMd_fd_mid() {
		return md_fd_mid;
	}

	public void setMd_fd_mid(Long md_fd_mid) {
		this.md_fd_mid = md_fd_mid;
	}

	public String getMetafield() {
		return metafield;
	}

	public void setMetafield(String metafield) {
		this.metafield = metafield;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
