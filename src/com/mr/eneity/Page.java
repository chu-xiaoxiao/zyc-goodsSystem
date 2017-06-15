package com.mr.eneity;

import java.util.List;


/**
 * @author YuChen Zhang
 *
 */
public class Page<T> {
	private Integer currentPage;
	private Integer sieze;
	private Integer allPage;
	private List<T> lists;
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Page(Integer currentPage, Integer sieze, Integer allPage,
			List<T> lists) {
		super();
		this.currentPage = currentPage;
		this.sieze = sieze;
		this.allPage = allPage;
		this.lists = lists;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getAllPage() {
		return allPage;
	}
	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	
	public Integer getSieze() {
		return sieze;
	}
	public void setSieze(Integer sieze) {
		this.sieze = sieze;
	}
	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", sieze=" + sieze
				+ ", allPage=" + allPage + ", lists=" + lists + "]";
	}
	
	
}
