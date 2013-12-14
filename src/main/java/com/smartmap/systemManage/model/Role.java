package com.smartmap.systemManage.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 角色
 */
@Entity
@Table(name="SysRole")
public class Role implements Serializable {

	@Id
    @GeneratedValue
    private Long id;
    
	/**角色代码*/
    @Column(length=64)
    private String code;
    
    /**角色名称*/
    @Column(length=64)
    private String roleName;
        
    /**所属组织*/
    private Long organizationId;
    
    @Transient
    private String organizationName;
    
    /**角色类别*/
    @Column(length=64)
    private String roleType;
    
    /**父角色*/
    @Column
    private Long parentId;
    
    @Transient
    private Set<Role> children = null;
    
    @Transient
    private Role parent = null;
    
    
    /**
     * 创建日期
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
	
    /**
     * 修改日期
     */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;	
    
	/**用户描述*/
    @Column(length=256)
    private String description;

    
    
    
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set<Role> getChildren() {
		return children;
	}

	public void setChildren(Set<Role> children) {
		this.children = children;
	}

	public Role getParent() {
		return parent;
	}

	public void setParent(Role parent) {
		this.parent = parent;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
    
    
}
