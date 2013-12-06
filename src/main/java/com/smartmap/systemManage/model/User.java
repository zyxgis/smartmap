package com.smartmap.systemManage.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 用户
 */
@Entity
@Table(name="SysUser")
public class User implements Serializable {

	@Id
    @GeneratedValue
    private Long id;
    
    /**账号*/
    @Column(length=32)
    private String loginUsername;
    
    /**密码*/
    @Column(length=32)
    private String loginPassword;
    
    /**姓名*/
    @Column(length=32)
    private String name;
    
    /**性别*/
    @Column(length=4)
    private String gender;
    
    /**邮箱*/
    @Column(length=32)
    private String email;
    
    	
    /**手机号码*/
    @Column(length=20)
    private String mobileNumber;
    
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

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
    
	public User() {
		super();
	}
}
