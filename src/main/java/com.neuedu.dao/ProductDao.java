package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;

public interface ProductDao {

	 /**
	  * 添加商品
	  * */
	boolean  addProduct(Product product);
	/**
	 * 查看商品
	 * */
	List<Product> findAll();
	/**
	 * 修改商品
	 * */
	boolean  updateProduct(Product product);
	/**
	 * 删除商品
	 * */
	boolean  deleteProduct(int id);
	
	/**根据id查询商品*/
	Product  findById(int id);
	
	/*
	 * 分页获取数据
	 */
   PageModel<Product> findProductByPage(int pageNo, int pageSize);
	
	public  boolean  reduceProductNum(int stock, int productNum, int productid);
}
