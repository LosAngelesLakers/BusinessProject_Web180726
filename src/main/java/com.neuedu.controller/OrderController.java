package com.neuedu.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.dao.ProductDao;
import com.neuedu.dao.impl.jdbc.ProductDaoImpl;
import com.neuedu.entity.Cart;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.entity.UserOrder;
import com.neuedu.service.OrderService;
import com.neuedu.service.impl.OrderServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/view/order")
public class OrderController extends HttpServlet{

	OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

    @Override
    public void init() throws ServletException {
        WebApplicationContext mWebApplicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        orderService=(OrderService) mWebApplicationContext.getBean("orderService");
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		System.out.println(operation);
		if(operation.equals("1")) {
			createOrder(request,response);
		}else if(operation.equals("2")) {
			findAllUserOrder(request,response);
		}else if(operation.equals("3")) {
			//findCartById(request,response);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
    /*
     * 点击下单按钮，自动创建订单
     */
	public  boolean  createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserOrder userorder=new UserOrder();
		boolean result=false;
		int user_id=0;
		int totalprice=0;
		try {
			user_id=Integer.parseInt(request.getParameter("id"));
			totalprice=Integer.parseInt(request.getParameter("totalprice"));
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		userorder.setOrder_no(System.currentTimeMillis());
		userorder.setUser_id(user_id);
		userorder.setPayment(totalprice);
		userorder.setPayment_type(0);
		userorder.setPostage(0);
		userorder.setStatus(0);
		userorder.setPayment_time(System.currentTimeMillis());
		System.out.println(System.currentTimeMillis());
		userorder.setSend_time(System.currentTimeMillis());
		result=createOrder(userorder);
		if(result) {
			System.out.println("****************订单创建成功***********************");
			findAllUserOrder(request,response);
		}
		
		
		
		return false;
	}
	
	
	public  boolean  createOrder(UserOrder userorder) {
		
		
		return orderService.createOrder(userorder);
		
	}
	
	 public  void findAllUserOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    	System.out.println("findAllUserOrder方法");
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
	    	
	    	PageModel<UserOrder> pageModel= orderService.findByPage(_pageNo, _pageSize);
	    	/*
	    	 * 第二个products是List集合
	    	 * 将查询出的商品集合放入request作用域中
	    	 */
	    	request.setAttribute("pageModel", pageModel);
	    	request.getRequestDispatcher("showUserOrder.jsp").forward(request,response);

	    }
	
	
	
	
	
}
