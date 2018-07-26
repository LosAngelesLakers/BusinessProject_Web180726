package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.dao.ProductDao;
import com.neuedu.dao.impl.jdbc.ProductDaoImpl;
import com.neuedu.entity.Cart;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.CartService;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.CartServiceImpl;
import com.neuedu.service.impl.ProductServiceImpl;
@WebServlet("/view/cart")
public class CartController extends HttpServlet{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    CartService cartService=new CartServiceImpl();
    ProductService  pService=new ProductServiceImpl();
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CartController类");
		String operation=request.getParameter("operation");
		System.out.println(operation);
		if(operation.equals("1")) {
			
			addCart(request,response);
		}else if(operation.equals("2")) {
			findAllCart(request,response);
		}else if(operation.equals("3")) {
			findCartById(request,response);
		}else if(operation.equals("4")) {
			deleteCart(request,response);
		}else if(operation.equals("5")) {
			updateCart(request,response);
		}else if(operation.equals("6")){
			findProductById(request, response);
		}
		
	}
	private void findCartById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=0;
    	id=Integer.parseInt(request.getParameter("id"));
    	Cart cart=cartService.findCartById(id);
    	request.setAttribute("cart", cart);
    	if(cart!=null) {//查询成功
    		System.out.println(id+"查询成功");
    		request.getRequestDispatcher("updateCart.jsp").forward(request, response);
    	}else {//查询失败
    		System.out.println(id+"查询失败");
    	}
		
	}
	public void findProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		try {
			id=Integer.parseInt(request.getParameter("id"));

	    	Product product=pService.findProductById(id);
	    	request.setAttribute("product", product);
	    	if(product!=null) {//查询成功
	    		System.out.println(id+"查询成功");
	    		request.getRequestDispatcher("addCart.jsp").forward(request, response);
	    	}else {//查询失败
	    		System.out.println(id+"查询失败");
	    	}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
    	
    	
    }
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	/**
	 * 添加购物车
	 **/
	public  void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CartController中的addCart方法");
		Cart cart=new Cart();
		boolean result=false;
		int productid=0;
		int productnum=0;
		int unit_price=0;
		int totalprice=0;
		try {
			productid=Integer.parseInt(request.getParameter("id"));
			productnum=Integer.parseInt(request.getParameter("productnum"));
			
			ProductController ppt=new ProductController();
			Product product=ppt.findProductById(productid);
			//该商品单价
			unit_price=(int) product.getPrice();
			totalprice=productnum*unit_price;
			
			System.out.println("单价="+unit_price);
			System.out.println("总价="+totalprice);
			int product_stock=product.getStock();       
			
            if(product!=null&&productnum<=product_stock) {
            	cart.setProduct(product);
            	cart.setProductid(product.getId());
    			cart.setProductNum(productnum);
    			cart.setUnit_price(unit_price);
    			cart.setTotalprice(totalprice);
    			System.out.println("Productid="+product.getId());
    			System.out.println("ProductNum="+productnum);
    			result=addCart(cart);
    			System.out.println("result="+result);
    			if(result) {
    				System.out.println("购物车添加成功"); 
    				ProductDao productDao=new ProductDaoImpl();
    				productDao.reduceProductNum(product_stock, productnum, productid);
    				product.setStock(product_stock-productnum);
    				System.out.println("product_stock-productnum="+(product_stock-productnum));
    				System.out.println("此时有数量"+product.getStock());
    				
    				
    				findAllCart(request, response);
    			}else {
    				System.out.println("商品添加失败");
    			}
            }else {
            	System.out.println("================商品库存不足===================");
            }
			
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
		
	
	public boolean  addCart(Cart cart) {
		
		return cartService.addCart(cart);
	}
	/**
	 * 删除
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	public boolean  deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
    	try {
    		id=Integer.parseInt(request.getParameter("id"));
    		System.out.println(id);
    		boolean result=cartService.deleteCart(id);
    		if(result) {
    			System.out.println(id+"购物车已删除");
    			findAllCart(request,response);
    		}else {
    			System.out.println(id+"购物车删除失败");
    		}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    	
		return cartService.deleteCart(id);
	}
	/**
	 * 修改购物车
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	public boolean  updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Cart cart=new Cart();
		    int id=0;
//		    int productid=0;
		    int productNum=0;
			boolean result=false;
            System.out.println(request.getParameter("productNum"));
			try {
				System.out.println("try块");
				id=Integer.parseInt((request.getParameter("id")));
//				productid=Integer.getInteger(request.getParameter("productid"));
				productNum=Integer.parseInt(request.getParameter("productNum"));
				 // System.out.println(productNum);
//				  cart.setId(id);
//				  cart.setProductid(productid);
				System.out.println(Integer.parseInt(request.getParameter("productNum")));
				System.out.println(Integer.parseInt(request.getParameter("id")));
				cart.setId(id);
				cart.setProductNum(productNum);
				System.out.println("id="+id);
				System.out.println("productNum="+productNum);
				result=updateCart(cart);
				System.out.println(cart.getProductNum());
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			if(result) {
				System.out.println("购物车修改成功");
				findAllCart(request,response);
			}else {
				System.out.println("购物车修改失败");
			}
	    	return false;
		
	}
	private boolean updateCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartService.updataeCart(cart);
	}
	/**
	 * 查询购物车
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	public void findAllCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String pageNo=request.getParameter("pageNo");
    	String pageSize=request.getParameter("pageSize");
    	System.out.println(request.getParameter("pageNo"));
    	System.out.println("pageSize="+pageSize);
    	int _pageNo=1;
    	int _pageSize=5;
    	
    	try {
    		if(pageNo!=null&&pageSize!=null) {
    			_pageNo=Integer.parseInt(pageNo);
        	    _pageSize=Integer.parseInt(pageSize);
    		}
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    	
    	PageModel<Cart> pageModel= cartService.findByPage(_pageNo, _pageSize);
    	/*
    	 * 第二个products是List集合
    	 * 将查询出的商品集合放入request作用域中
    	 */
    	request.setAttribute("pageModel", pageModel);
    	request.getRequestDispatcher("showCart.jsp").forward(request,response);
		
		
		
		
	  	/*List<Cart> carts= cartService.findAllCart();
    	
    	 * 第二个products是List集合
    	 * 将查询出的商品集合放入request作用域中
    	 
    	request.setAttribute("carts", carts);
    	request.getRequestDispatcher("showCart.jsp").forward(request, response);
		return cartService.findAllCart();*/
	}
	/**
	 * 获取购物车中商品数量
	 * */
	public int  getCartNum() {
		return cartService.getCartNum();
	};
	
	/**修改购物车商品数量
	 * @param  request  要修改的购物车的Id
	 * @param  response 修改后的数量
	 * */
//	public boolean  updateCart(HttpServletRequest request,HttpServletResponse response) {
//		return  cartService.updateCart(request, response);
//	};
}
