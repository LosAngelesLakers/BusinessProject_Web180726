package com.neuedu.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.ProductDao;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.utils.DBUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		PreparedStatement st=null;
		try {
			conn=DBUtils.getConnection();
			
			String  sql="insert into product(name,pdesc,price,rule,image,stock,category_id) values (?,?,?,?,?,?,?)";
			st=conn.prepareStatement(sql);
			//占位符赋值
			st.setString(1, product.getName());
			st.setString(2, product.getDesc());
			st.setDouble(3, product.getPrice());
			st.setString(4, product.getRule());
			st.setString(5, product.getImage());
			st.setInt(6, product.getStock());
			st.setInt(7, product.getCategory_id());
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
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		List<Product> products=new ArrayList<Product>();
		
		Connection conn=null;
		PreparedStatement st=null;
		try {
			conn=DBUtils.getConnection();
			
			String  sql="select id,name,pdesc,price,rule ,image,stock,category_id from  product";
			st=conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
			 int  id= rs.getInt("id");	
			 String  name=rs.getString("name");
			 String pdesc=rs.getString("pdesc");
			 double price=rs.getDouble("price");
			 String rule=rs.getString("rule");
			 String  image=rs.getString("image");
			 int stock=rs.getInt("stock");
			 int category_id=rs.getInt("category_id");
			 Product product=new Product(id,name,pdesc,price,rule,image,stock,category_id);
//			 product.setStock(stock);
			 products.add(product);
			}
			return products;
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
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pst=null;
		
		try {
			 conn=DBUtils.getConnection();
			 String sql="update product set name=?,pdesc=?,price=?,rule=?,image=?,stock=?,category_id=? where id=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1, product.getName());
			 System.out.println(product.getName());
			 pst.setString(2,product.getDesc());
			 pst.setDouble(3, product.getPrice());
			 pst.setString(4, product.getRule());
			 pst.setString(5, product.getImage());
			 System.out.println("dao="+product.getStock());
			 pst.setInt(6, product.getStock());
			 pst.setInt(7, product.getCategory_id());
			 pst.setInt(8, product.getId());
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
	public boolean deleteProduct(int id) {
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtils.getConnection();
			String  sql="delete from  product where id=?";
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
	public Product findById(int id) {
		// TODO Auto-generated method stub
				Product product=null;
				Connection conn=null;
				PreparedStatement st=null;
				try {
					conn=DBUtils.getConnection();
					
					String  sql="select id,name,pdesc,price,rule ,image,stock,category_id from  product where id=?";
					st=conn.prepareStatement(sql);
					st.setInt(1, id);
					System.out.println(sql);
					ResultSet rs=st.executeQuery();
					
					if(rs.first()) {
						 product=new Product();
						 int  _id= rs.getInt("id");	
						 String  name=rs.getString("name");
						 String pdesc=rs.getString("pdesc");
						 double price=rs.getDouble("price");
						 String rule=rs.getString("rule");
						 String  image=rs.getString("image");
						 int stock=rs.getInt("stock");
						 int category_id=rs.getInt("category_id");
						 product.setId(_id);
                         product.setName(name);
                         product.setPrice(price);
                         product.setDesc(pdesc);
                         product.setRule(rule);
                         product.setImage(image);
                         product.setStock(stock);
                         product.setCategory_id(category_id);
					}
					
					
					
					return product;
					
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
				
		return product;
	}

	@Override
	public PageModel<Product> findProductByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageModel<Product> pageModel=new PageModel<Product>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			
			//查询总的记录数
			String sqlcount="select count(id) from product";
			pst=conn.prepareStatement(sqlcount);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				int totalCount=rs.getInt(1);//总的记录数
				//计算共多少页
			int totalPage=(totalCount%pageSize)==0?totalCount/pageSize:totalCount/pageSize+1;   
			pageModel.setTotalPage(totalPage);
				
			}
			
			
			
			String sql="select id,name,pdesc,price,rule,image,stock,category_id from product limit ?,?";
			System.out.println(sql);
			pst=conn.prepareStatement(sql);
			pst.setInt(1,(pageNo-1)*pageSize);
			System.out.println("(pageNo-1)*pageSize="+(pageNo-1)*pageSize);
			pst.setInt(2,pageSize);
			rs=pst.executeQuery();
			List<Product> list=new ArrayList<Product>();
			while(rs.next()) {
				
				Product product=new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDesc(rs.getString("pdesc"));
				product.setPrice(rs.getDouble("price"));
				product.setRule(rs.getString("rule"));
				product.setImage(rs.getString("image"));
				product.setStock(rs.getInt("stock"));
				product.setCategory_id(rs.getInt("category_id"));
				list.add(product);
				pageModel.setData(list);
			}
			pageModel.setCurrentPage(pageNo);
			//获取总的记录数
			String totalPage_sql="select count(id) from product";
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
/*
 * 加入购物车之后剩余的商品数量
 */
	@Override
	public boolean reduceProductNum(int stock, int productNum,int productid) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pst=null;
		int s=stock-productNum;
		try {
			 conn=DBUtils.getConnection();
			 String sql="update product set stock=? where id=?";
			 pst=conn.prepareStatement(sql);
			 pst.setInt(1, s);
			 pst.setInt(2, productid);
			 pst.execute();
			 DBUtils.close(conn, pst);
			 return true;
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return false;
	}

}
