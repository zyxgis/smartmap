package com.smartmap.systemManage.model;

import java.sql.Timestamp;

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
    @Column(length=64)
    private String code;
    
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
    
    
}
