package com.eDecree.model;


public class FolderPermission {
	
	
	private Long id;
	
	private Integer type;
	
	private Long userId;
	
	private Long value;
	
	private Integer status;
	
	private String name;
	
	private String folderPath;
	
	private Integer isParent;
	
	private Boolean assignAll;
	
	public Long getId() {
		return id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAssignAll() {
		return assignAll;
	}

	public void setAssignAll(Boolean assignAll) {
		this.assignAll = assignAll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	
	
}
