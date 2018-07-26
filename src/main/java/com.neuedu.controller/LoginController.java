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
import com.neuedu.service.ILoginService;
import com.neuedu.service.impl.LoginServiceImpl;
import com.neuedu.utils.MD5Utils;


/**
 * 负责接收用户用户名、密码
 * 登录的业务逻辑：用户第一次登录，会去会话域中查找有没有token，此时是没有token的，
 *然后再从Cookie中获取用户名与密码，如果Cookie中没有，再从请求参数中获取。
 * 
 * 当第一次登录之后，再次登录时，首先去查看会话域，若有token将取得它，将其与数据库中
 * 保存的token相比较，相同时将会通过过滤器。
 * 可以使用后台其他资源的.jsp进行免登录，直接进入对应的界面。
 * 
 * 
 * 
 * */
@WebServlet("/login.do")
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ILoginService  loginService=new LoginServiceImpl();
		
		String  username=request.getParameter("username");
		String  password=request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		//用MD5将password加密
		if(username!=null&&password!=null&&!username.equals("")&&!password.equals("")) {
			Account acc=loginService.doLogin(username, MD5Utils.GetMD5Code(password));
			if(acc!=null) {//登录成功
				Cookie cookie=new Cookie("username",username);
				cookie.setMaxAge(7*24*3600);
				response.addCookie(cookie);
				
				Cookie pwd_cookie=new Cookie("password",MD5Utils.GetMD5Code(password));
				response.addCookie(pwd_cookie);
				
				//生成token，存放到数据库中
				long time=System.currentTimeMillis();
				String token=MD5Utils.GetMD5Code(username+password+time);
				System.out.println("token="+token);
				loginService.addToken(token,acc);
				//token放到会话域中
				HttpSession session=request.getSession();
				session.setAttribute("token", token);
				session.setAttribute("acc", acc);
				System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
				//request.getRequestDispatcher("view/Succ.jsp").forward(request, response);
				response.sendRedirect("http://localhost:8080/BusinessProject_Web/view/Succ.jsp");
			}else {//登录失败
				request.getRequestDispatcher("view/fail.jsp").forward(request, response);
			}
		}else {//登录失败
			request.getRequestDispatcher("view/fail.jsp").forward(request, response);
		}
		
		
		/*if(acc!=null) {//登录成功
			Cookie cookie=new Cookie("username",username);
			cookie.setMaxAge(7*24*3600);
			response.addCookie(cookie);
			
			Cookie pwd_cookie=new Cookie("password",MD5Utils.GetMD5Code(password));
			response.addCookie(pwd_cookie);
			
			//生成token，存放到数据库中
			long time=System.currentTimeMillis();
			String token=MD5Utils.GetMD5Code(username+password+time);
			System.out.println("token="+token);
			loginService.addToken(token,acc);
			//token放到会话域中
			HttpSession session=request.getSession();
			session.setAttribute("token", token);
			session.setAttribute("acc", acc);
			request.getRequestDispatcher("view/Succ.jsp").forward(request, response);
		}else {//登录失败
			request.getRequestDispatcher("view/fail.jsp").forward(request, response);
		}*/
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request,response);
	}

	
}
