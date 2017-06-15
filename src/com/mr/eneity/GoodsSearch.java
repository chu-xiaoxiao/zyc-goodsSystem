package com.mr.eneity;

import com.mr.pojoxml.Gtype;

/**
 * @author YuChen Zhang
 *
 */
public class GoodsSearch {
	private String gname;
	private Double max;
	private Double min;
	private Gtype gtype;
	private Integer imgCount;
	public GoodsSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GoodsSearch(String gname, Double max, Double min, Gtype gtype) {
		super();
		this.gname = gname;
		this.max = max;
		this.min = min;
		this.gtype = gtype;
	}
	
	public GoodsSearch(String gname, Double max, Double min, Gtype gtype,
			Integer imgCount) {
		super();
		this.gname = gname;
		this.max = max;
		this.min = min;
		this.gtype = gtype;
		this.imgCount = imgCount;
	}

	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}

	public Gtype getGtype() {
		return gtype;
	}

	public void setGtype(Gtype gtype) {
		this.gtype = gtype;
	}

	public Integer getImgCount() {
		return imgCount;
	}

	public void setImgCount(Integer imgCount) {
		this.imgCount = imgCount;
	}

	@Override
	public String toString() {
		return "GoodsSearch [gname=" + gname + ", max=" + max + ", min=" + min
				+ ", gtype=" + gtype + ", imgCount=" + imgCount + "]";
	}
}
