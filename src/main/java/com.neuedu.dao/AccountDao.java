package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Account;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;

public interface AccountDao {
 /*
  *�����û� 
 */
	boolean  addAccount(Account account);
 /*
  * ɾ���û�	
  */
	boolean  deleteAccount(int accountid);
	/*
	 * �����û�
	 */
	boolean  updateAccount(Account account);
	/*
	 * ��ѯ�û�
	 */
	  PageModel<Account> findAccountByPage(int pageNo, int pageSize);
	
	
}
