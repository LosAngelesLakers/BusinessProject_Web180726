package com.neuedu.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.CartDao;
import com.neuedu.dao.ProductDao;
import com.neuedu.entity.Cart;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.utils.DBUtils;

public class CartDaoImpl implements CartDao {

	ProductDao productDao=new ProductDaoImpl();
	@Override
	public boolean addCart(Cart cart) {
		// TODO Auto-generated method stub
		System.out.println("jdbc包的addCart方法");
		Connection conn=null;
		PreparedStatement st=null;
		try {
			conn=DBUtils.getConnection();
		
			
			String  sql="insert into cart(productid,productnum,unit_price,totalprice) values ("+cart.getProduct().getId()+","+cart.getProductNum()+","+cart.getUnit_price()+","+cart.getTotalprice()+")";
			System.out.println(sql);
			st=conn.prepareStatement(sql);
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
	public boolean deleteCart(int id) {
		// TODO Auto-generated method stub
		
		
		Connection conn=null;
		Statement st=null;
		try {
			conn=DBUtils.getConnection();
			st=conn.createStatement();
			String  sql="delete from cart where id="+id+"";
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
	public boolean updataeCart(Cart cart) {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtils.getConnection();
			String  sql="update cart set productnum=? where id=?";
			pst=conn.prepareStatement(sql);
			System.out.println(sql);
			pst.setInt(1,cart.getProductNum());
			System.out.println("jdbc"+cart.getProductNum());
			pst.setInt(2, cart.getId());
			pst.execute();
			DBUtils.close(conn, pst);
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public List<Cart> findAllCart() {
		// TODO Auto-generated method stub
		
	List<Cart> carts=new ArrayList<Cart>();
		
		Connection conn=null;
		Statement st=null;
		try {
			conn=DBUtils.getConnection();
			st=conn.createStatement();
			String  sql="select id,productid,productnum,unit_price,totalprice from  cart";
			System.out.println(sql);
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
			 int  id= rs.getInt("id");	
			 int  productid=rs.getInt("productid");
			 System.out.println("productid="+productid);
			 int num=rs.getInt("productNum");
			 int unit_price=rs.getInt("unit_price");
			 int totalprice=rs.getInt("totalprice");
			 
			 Cart cart=new Cart();
			 cart.setId(id);
			 cart.setProductNum(num);
			 cart.setProductid(productid);
			 cart.setProduct(productDao.findById(productid));
			 cart.setUnit_price(unit_price);
			 cart.setTotalprice(totalprice);
			 
			 
			 
			 carts.add(cart);
			 
			 
			}
			
			return carts;
			
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
		
		
		return null;
	}

	@Override
	public int getCartNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateCart(int id, int num) {
		// TODO Auto-generated method stub
		Connection conn=null;
		Statement st=null;
		try {
			conn=DBUtils.getConnection();
			st=conn.createStatement();
			String  sql="update cart set productnum="+num+" where id="+id+"";
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
	public void clearCart() {
		// TODO Auto-generated method stub

	}

	@Override
	public Cart findCartById(int id) {
		// TODO Auto-generated method stub
		Cart cart=null;
		Connection conn=null;
		PreparedStatement st=null;
		try {
			conn=DBUtils.getConnection();
			
			String  sql="select id,productid,productnum,unit_price,totalprice from  cart where id=?";
			st=conn.prepareStatement(sql);
			st.setInt(1, id);
			System.out.println(sql);
			ResultSet rs=st.executeQuery();
			
			if(rs.first()) {
				 cart=new Cart();
				 int  _id= rs.getInt("id");	
				 int productid=rs.getInt("productid");
				 int productNum=rs.getInt("productNum");
				 int unit_price=rs.getInt("unit_price");
				 int totalprice=rs.getInt("totalprice");
				 cart.setId(_id);
				 cart.setProductid(productid);
				 cart.setProductNum(productNum);
                 cart.setUnit_price(unit_price);
                 cart.setTotalprice(totalprice);
			}
			
			
			
			return cart;
			
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
		
      return cart;
		

	}

	@Override
	public PageModel<Cart> findCartByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		
		PageModel<Cart> pageModel=new PageModel<Cart>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			
			//查询总的记录数
			String sqlcount="select count(id) from cart";
			System.out.println(sqlcount);
			pst=conn.prepareStatement(sqlcount);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				int totalCount=rs.getInt(1);//总的记录数
				//计算共多少页
			int totalPage=(totalCount%pageSize)==0?totalCount/pageSize:totalCount/pageSize+1;   
			pageModel.setTotalPage(totalPage);
				
			}
			String sql="select id,productid,productnum,unit_price,totalprice from  cart limit ?,?";
			System.out.println(sql);
			pst=conn.prepareStatement(sql);
			pst.setInt(1,(pageNo-1)*pageSize);
			System.out.println("(pageNo-1)*pageSize="+(pageNo-1)*pageSize);
			pst.setInt(2,pageSize);
			rs=pst.executeQuery();
			List<Cart> list=new ArrayList<Cart>();
			while(rs.next()) {
				
				Cart cart=new Cart();
				cart.setId(rs.getInt("id"));
				cart.setProductid(rs.getInt("productid"));
				cart.setProductNum(rs.getInt("productnum"));
				cart.setUnit_price(rs.getInt("unit_price"));
				cart.setTotalprice(rs.getInt("totalprice"));
				
				list.add(cart);
				pageModel.setData(list);
			}
			pageModel.setCurrentPage(pageNo);
			//获取总的记录数
			String totalPage_sql="select count(id) from cart";
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
