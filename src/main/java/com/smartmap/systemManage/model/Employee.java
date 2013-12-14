package com.smartmap.systemManage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 职工
 */
@Entity
@Table(name="SysEmployee")
public class Employee {
	@Id
    @GeneratedValue
    private Long id;
	
	/**职工名称*/
    @Column(length=64)
    private String employeeName;
	
    /**职工编码*/
    @Column(length=64)
    private String code;
    
    /**性别*/
    @Column(length=4)
    private String gender;
    
    /**邮箱*/
    @Column(length=32)
    private String email;
    
    	
    /**手机号码*/
    @Column(length=20)
    private String mobileNumber;
    
    
    /**身份证*/
    @Column(length=32)
    private String idCard;
    
    /**银行卡*/
    @Column(length=32)
    private String bankCode;
    
    
    /**出生日期*/
    @Column
	@Temporal(TemporalType.DATE)
    private Date birthday;
    
    /**家庭住址*/
    @Column(length=32)
    private String homeAddress;
    
    /**用户*/    
    @Column
    private Long userId;
    
    @Transient
    private String userName;
    
    /**组织*/
    @Column
    private Long organizationId;
    
    @Transient
    private String organizationName;
        
   
    /**职位*/    
    @Column
    private Long dutyId;

    @Transient
    private String dutyName;
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


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
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


	public String getIdCard() {
		return idCard;
	}


	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}


	public String getBankCode() {
		return bankCode;
	}


	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getHomeAddress() {
		return homeAddress;
	}


	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Long getDutyId() {
		return dutyId;
	}


	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}


	public String getDutyName() {
		return dutyName;
	}


	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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
    
	
}
