package com.neuedu.dao;

import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.entity.UserOrder;

public interface OrderDao {

	/**
	 * 创建订单
	 * */
	
   boolean  createOrder(UserOrder userorder);
   
   /**
    * 生成订单id
    * */
   int  generateOrderId();
   /*
	 * 分页获取数据
	 */
  PageModel<UserOrder> findUserOrderByPage(int pageNo, int pageSize);
}
