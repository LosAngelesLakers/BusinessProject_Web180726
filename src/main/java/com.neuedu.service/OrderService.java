package com.neuedu.service;

import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.entity.UserOrder;

public interface OrderService {

	/**
	 * 用户下单
	 * */
	boolean  createOrder(UserOrder userorder);
	
	/**
	 * 
	 * 生成订单编号order_no
	 * */
  long  generateOrderNo();
  
  public PageModel<UserOrder> findByPage(Integer pageNo, Integer pageSize);
}
