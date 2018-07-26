package com.neuedu.service;

import com.neuedu.entity.Account;
import com.neuedu.entity.PageModel;

public interface AccountService {
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
