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
 * ��������û��û���������
 * ��¼��ҵ���߼����û���һ�ε�¼����ȥ�Ự���в�����û��token����ʱ��û��token�ģ�
 *Ȼ���ٴ�Cookie�л�ȡ�û��������룬���Cookie��û�У��ٴ���������л�ȡ��
 * 
 * ����һ�ε�¼֮���ٴε�¼ʱ������ȥ�鿴�Ự������token��ȡ���������������ݿ���
 * �����token��Ƚϣ���ͬʱ����ͨ����������
 * ����ʹ�ú�̨������Դ��.jsp�������¼��ֱ�ӽ����Ӧ�Ľ��档
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
		//��MD5��password����
		if(username!=null&&password!=null&&!username.equals("")&&!password.equals("")) {
			Account acc=loginService.doLogin(username, MD5Utils.GetMD5Code(password));
			if(acc!=null) {//��¼�ɹ�
				Cookie cookie=new Cookie("username",username);
				cookie.setMaxAge(7*24*3600);
				response.addCookie(cookie);
				
				Cookie pwd_cookie=new Cookie("password",MD5Utils.GetMD5Code(password));
				response.addCookie(pwd_cookie);
				
				//����token����ŵ����ݿ���
				long time=System.currentTimeMillis();
				String token=MD5Utils.GetMD5Code(username+password+time);
				System.out.println("token="+token);
				loginService.addToken(token,acc);
				//token�ŵ��Ự����
				HttpSession session=request.getSession();
				session.setAttribute("token", token);
				session.setAttribute("acc", acc);
				System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
				//request.getRequestDispatcher("view/Succ.jsp").forward(request, response);
				response.sendRedirect("http://localhost:8080/BusinessProject_Web/view/Succ.jsp");
			}else {//��¼ʧ��
				request.getRequestDispatcher("view/fail.jsp").forward(request, response);
			}
		}else {//��¼ʧ��
			request.getRequestDispatcher("view/fail.jsp").forward(request, response);
		}
		
		
		/*if(acc!=null) {//��¼�ɹ�
			Cookie cookie=new Cookie("username",username);
			cookie.setMaxAge(7*24*3600);
			response.addCookie(cookie);
			
			Cookie pwd_cookie=new Cookie("password",MD5Utils.GetMD5Code(password));
			response.addCookie(pwd_cookie);
			
			//����token����ŵ����ݿ���
			long time=System.currentTimeMillis();
			String token=MD5Utils.GetMD5Code(username+password+time);
			System.out.println("token="+token);
			loginService.addToken(token,acc);
			//token�ŵ��Ự����
			HttpSession session=request.getSession();
			session.setAttribute("token", token);
			session.setAttribute("acc", acc);
			request.getRequestDispatcher("view/Succ.jsp").forward(request, response);
		}else {//��¼ʧ��
			request.getRequestDispatcher("view/fail.jsp").forward(request, response);
		}*/
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request,response);
	}

	
}
