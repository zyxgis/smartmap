package com.smartmap.systemManage.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="Sys_Resource")
public class Resource {

	@Id
    @GeneratedValue
    private Long id;
    
    /**资源名称*/
    @Column(length=64)
    private String name;
    
    /**资源代码*/
    @Column(length=64)
    private String code;
    
    /**资源描述*/
    @Column(length=256)
    private String description;
    
    /**资源URL*/
    @Column(length=256)
    private String url;
    
    /**资源排序*/
    @Column(name = "sortOrder")
    private Long sortOrder;
    
    /**上级资源*/
    @ManyToOne
    @JoinColumn(name="parentId")
    private Resource parent;
    
    /**下级资源*/
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="parentId")
    @OrderBy(value = "sortOrder ASC")
    private Set<Resource> children = new HashSet<Resource>();
        
    /**
     * 拥有的操作
     */
    @OneToMany(mappedBy="resource",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<Operator> operators = new HashSet<Operator>();
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public Set<Resource> getChildren() {
		return children;
	}

	public void setChildren(Set<Resource> children) {
		this.children = children;
	}

	public Set<Operator> getOperators() {
		return operators;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

}
