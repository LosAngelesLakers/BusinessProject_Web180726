package com.neuedu.dao.impl.jdbc.mybatis;

import com.neuedu.dao.AccountDao;
import com.neuedu.entity.Account;
import com.neuedu.entity.PageModel;
import com.neuedu.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class AccountMybatisImpl implements AccountDao {


    @Override
    public boolean addAccount(Account account) {
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        sqlSession.insert("com.neuedu.entity.Account.insertAccount",account);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    @Override
    public boolean deleteAccount(int accountid) {
        return false;
    }

    @Override
    public boolean updateAccount(Account account) {
        return false;
    }

    @Override
    public PageModel<Account> findAccountByPage(int pageNo, int pageSize) {
        return null;
    }
}
