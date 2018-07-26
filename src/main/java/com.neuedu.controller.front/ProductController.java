package com.neuedu.controller.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.neuedu.entity.Product;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/front/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=request.getParameter("method");
		
		ProductService pService=new ProductServiceImpl();
		List<Product> products=pService.findAll();
		Gson gson=new Gson();
		String json=gson.toJson(products);
		
		PrintWriter write=response.getWriter();
		write.print(method+"("+json+")");
		System.out.println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
