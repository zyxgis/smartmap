package com.smartmap.systemManage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 用户
 */
@Entity
@Table(name="Sys_User")
public class User implements Serializable {

	@Id
    @GeneratedValue
    private Long id;
    
    /**账号*/
    @Column(length=32)
    private String account;
    
    /**密码*/
    @Column(length=32)
    private String password;
    
    /**姓名*/
    @Column(length=32)
    private String name;
    
    /**性别*/
    @Column(length=4)
    private String gender;
    
    /**邮箱*/
    @Column(length=32)
    private String email;
    
    /**IP*/
    @Column(name = "ip", length = 50, nullable = false)
    private String ip;
	
    /**是否绑定IP*/
	@Column(name = "isBind")
    private Boolean isBind;
	
    /**手机号码*/
    @Column(length=20)
    private String mobileNumber;
    /**
     * 创建日期
     */
    @Column(name = "createDate")
	@Temporal(TemporalType.DATE)
    private Date createDate;
	
    /**
     * 修改日期
     */
	@Column(name = "lastModifyDate")
	@Temporal(TemporalType.DATE)
    private Date lastModifyDate;
	
    /**
     * 所属组织
     */
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="organizationId")
    private Organization organization;
    
    /**
     * 拥有的角色
     */
    @ManyToMany 
    @JoinTable(name="Sys_User_Role",joinColumns=@JoinColumn(name="userId"),
            inverseJoinColumns=@JoinColumn(name="roleId"))
    private Set<Role> roles = new HashSet<Role>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Boolean getIsBind() {
		return isBind;
	}

	public void setIsBind(boolean isBind) {
		this.isBind = isBind;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
    
    
    
}
