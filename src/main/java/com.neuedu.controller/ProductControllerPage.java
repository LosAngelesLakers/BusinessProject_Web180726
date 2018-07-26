package com.neuedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.ProductServiceImpl;
@WebServlet("/view/Bypage")
public class ProductControllerPage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNo=request.getParameter("pageNo");
		ProductService productService=new ProductServiceImpl();
		PageModel<Product> pageModel=productService.findByPage(Integer.parseInt(pageNo), 4);
		request.setAttribute("pageModel", pageModel);
	    request.getRequestDispatcher("showProductByPage.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	

}
