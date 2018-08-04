package com.neuedu.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.CategoryDao;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.utils.DBUtils;

public class CategoryDaoImpl implements CategoryDao{

	@Override
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		System.out.println("addCategory方法");
		try {
			conn=DBUtils.getConnection();
			
			String  sql="insert into category(parent_id,name,status,sort_order,creat_time,update_time) values (?,?,?,?,now(),now())";
			System.out.println(sql);
			st=conn.prepareStatement(sql);
			//占位符赋值
			st.setInt(1, category.getParent_id());
			st.setString(2, category.getName());
			st.setInt(3, category.getStatus());
			st.setInt(4, category.getSort_order());
			
			System.out.println(sql);
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
	public List<Category> findAll() {
		// TODO Auto-generated method stub
List<Category> categorys=new ArrayList<Category>();
		
		Connection conn=null;
		PreparedStatement st=null;
		try {
			conn=DBUtils.getConnection();
			
			String  sql="select id,parent_id,name,status,sort_order,creat_time,update_time from  category";
			st=conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
			 int  id= rs.getInt("id");	
			 int parent_id=rs.getInt("parent_id");
			 String name=rs.getString("name");
			 int status=rs.getInt("status");
			 int sort_order=rs.getInt("sort_order");
			 Date creat_time=rs.getDate("creat_time");
			 Date update_time=rs.getDate("update_time");
			 
			 Category category=new Category(id,parent_id,name,status,sort_order,creat_time,update_time);
			 categorys.add(category);
			}
			return categorys;
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
	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pst=null;
		
		try {
			 conn=DBUtils.getConnection();
			 String sql="update category set parent_id=?,name=?,status=?,sort_order=? where id=?";
			 pst=conn.prepareStatement(sql);
			 pst.setInt(1, category.getParent_id());
			 pst.setString(2, category.getName());
			 pst.setInt(3, category.getStatus());
			 pst.setInt(4, category.getSort_order());
			 pst.setInt(5, category.getId());
			
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
	public boolean deleteCategory(int id) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtils.getConnection();
			String  sql="delete from  category where id=?";
			System.out.println(sql);
			pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public Category findById(int id) {
		// TODO Auto-generated method stub
		Category category=null;
		Connection conn=null;
		PreparedStatement st=null;
		try {
			conn=DBUtils.getConnection();
			
			String  sql="select id,parent_id,name,status,sort_order,creat_time,update_time from  category where id=?";
			st=conn.prepareStatement(sql);
			st.setInt(1, id);
			System.out.println(sql);
			ResultSet rs=st.executeQuery();
			
			if(rs.first()) {
				 category=new Category();
				 int  _id= rs.getInt("id");
				 int parent_id=rs.getInt("parent_id");
				 String name=rs.getString("name");
				 int status=rs.getInt("status");
				 int sort_order=rs.getInt("sort_order");
				 Date creat_time=rs.getDate("creat_time");
				 Date update_time=rs.getDate("update_time");
				 
				 category.setId(_id);
				 category.setParent_id(parent_id);
				 category.setName(name);
				 category.setStatus(status);
				 category.setSort_order(sort_order);
				 category.setCreat_time(creat_time);
				 category.setUpdate_time(update_time);
				 
			}
			return category;
			
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
		
     return category;
		

	}

	@Override
	public PageModel<Category> findCategoryByPage(int pageNo, int pageSize) {
		PageModel<Category> pageModel=new PageModel<Category>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			
			//查询总的记录数
			String sqlcount="select count(id) from category";
			System.out.println(sqlcount);
			pst=conn.prepareStatement(sqlcount);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				int totalCount=rs.getInt(1);//总的记录数
				//计算共多少页
			int totalPage=(totalCount%pageSize)==0?totalCount/pageSize:totalCount/pageSize+1;   
			pageModel.setTotalPage(totalPage);
				
			}
			
			
			
			String sql="select id,parent_id,name,status,sort_order,creat_time,update_time from  category limit ?,?";
			System.out.println(sql);
			pst=conn.prepareStatement(sql);
			pst.setInt(1,(pageNo-1)*pageSize);
			System.out.println("(pageNo-1)*pageSize="+(pageNo-1)*pageSize);
			pst.setInt(2,pageSize);
			rs=pst.executeQuery();
			List<Category> list=new ArrayList<Category>();
			while(rs.next()) {
				
				Category category=new Category();
				category.setId(rs.getInt("id"));
				category.setParent_id(rs.getInt("parent_id"));
				category.setName(rs.getString("name"));
				category.setStatus(rs.getInt("status"));
				category.setSort_order(rs.getInt("sort_order"));
				category.setCreat_time(rs.getDate("creat_time"));
				category.setUpdate_time(rs.getDate("update_time"));
				
				list.add(category);
				pageModel.setData(list);
			}
			pageModel.setCurrentPage(pageNo);
			//获取总的记录数
			String totalPage_sql="select count(id) from category";
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
