package com.neuedu.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neuedu.dao.OrderDao;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.entity.UserOrder;
import com.neuedu.utils.DBUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public boolean createOrder(UserOrder userOrder) {
		// TODO Auto-generated method stub
		Connection conn=null;
		Statement st=null;
		try {
			conn=DBUtils.getConnection();
			
			st=conn.createStatement();
		
			String  sql="insert into userorder(order_no,user_id,shipping_id,payment,payment_type,postage,status,create_time)"
					+ " values ("+userOrder.getOrder_no()+","+userOrder.getUser_id()+","+userOrder.getShipping_id()+","+userOrder.getPayment()+","+userOrder.getPayment_type()+","+userOrder.getPostage()+","+userOrder.getStatus()+",now())";
			System.out.println(sql);

			st.execute(sql);
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
	public int generateOrderId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageModel<UserOrder> findUserOrderByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		
		PageModel<UserOrder> pageModel=new PageModel<UserOrder>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			
			//查询总的记录数
			String sqlcount="select count(id) from userorder";
			pst=conn.prepareStatement(sqlcount);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				int totalCount=rs.getInt(1);//总的记录数
				//计算共多少页
			int totalPage=(totalCount%pageSize)==0?totalCount/pageSize:totalCount/pageSize+1;   
			pageModel.setTotalPage(totalPage);
				
			}
			
			String sql="select id,order_no,user_id,shipping_id,payment,payment_type,postage,status,create_time from userorder limit ?,?";
			System.out.println(sql);
			pst=conn.prepareStatement(sql);
			pst.setInt(1,(pageNo-1)*pageSize);
			System.out.println("(pageNo-1)*pageSize="+(pageNo-1)*pageSize);
			pst.setInt(2,pageSize);
			rs=pst.executeQuery();
			List<UserOrder> list=new ArrayList<UserOrder>();
			while(rs.next()) {
				
				UserOrder userorder=new UserOrder();
				userorder.setId(rs.getInt("id"));
				userorder.setOrder_no(rs.getLong("order_no"));
				userorder.setUser_id(rs.getInt("user_id"));
				userorder.setShipping_id(rs.getInt("shipping_id"));
				userorder.setPayment(rs.getDouble("payment"));
				userorder.setPayment_type(rs.getInt("payment_type"));
				userorder.setPostage(rs.getInt("postage"));
				userorder.setStatus(rs.getInt("status"));
				//userorder.setPayment_time(rs.getLong("payment_time"));
				//System.out.println("数据库中取出的的payment_time="+rs.getLong("payment_time"));
				//userorder.setSend_time(rs.getLong("send_time"));
				//userorder.setEnd_time(rs.getLong("end_time"));
				//userorder.setClose_time(rs.getLong("close_time"));
				userorder.setCreate_time(rs.getDate("create_time"));
				//userorder.setUpdate_time(rs.getLong("update_time"));
					
				list.add(userorder);
				pageModel.setData(list);
			}
			pageModel.setCurrentPage(pageNo);
			//获取总的记录数
			String totalPage_sql="select count(id) from userorder";
			pst=conn.prepareStatement(totalPage_sql);
			rs=pst.executeQuery();
			if(rs.first()) {
				int totalCount=rs.getInt(1);
				int totalPage=((totalCount%pageSize)==0?totalCount/pageSize:totalCount/pageSize+1);
				pageModel.setTotalPage(totalPage);
			}
			return pageModel;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return pageModel;
		

	}

}
