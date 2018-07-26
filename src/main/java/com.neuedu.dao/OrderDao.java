package com.neuedu.dao;

import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.entity.UserOrder;

public interface OrderDao {

	/**
	 * ��������
	 * */
	
   boolean  createOrder(UserOrder userorder);
   
   /**
    * ���ɶ���id
    * */
   int  generateOrderId();
   /*
	 * ��ҳ��ȡ����
	 */
  PageModel<UserOrder> findUserOrderByPage(int pageNo, int pageSize);
}
