package com.neuedu.controller.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.neuedu.entity.Account;
import com.neuedu.service.ILoginService;
import com.neuedu.service.impl.LoginServiceImpl;
import com.neuedu.utils.MD5Utils;



/**
 * Servlet implementation class FrontLoginController
 */
@WebServlet("/front/login.do")
public class FrontLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=======================================");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String method=request.getParameter("method");
		System.out.println("username="+username);
		System.out.println("password="+password);
		ILoginService loginService=new LoginServiceImpl();
		Account acc=loginService.doLogin(username, MD5Utils.GetMD5Code(password));
		/*"username":"张三"
		 * "passwors":
		 *"ip":"123"
		 *""
		 */
		System.out.println(acc!=null);
		if(acc!=null) {//登陆成功
			/*
			 * 将java对象和json字符串的互相转换的两个
			 * gson.jar--->google
			 * jackson---->alibaba
			 */
			//将java对象转json字符串
			
			Gson gson=new Gson();
			String json=gson.toJson(acc);
			/*String json1=JSONArray.toJSONString(acc);*/
			//将json字符串转到java对象
			Account account=gson.fromJson(json, Account.class);
			System.out.println(account);
			
			//获取输出流
			PrintWriter write=response.getWriter();
			//调用js的success方法
			write.println(method+"("+json+")");
		    System.out.println(json);
		   /*System.out.println(json1);*/
		
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
