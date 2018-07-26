package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Account;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;

public interface AccountDao {
 /*
  *增加用户 
 */
	boolean  addAccount(Account account);
 /*
  * 删除用户	
  */
	boolean  deleteAccount(int accountid);
	/*
	 * 更新用户
	 */
	boolean  updateAccount(Account account);
	/*
	 * 查询用户
	 */
	  PageModel<Account> findAccountByPage(int pageNo, int pageSize);
	
	
}
