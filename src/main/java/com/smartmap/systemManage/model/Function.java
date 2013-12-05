package com.smartmap.systemManage.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 功能
 */
@Entity
@Table(name="Sys_Function")
public class Function implements Serializable {

		@Id
	    @GeneratedValue
	    private Long id;
	    
	    /**编码*/
	    @Column(length=32)
	    private String code;
	    
	    /**名称*/
	    @Column(length=32)
	    private String name;

	    /**
	     * 拥有的操作
	     */
	    @OneToMany(mappedBy="function",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	    private Set<Operator> operators = new HashSet<Operator>();
	    
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Set<Operator> getOperators() {
			return operators;
		}

		public void setOperators(Set<Operator> operators) {
			this.operators = operators;
		}
	    
		
	    
	    
}
