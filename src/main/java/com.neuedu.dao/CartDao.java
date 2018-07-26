package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Cart;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;

/**
 * 
 * 
 * ���ﳵ
 * */
public interface CartDao {

	/**
	 * ��ӹ��ﳵ
	 **/
	boolean  addCart(Cart cart);
	/**
	 * ɾ��������Ϣ��Id
	 * */
	boolean  deleteCart(int id);
	/**
	 * �޸Ĺ��ﳵ
	 * */
	boolean  updataeCart(Cart cart);
	/**
	 * ��ѯ���ﳵ
	 * */
	List<Cart> findAllCart();
	
	/**
	 * ��ȡ���ﳵ����Ʒ����
	 * */
	int  getCartNum();
	
	/**�޸Ĺ��ﳵ��Ʒ����
	 * @param  id  Ҫ�޸ĵ���Ʒ��Id
	 * @param  num �޸ĺ������
	 * */
	boolean  updateCart(int id, int num);
	

	/**
	 * ��չ��ﳵ
	 * */
	void  clearCart() ;
	
	
	Cart findCartById(int id);
	
	public PageModel<Cart> findCartByPage(int pageNo, int pageSize);
	
}
