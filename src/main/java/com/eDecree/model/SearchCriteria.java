package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "search_criteria")
public class SearchCriteria {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="search_criteria_seq")
	@SequenceGenerator(name="search_criteria_seq", sequenceName="search_criteria_seq", allocationSize=1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "FIELD")
	private Long metafield;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "OPERATOR")
	private String operator;
	
	@Column(name = "CRITERIA")
	private String criteria;
	
	@Column(name = "SEARCHTEXT")
	private String searchtext;
	
	@Column(name = "SQ_ID")
	private Long sq_id;
	
	@Transient
	private String metafieldname;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getMetafield() {
		return metafield;
	}

	public void setMetafield(Long metafield) {
		this.metafield = metafield;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}

	public Long getSq_id() {
		return sq_id;
	}

	public void setSq_id(Long sq_id) {
		this.sq_id = sq_id;
	}

	public String getMetafieldname() {
		return metafieldname;
	}

	public void setMetafieldname(String metafieldname) {
		this.metafieldname = metafieldname;
	}
	
		
	

}
