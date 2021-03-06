package com.smartmap.systemManage.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SysOperate")
public class Operate {
	@Id
    @GeneratedValue
    private Long id;
       
	/**操作名称*/
    @Column(length=64)
    private String operateName;
        
    /**操作代码*/
    @Column
    private Long code;
    
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

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
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
    
    
}
