package com.neuedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Account;
import com.neuedu.entity.Product;
import com.neuedu.service.AccountService;
import com.neuedu.service.ILoginService;
import com.neuedu.service.impl.AccountServiceImpl;
import com.neuedu.service.impl.LoginServiceImpl;
import com.neuedu.utils.MD5Utils;


@WebServlet("/view/account")
public class AccountController extends HttpServlet{
     
	AccountService aService=new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if(operation.equals("1")) {
			System.out.println("AccountController");
			addAccount(request,response);
		}else if(operation.equals("2")) {
			//findAccount(request,response);
		}else if(operation.equals("3")) {
			//findAccountById(request,response);
		}else if(operation.equals("4")) {
			 //deleteAccount(request,response);
		}else if(operation.equals("5")) {
			//updateAccount(request,response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request,response);
	}

	public void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("addAccount方法");
		Account account=new Account();
		boolean result=false;
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String ip=request.getParameter("ip");
		String sex=request.getParameter("sex");
		//String token=request.getParameter("token");
		try {
			//int stock=Integer.parseInt(request.getParameter("stock"));
			account.setUsername(username);
			account.setPassword(MD5Utils.GetMD5Code(password));
			account.setIp(ip);
			account.setSex(sex);
			//account.setStock(stock);
			//account.setToken(token);
			
			
			result=addAccount(account);
			if(result) {
				System.out.println("用户添加成功");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else {
				System.out.println("用户添加失败");
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
	
	 public  boolean addAccount(Account account) {
	    	return aService.addAccount(account);
	    }
	
	
	
	
	
	
	
}
