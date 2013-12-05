package com.smartmap.gis.model.evolution;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="yj_mnjg")
public class Mnjg implements Serializable {
	private static final long serialVersionUID = -1308795024262635690L;

	//主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	//参考段面
	@Column(name="ckdm")
	private String ckdm;

	//参考段面X坐标
	@Column(name="ckdmx")
	private String ckdmx;

	//参考段面Y坐标
	@Column(name="ckdmy")
	private String ckdmy;
	
	//段面
	@Column(name="dm")
	private String dm;
	
	//段面里程值
	@Column(name="dmlcz")
	private String dmlcz;
	
	//段面X坐标
	@Column(name="dmx")
	private String dmx;
	
	//段面Y坐标
	@Column(name="dmy")
	private String dmy;
	
	//段面所在河流
	@Column(name="hl")
	private String hl;
	
	//浓度
	@Column(name="nd")
	private String nd;
	
	//时间
	@Column(name="sj")
	private String sj;
	
	//因子
	@Column(name="yz")
	private String yz;
	
	//申请ID
	@Column(name="sq_id")
	private String sqId;
	
	//大段面名称
	@Column(name="ddmmc")
	private String ddmmc;
	
	//河道名称
	@Column(name="hdmc")
	private String hdmc;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCkdm() {
		return ckdm;
	}


	public void setCkdm(String ckdm) {
		this.ckdm = ckdm;
	}


	public String getCkdmx() {
		return ckdmx;
	}


	public void setCkdmx(String ckdmx) {
		this.ckdmx = ckdmx;
	}


	public String getCkdmy() {
		return ckdmy;
	}


	public void setCkdmy(String ckdmy) {
		this.ckdmy = ckdmy;
	}


	public String getDm() {
		return dm;
	}


	public void setDm(String dm) {
		this.dm = dm;
	}


	public String getDmlcz() {
		return dmlcz;
	}


	public void setDmlcz(String dmlcz) {
		this.dmlcz = dmlcz;
	}


	public String getDmx() {
		return dmx;
	}


	public void setDmx(String dmx) {
		this.dmx = dmx;
	}


	public String getDmy() {
		return dmy;
	}


	public void setDmy(String dmy) {
		this.dmy = dmy;
	}


	public String getHl() {
		return hl;
	}


	public void setHl(String hl) {
		this.hl = hl;
	}


	public String getNd() {
		return nd;
	}


	public void setNd(String nd) {
		this.nd = nd;
	}


	public String getSj() {
		return sj;
	}


	public void setSj(String sj) {
		this.sj = sj;
	}


	public String getYz() {
		return yz;
	}


	public void setYz(String yz) {
		this.yz = yz;
	}


	public String getSqId() {
		return sqId;
	}


	public void setSqId(String sqId) {
		this.sqId = sqId;
	}


	public String getDdmmc() {
		return ddmmc;
	}


	public void setDdmmc(String ddmmc) {
		this.ddmmc = ddmmc;
	}


	public String getHdmc() {
		return hdmc;
	}


	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}


	public Mnjg() {
	}
}

