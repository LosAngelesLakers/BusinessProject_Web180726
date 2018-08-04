package com.neuedu.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.neuedu.dao.AccountDao;
import com.neuedu.entity.Account;
import com.neuedu.entity.PageModel;
import com.neuedu.utils.DBUtils;

public class AccountDaoImpl implements AccountDao {

	@Override
	public boolean addAccount(Account account) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		try {
			conn=DBUtils.getConnection();
		    String sql="INSERT into account(username,password,ip,sex) VALUE(?,?,?,?)";
			st=conn.prepareStatement(sql);
			st.setString(1,account.getUsername());
			st.setString(2, account.getPassword());
			st.setString(3, account.getIp());
			st.setString(4, account.getSex());
			st.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DBUtils.close(conn, st);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return false;
	}

	@Override
	public boolean deleteAccount(int accountid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageModel<Account> findAccountByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
