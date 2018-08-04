package com.neuedu.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.ProductServiceImpl;
import com.neuedu.utils.DBUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/view/product")
public class ProductController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ProductService  pService;

	public void setpService(ProductService pService) {
		this.pService = pService;
	}

    @Override
    public void init() throws ServletException {
        WebApplicationContext mWebApplicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        pService=(ProductService) mWebApplicationContext.getBean("pService");
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String operation=request.getParameter("operation");
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		if(operation.equals("1")) {
			addProduct(request,response);
		}else if(operation.equals("2")) {
			findAll(request,response);
		}else if(operation.equals("3")) {
			findProductById(request,response);
		}else if(operation.equals("4")) {
			deleteProduct(request,response);
		}else if(operation.equals("5")) {
			updateProduct(request,response);
		}
		
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	public  void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product=new Product();
		boolean result=false;
		String name=request.getParameter("pname");
		String desc=request.getParameter("pdesc");
		String rule=request.getParameter("rule");
		String image=request.getParameter("pimage");
		try {
			double price=Double.parseDouble(request.getParameter("price"));
			int stock=Integer.parseInt(request.getParameter("stock"));
			int category_id=Integer.parseInt(request.getParameter("category_id"));
			product.setName(name);
			product.setDesc(desc);
			product.setRule(rule);
			product.setImage(image);
			product.setPrice(price);
			product.setStock(stock);
			product.setCategory_id(category_id);
			result=addProduct(product);
			if(result) {
				System.out.println("��Ʒ��ӳɹ�");
				findAll(request,response);
			}else {
				System.out.println("��Ʒ���ʧ��");
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	/**�����Ʒ*/
    public  boolean addProduct(Product product) {
    	return pService.addProduct(product);
    }

    /**��ѯ��Ʒ
     * @throws IOException 
     * @throws ServletException */
    public  void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String pageNo=request.getParameter("pageNo");
    	String pageSize=request.getParameter("pageSize");
    	System.out.println(request.getParameter("pageNo"));
    	System.out.println("pageSize="+pageSize);
    	int _pageNo=1;
    	int _pageSize=5;
    	
    	try {
    		if(pageNo!=null&&pageSize!=null) {
    			_pageNo=Integer.parseInt(pageNo);
        	    _pageSize=Integer.parseInt(pageSize);
    		}
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    	
    	PageModel<Product> pageModel= pService.findByPage(_pageNo, _pageSize);
    	/*
    	 * �ڶ���products��List����
    	 * ����ѯ������Ʒ���Ϸ���request��������
    	 */
    	request.setAttribute("pageModel", pageModel);
    	request.getRequestDispatcher("showProductByPage.jsp").forward(request,response);

    	
    	/*String json=JSONArray.toJSONString(pageModel);
    	response.getWriter().print(json);
    	System.out.println(json);*/
    }
    
    /*
     * ����id��ѯ��Ʒ��Ϣ
     */
    public void findProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
    	id=Integer.parseInt(request.getParameter("id"));
    	Product product=pService.findProductById(id);
    	request.setAttribute("product", product);
    	if(product!=null) {//��ѯ�ɹ�
    		System.out.println(id+"��ѯ�ɹ�");
    		request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
    	}else {//��ѯʧ��
    		System.out.println(id+"��ѯʧ��");
    	}
    	
    }
    
 public  boolean  updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
	    Product product=new Product();
		boolean result=false;
		String name=request.getParameter("pname");
		String desc=request.getParameter("pdesc");
		String rule=request.getParameter("rule");
		String image=request.getParameter("pimage");
		try {
			double price=Double.parseDouble(request.getParameter("price"));
			System.out.println(request.getParameter("stock"));
			int stock=Integer.parseInt(request.getParameter("stock"));
			int id=Integer.parseInt(request.getParameter("id"));
			int category_id=Integer.parseInt(request.getParameter("category_id"));
			product.setId(id);
			product.setName(name);
			product.setDesc(desc);
			product.setRule(rule);
			product.setImage(image);
			product.setPrice(price);
			product.setStock(stock);
			product.setCategory_id(category_id);
			result=updateProduct(product);
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if(result) {
			System.out.println("��Ʒ�޸ĳɹ�");
			findAll(request,response);
		}else {
			System.out.println("��Ʒ�޸�ʧ��");
		}
    	return false;
    }
    /**�޸���Ʒ*/
    public  boolean  updateProduct(Product product) {
    	
    	return pService.updateProduct(product);
    }
    

	/**ɾ����Ʒ
     * @throws IOException 
     * @throws ServletException */
    public  boolean deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id=0;
    	try {
    		id=Integer.parseInt(request.getParameter("id"));
    		System.out.println(id);
    		boolean result=pService.deleteProduct(id);
    		if(result) {
    			System.out.println(id+"��Ʒ��ɾ��");
    			findAll(request,response);
    		}else {
    			System.out.println(id+"��Ʒʧ��");
    		}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    	
    	
    	
    	return pService.deleteProduct(id);
    }
    public Product findProductById(int id) {
    	return pService.findProductById(id);
    }

	
}
