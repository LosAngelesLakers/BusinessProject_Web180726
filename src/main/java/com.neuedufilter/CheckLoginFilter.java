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
	   System.out.println("Session�Ĵ��ʱ��"+session.getMaxInactiveInterval());
	   //��session��ȡ��"token"��Ӧ��ֵ����username+password+ʱ���
	   Object o=session.getAttribute("token");
	   Object accobj=session.getAttribute("acc");
	   if(o!=null&&accobj!=null) {
		   String token=(String)o;
		   //�ȽϻỰ���е�token�����ݿ��е�
		   ILoginService loginService=new LoginServiceImpl();
		   Account acc=(Account)accobj;
		   String result_token=loginService.findTokenByAccountid(acc.getAccountId());
	       if(result_token!=null) {
	    	   if(token.equals(result_token)) {//��Ч��token
	    		   chain.doFilter(request, response);
	    		   return ;
	    	   }
	    	   
	       }
	   
	   }else {
		   //�ض���
		   
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
