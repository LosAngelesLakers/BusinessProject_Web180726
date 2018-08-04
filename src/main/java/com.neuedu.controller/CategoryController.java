package com.neuedu.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.CategoryService;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.CategoryServiceImpl;
import com.neuedu.service.impl.ProductServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/view/category")
public class CategoryController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CategoryService  cService;

	public void setcService(CategoryService cService) {
		this.cService = cService;
	}

	@Override
	public void init() throws ServletException {
		WebApplicationContext mWebApplicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		cService=(CategoryService) mWebApplicationContext.getBean("cService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation=request.getParameter("operation");
		System.out.println("doGet����");
		System.out.println(operation);
		if(operation.equals("1")) {
			addCategory(request,response);
		}else if(operation.equals("2")) {
			System.out.println("operation");
			findAll(request,response);
		}else if(operation.equals("3")) {
			findCategoryById(request,response);
		}else if(operation.equals("4")) {
			deleteCategory(request,response);
		}else if(operation.equals("5")) {
			updateCategory(request,response);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	public  void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category category=new Category();
		boolean result=false;
		String name=request.getParameter("name");
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			int parent_id=Integer.parseInt(request.getParameter("parent_id"));
			int status=Integer.parseInt(request.getParameter("status"));
			int sort_order=Integer.parseInt(request.getParameter("sort_order"));

			category.setId(id);
			category.setParent_id(parent_id);
			category.setName(name);
			category.setStatus(status);
			category.setSort_order(sort_order);

			/*Date creatDate = new Date();
			Date date = new Date(creatDate.getTime());



			category.setCreat_time(now());
			category.setUpdate_time(date);*/
			System.out.println("addCategory");

			result=addCategory(category);
            System.out.println(category.getName());
			if(result) {
				System.out.println("�����ӳɹ�");
				findAll(request, response);
			}else {
				System.out.println("������ʧ��");
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	/**������*/
    public  boolean addCategory(Category category) {
    	return cService.addCategory(category);
    }	
	
    /**��ѯ���
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
    	
    	PageModel<Category> pageModel= cService.findByPage(_pageNo, _pageSize);
    	/*
    	 * �ڶ���products��List����
    	 * ����ѯ������Ʒ���Ϸ���request��������
    	 */
    	request.setAttribute("pageModel", pageModel);
    	request.getRequestDispatcher("showCategory.jsp").forward(request,response);
    	/*List<Category> categorys= cService.findAll();
    	
    	 * �ڶ���products��List����
    	 * ����ѯ������Ʒ���Ϸ���request��������
    	 
    	request.setAttribute("categorys", categorys);
    	request.getRequestDispatcher("showCategory.jsp").forward(request, response);
    	return cService.findAll();*/
    }
    
    
	/**ɾ�����
     * @throws IOException 
     * @throws ServletException */
    public  boolean deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id=0;
    	try {
    		id=Integer.parseInt(request.getParameter("id"));
    		System.out.println(id);
    		boolean result=cService.deleteCategory(id);
    		if(result) {
    			System.out.println(id+"�����ɾ��");
    			findAll(request,response);
    		}else {
    			System.out.println(id+"���ɾ��ʧ��");
    		}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    	
    	return cService.deleteCategory(id);
    }
    public void findCategoryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id=0;
    	id=Integer.parseInt(request.getParameter("id"));
    	Category category=cService.findCategoryById(id);
    	request.setAttribute("category", category);
    	if(category!=null) {//��ѯ�ɹ�
    		System.out.println(id+"��ѯ�ɹ�");
    		request.getRequestDispatcher("updateCategory.jsp").forward(request, response);
    	}else {//��ѯʧ��
    		System.out.println(id+"��ѯʧ��");
    	}
    	
    }
    
    public  boolean  updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 System.out.println("updateCategory����");
    	Category category=new Category();
		boolean result=false;
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			int parent_id=Integer.parseInt(request.getParameter("parent_id"));
			String name=request.getParameter("name");
			int status=Integer.parseInt(request.getParameter("status"));
			int sort_order=Integer.parseInt(request.getParameter("sort_order"));
			String creat_time=request.getParameter("creat_time");
			String update_time=request.getParameter("update_time");
			
			
			category.setId(id);
			category.setParent_id(parent_id);
			category.setName(name);
			category.setStatus(status);
			category.setSort_order(sort_order);


			result=updateCategory(category);
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if(result) {
			System.out.println("����޸ĳɹ�");
			findAll(request,response);
		}else {
			System.out.println("��Ʒ�޸�ʧ��");
		}
    	return false;
    }
    /**�޸����*/
    public  boolean  updateCategory(Category category) {
    	
    	return cService.updateCategory(category);
    }
    
    
    
    
    
	
	

}
