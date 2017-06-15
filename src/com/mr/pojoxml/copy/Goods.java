package com.mr.pojoxml.copy;

import java.util.Date;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private Short gid;
	private Gtype gtype;
	private String gname;
	private Short gnum;
	private Double gprice;
	private String glocation;
	private Date gprocedate;

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** full constructor */
	public Goods(Gtype gtype, String gname, Short gnum, Double gprice,
			String glocation, Date gprocedate) {
		this.gtype = gtype;
		this.gname = gname;
		this.gnum = gnum;
		this.gprice = gprice;
		this.glocation = glocation;
		this.gprocedate = gprocedate;
	}

	// Property accessors

	public Short getGid() {
		return this.gid;
	}

	public void setGid(Short gid) {
		this.gid = gid;
	}

	public Gtype getGtype() {
		return this.gtype;
	}

	public void setGtype(Gtype gtype) {
		this.gtype = gtype;
	}

	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public Short getGnum() {
		return this.gnum;
	}

	public void setGnum(Short gnum) {
		this.gnum = gnum;
	}

	public Double getGprice() {
		return this.gprice;
	}

	public void setGprice(Double gprice) {
		this.gprice = gprice;
	}

	public String getGlocation() {
		return this.glocation;
	}

	public void setGlocation(String glocation) {
		this.glocation = glocation;
	}

	public Date getGprocedate() {
		return this.gprocedate;
	}

	public void setGprocedate(Date gprocedate) {
		this.gprocedate = gprocedate;
	}

	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", gtype=" + gtype + ", gname=" + gname
				+ ", gnum=" + gnum + ", gprice=" + gprice + ", glocation="
				+ glocation + ", gprocedate=" + gprocedate + "]";
	}
	
}