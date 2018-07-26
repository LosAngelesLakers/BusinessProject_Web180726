package com.neuedu.service;

import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.entity.UserOrder;

public interface OrderService {

	/**
	 * �û��µ�
	 * */
	boolean  createOrder(UserOrder userorder);
	
	/**
	 * 
	 * ���ɶ������order_no
	 * */
  long  generateOrderNo();
  
  public PageModel<UserOrder> findByPage(Integer pageNo, Integer pageSize);
}
