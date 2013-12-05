package com.smartmap.gis.model.evaluate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jc_pjjg")
public class Pjjg implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;

	//主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	//监测断面编码
	@Column(name="jcdmbm")
	private String jcdmbm;

	//监测断面名称
	@Column(name="jcdmmc")
	private String jcdmmc;

	//监测断面类型
	@Column(name="jcdmlx")
	private String jcdmlx;
	
	//监测时间
	@Column(name="jcsj")
	private String jcsj;
	
	//水质类型
	@Column(name="szlx")
	private String szlx;
			
	//目标水质类型
	@Column(name="mbszlx")
	private String mbszlx;
	
	//段面经度坐标
	@Column(name="x")
	private String x;
	
	//段面纬度坐标
	@Column(name="y")
	private String y;
	
	//是否超标
	@Column(name="sfcb")
	private String sfcb;
	
	
	

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getJcdmbm() {
		return jcdmbm;
	}




	public void setJcdmbm(String jcdmbm) {
		this.jcdmbm = jcdmbm;
	}




	public String getJcdmmc() {
		return jcdmmc;
	}




	public void setJcdmmc(String jcdmmc) {
		this.jcdmmc = jcdmmc;
	}




	public String getJcdmlx() {
		return jcdmlx;
	}




	public void setJcdmlx(String jcdmlx) {
		this.jcdmlx = jcdmlx;
	}




	public String getJcsj() {
		return jcsj;
	}




	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}




	public String getSzlx() {
		return szlx;
	}




	public void setSzlx(String szlx) {
		this.szlx = szlx;
	}




	public String getMbszlx() {
		return mbszlx;
	}




	public void setMbszlx(String mbszlx) {
		this.mbszlx = mbszlx;
	}




	public String getX() {
		return x;
	}




	public void setX(String x) {
		this.x = x;
	}




	public String getY() {
		return y;
	}




	public void setY(String y) {
		this.y = y;
	}




	public String getSfcb() {
		return sfcb;
	}




	public void setSfcb(String sfcb) {
		this.sfcb = sfcb;
	}




	public Pjjg() {
		super();
	}	
	
}


	
