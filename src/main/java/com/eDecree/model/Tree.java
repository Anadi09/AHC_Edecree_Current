package com.eDecree.model;

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
import javax.persistence.Transient;

@Entity
@Table(name = "object_master")
public class Tree {			
		
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="MstrSeq")
	@SequenceGenerator(name="MstrSeq", sequenceName="MstrSeq", allocationSize=1)
	@Column(name = "om_id")
	private Long Id;
	
	@Column(name = "om_object_name")
	private String Name;	
	
	@Column(name = "om_parent_id")
	private Long parentid;	
	
	
	@Column(name = "om_rec_status")
	private Integer om_rec_status;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "om_parent_id")	
	private List<Tree> children;


	@Transient
	private Boolean checkbox;
	
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public Integer getOm_rec_status() {
		return om_rec_status;
	}

	public void setOm_rec_status(Integer om_rec_status) {
		this.om_rec_status = om_rec_status;
	}

	public Boolean getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(Boolean checkbox) {
		this.checkbox = checkbox;
	}

	
	
	
	
}
