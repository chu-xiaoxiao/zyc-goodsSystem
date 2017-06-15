package com.mr.pojoxml;

/**
 * Imgs entity. @author MyEclipse Persistence Tools
 */

public class Imgs implements java.io.Serializable {

	// Fields

	private Integer imgid;
	private Goods goods;
	private String imgpath;

	// Constructors

	/** default constructor */
	public Imgs() {
	}

	/** full constructor */
	public Imgs(Goods goods, String imgpath) {
		this.goods = goods;
		this.imgpath = imgpath;
	}

	// Property accessors

	public Integer getImgid() {
		return this.imgid;
	}

	public void setImgid(Integer imgid) {
		this.imgid = imgid;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getImgpath() {
		return this.imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

}