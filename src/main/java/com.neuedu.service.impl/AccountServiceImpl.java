package com.neuedu.service.impl;

import com.neuedu.dao.AccountDao;
import com.neuedu.dao.impl.jdbc.AccountDaoImpl;
import com.neuedu.entity.Account;
import com.neuedu.entity.PageModel;
import com.neuedu.service.AccountService;

public class AccountServiceImpl implements AccountService {
    AccountDao accountDao=new AccountDaoImpl();
	@Override
	public boolean addAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.addAccount(account);
	}

	@Override
	public boolean deleteAccount(int accountid) {
		// TODO Auto-generated method stub
		return accountDao.deleteAccount(accountid);
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.updateAccount(account);
	}

	@Override
	public PageModel<Account> findAccountByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return accountDao.findAccountByPage(pageNo, pageSize);
	}

}
