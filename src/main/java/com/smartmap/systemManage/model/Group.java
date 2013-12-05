package com.smartmap.systemManage.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="SysGroup")
public class Group {
	@Id
    @GeneratedValue
    private Long id;
    
    /**组织名称*/
    @Column(length=64)
    private String groupName;
    
    /**组织编码*/
    @Column(length=64)
    private String code;
    
    /**父组织*/
    @Column
    private Long parentId;
    
    /**
     * 创建日期
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createTime;
	
    /**
     * 修改日期
     */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastUpdate;	
    
	/**用户描述*/
    @Column(length=256)
    private String description;

   
    @Transient
    private Set<Group> children = null;
    
    @Transient
    private Group parent = null;
    
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set<Group> getChildren() {
		return children;
	}

	public void setChildren(Set<Group> children) {
		this.children = children;
	}

	public Group getParent() {
		return parent;
	}

	public void setParent(Group parent) {
		this.parent = parent;
	}
    
     
}
