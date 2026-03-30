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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "search_query")
public class SearchQuery {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="search_query_seq")
	@SequenceGenerator(name="search_query_seq", sequenceName="search_query_seq", allocationSize=1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "QUERY")
	private String query;
	
	@Column(name = "CREATED")
	private Date created;
	
	@Column(name = "UPDATED")
	private Date updated;
	
	@Column(name = "CREATED_BY")
	private Long created_by;
	
	@Column(name = "UPDATED_BY")
	private Long updated_by;
	
	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "SQ_ID",referencedColumnName="ID",insertable = false, updatable = false)	
	private List<SearchCriteria> criterias;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<SearchCriteria> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<SearchCriteria> criterias) {
		this.criterias = criterias;
	}

	

	
	
	

}
