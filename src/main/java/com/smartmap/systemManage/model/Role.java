package com.smartmap.systemManage.model;

import java.io.Serializable;

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
import javax.persistence.Table;

/**
 * 角色
 */
@Entity
@Table(name="Sys_Role")
public class Role implements Serializable {

	@Id
    @GeneratedValue
    private Long id;
    
    /**角色名称*/
    @Column(length=64)
    private String name;
    
    /**角色代码*/
    @Column(length=64)
    private String code;
    
    /**角色描述*/
    @Column(length=256)
    private String description;
    
    /**超级角色*/
    @Column(name = "superRole")
    private boolean superRole = false;
    
    /**
     * 被分配给的用户
     */
    @ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<User>();
    
    
    /**
     * 可以对访问的资源进行的操作
     */
    @ManyToMany(mappedBy="roles")
    private Set<Operator> operators = new HashSet<Operator>();
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


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


	public boolean getSuperRole() {
		return superRole;
	}


	public void setSuperRole(boolean superRole) {
		this.superRole = superRole;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	public Set<Operator> getOperators() {
		return operators;
	}


	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

}
