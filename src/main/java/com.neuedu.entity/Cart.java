package com.neuedu.entity;

import java.io.Serializable;

/**
 * 购物车实体类
 * */
public class Cart  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5117733379863943502L;
	private  int id;
	private int productid;
	private  Product  product;
	private  int  productNum;//商品数量
	private int unit_price;
	private int totalprice;
	
	public Cart(int id, int productid, Product product, int productNum, int unit_price, int totalprice) {
		super();
		this.id = id;
		this.productid = productid;
		this.product = product;
		this.productNum = productNum;
		this.unit_price = unit_price;
		this.totalprice = totalprice;
	}
	public Cart(int id,int productid, int productNum) {
		super();
		this.id = id;
		this.productid=productid;
		this.productNum = productNum;
	}
	public Cart() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product2) {
		this.product = product2;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	
	public int getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", productid=" + productid+", product=" + product + ", productNum=" + productNum +",unit_price="+unit_price+",unit_price="+totalprice+"]";
	}
	
	
	
	
	
	
}
