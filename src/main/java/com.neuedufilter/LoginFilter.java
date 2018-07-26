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
	//执行了chain.doFilter(request, response)，请求通过过滤器
		String username=null;
		String password=null;
	/*
	 * ServletRequest的request不能获取cookie	
	 */
		
		System.out.println("到达loginFilter过滤器");
		HttpServletRequest _request=(HttpServletRequest)request;
		HttpServletResponse _response=(HttpServletResponse)response;
		
		Cookie[] cookis=_request.getCookies();
		System.out.println("cookis="+cookis!=null);
		if(cookis!=null) {
			for (Cookie c : cookis) {
				//Cookie的构造方法包含两个参数(name,value)
				if(c.getName().equals("username")){
					//用户名
					username=c.getValue();
					
					System.out.println("======cookie username===="+username);
				}
				if(c.getName().equals("password")) {
					//密码
					password=c.getValue();
					System.out.println("======cookie password===="+password);
				}
				
			}
		}
		
		
		//从Cookie中获取用户名密码
		if(username!=null&&password!=null&&!username.equals("")&&!password.equals("")) {
			ILoginService loginService=new LoginServiceImpl();
			Account acc=loginService.doLogin(username, password);
			if(acc!=null) {//登录成功
				request.getRequestDispatcher("view/Succ.jsp").forward(request, response);
			}else {//登录失败
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
