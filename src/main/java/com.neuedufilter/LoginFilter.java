package com.neuedufilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.Account;
import com.neuedu.service.ILoginService;
import com.neuedu.service.impl.LoginServiceImpl;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/login.jsp")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	//ִ����chain.doFilter(request, response)������ͨ��������
		String username=null;
		String password=null;
	/*
	 * ServletRequest��request���ܻ�ȡcookie	
	 */
		
		System.out.println("����loginFilter������");
		HttpServletRequest _request=(HttpServletRequest)request;
		HttpServletResponse _response=(HttpServletResponse)response;
		
		Cookie[] cookis=_request.getCookies();
		System.out.println("cookis="+cookis!=null);
		if(cookis!=null) {
			for (Cookie c : cookis) {
				//Cookie�Ĺ��췽��������������(name,value)
				if(c.getName().equals("username")){
					//�û���
					username=c.getValue();
					
					System.out.println("======cookie username===="+username);
				}
				if(c.getName().equals("password")) {
					//����
					password=c.getValue();
					System.out.println("======cookie password===="+password);
				}
				
			}
		}
		
		
		//��Cookie�л�ȡ�û�������
		if(username!=null&&password!=null&&!username.equals("")&&!password.equals("")) {
			ILoginService loginService=new LoginServiceImpl();
			Account acc=loginService.doLogin(username, password);
			if(acc!=null) {//��¼�ɹ�
				request.getRequestDispatcher("view/Succ.jsp").forward(request, response);
			}else {//��¼ʧ��
				chain.doFilter(request, response);
			}
			
		}else {
			System.out.println("chain.doFilter(request, response);");
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
