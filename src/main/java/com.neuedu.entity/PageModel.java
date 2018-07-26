package com.neuedu.entity;

import java.io.Serializable;
import java.util.List;

public class PageModel<T> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8229837662887549422L;
	//每页数据集合
	private List<T>  data;
   //总共有多少页
	private int totalPage;
	//
	private int currentPage;
	
	public PageModel() {
		super();
	}
	public PageModel(List<T> data,int totalPage,int currentPage) {
		super();
		this.data=data;
		this.totalPage=totalPage;
		this.currentPage=currentPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int current) {
		this.currentPage = current;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
