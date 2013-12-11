package com.smartmap.systemManage.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SysOrganization")
public class Organization {
	@Id
    @GeneratedValue
    private Long id;
    
    /**组织名称*/
    @Column(length=64)
    private String organizationName;
    
    /**组织编码*/
    @Column(length=64)
    private String code;
   
    /**上级组织*/
    @Column
    private Long parentId;
    
    /**组织描述*/
    @Column(length=256)
    private String description;

       
    @Transient
    private Set<Organization> children = null;
    
    @Transient
    private Organization parent = null;
    
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Organization getParent() {
		return parent;
	}
	public void setParent(Organization parent) {
		this.parent = parent;
	}
	public Set<Organization> getChildren() {
		return children;
	}
	public void setChildren(Set<Organization> children) {
		this.children = children;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
