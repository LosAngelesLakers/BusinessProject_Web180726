package com.neuedufilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Account;
import com.neuedu.service.ILoginService;
import com.neuedu.service.impl.LoginServiceImpl;

/**
 * Servlet Filter implementation class CheckLoginFilter
 */
@WebFilter("/view/*")
public class CheckLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckLoginFilter() {
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
	   System.out.println("CheckLoginFilter");
	   HttpServletRequest _request=(HttpServletRequest)request;
	   HttpServletResponse _response=(HttpServletResponse)response;
	   HttpSession session=_request.getSession();
	   System.out.println("Session的存活时间"+session.getMaxInactiveInterval());
	   //从session中取得"token"对应的值，即username+password+时间戳
	   Object o=session.getAttribute("token");
	   Object accobj=session.getAttribute("acc");
	   if(o!=null&&accobj!=null) {
		   String token=(String)o;
		   //比较会话域中的token与数据库中的
		   ILoginService loginService=new LoginServiceImpl();
		   Account acc=(Account)accobj;
		   String result_token=loginService.findTokenByAccountid(acc.getAccountId());
	       if(result_token!=null) {
	    	   if(token.equals(result_token)) {//有效的token
	    		   chain.doFilter(request, response);
	    		   return ;
	    	   }
	    	   
	       }
	   
	   }else {
		   //重定向
		   
		   _response.sendRedirect("http://localhost:8080/BusinessProject_Web/login.jsp");
	   }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
