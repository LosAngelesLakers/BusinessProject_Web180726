package com.neuedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.dao.ProductDao;
import com.neuedu.dao.impl.jdbc.ProductDaoImpl;
import com.neuedu.entity.Cart;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.CartService;
import com.neuedu.service.ProductService;
import com.neuedu.service.impl.CartServiceImpl;
import com.neuedu.service.impl.ProductServiceImpl;
@WebServlet("/view/cart")
public class CartController extends HttpServlet{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    CartService cartService=new CartServiceImpl();
    ProductService  pService=new ProductServiceImpl();
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CartController��");
		String operation=request.getParameter("operation");
		System.out.println(operation);
		if(operation.equals("1")) {
			
			addCart(request,response);
		}else if(operation.equals("2")) {
			findAllCart(request,response);
		}else if(operation.equals("3")) {
			findCartById(request,response);
		}else if(operation.equals("4")) {
			deleteCart(request,response);
		}else if(operation.equals("5")) {
			updateCart(request,response);
		}else if(operation.equals("6")){
			findProductById(request, response);
		}
		
	}
	private void findCartById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=0;
    	id=Integer.parseInt(request.getParameter("id"));
    	Cart cart=cartService.findCartById(id);
    	request.setAttribute("cart", cart);
    	if(cart!=null) {//��ѯ�ɹ�
    		System.out.println(id+"��ѯ�ɹ�");
    		request.getRequestDispatcher("updateCart.jsp").forward(request, response);
    	}else {//��ѯʧ��
    		System.out.println(id+"��ѯʧ��");
    	}
		
	}
	public void findProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		try {
			id=Integer.parseInt(request.getParameter("id"));

	    	Product product=pService.findProductById(id);
	    	request.setAttribute("product", product);
	    	if(product!=null) {//��ѯ�ɹ�
	    		System.out.println(id+"��ѯ�ɹ�");
	    		request.getRequestDispatcher("addCart.jsp").forward(request, response);
	    	}else {//��ѯʧ��
	    		System.out.println(id+"��ѯʧ��");
	    	}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
    	
    	
    }
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	/**
	 * ��ӹ��ﳵ
	 **/
	public  void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CartController�е�addCart����");
		Cart cart=new Cart();
		boolean result=false;
		int productid=0;
		int productnum=0;
		int unit_price=0;
		int totalprice=0;
		try {
			productid=Integer.parseInt(request.getParameter("id"));
			productnum=Integer.parseInt(request.getParameter("productnum"));
			
			ProductController ppt=new ProductController();
			Product product=ppt.findProductById(productid);
			//����Ʒ����
			unit_price=(int) product.getPrice();
			totalprice=productnum*unit_price;
			
			System.out.println("����="+unit_price);
			System.out.println("�ܼ�="+totalprice);
			int product_stock=product.getStock();       
			
            if(product!=null&&productnum<=product_stock) {
            	cart.setProduct(product);
            	cart.setProductid(product.getId());
    			cart.setProductNum(productnum);
    			cart.setUnit_price(unit_price);
    			cart.setTotalprice(totalprice);
    			System.out.println("Productid="+product.getId());
    			System.out.println("ProductNum="+productnum);
    			result=addCart(cart);
    			System.out.println("result="+result);
    			if(result) {
    				System.out.println("���ﳵ��ӳɹ�"); 
    				ProductDao productDao=new ProductDaoImpl();
    				productDao.reduceProductNum(product_stock, productnum, productid);
    				product.setStock(product_stock-productnum);
    				System.out.println("product_stock-productnum="+(product_stock-productnum));
    				System.out.println("��ʱ������"+product.getStock());
    				
    				
    				findAllCart(request, response);
    			}else {
    				System.out.println("��Ʒ���ʧ��");
    			}
            }else {
            	System.out.println("================��Ʒ��治��===================");
            }
			
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
		
	
	public boolean  addCart(Cart cart) {
		
		return cartService.addCart(cart);
	}
	/**
	 * ɾ��
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	public boolean  deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
    	try {
    		id=Integer.parseInt(request.getParameter("id"));
    		System.out.println(id);
    		boolean result=cartService.deleteCart(id);
    		if(result) {
    			System.out.println(id+"���ﳵ��ɾ��");
    			findAllCart(request,response);
    		}else {
    			System.out.println(id+"���ﳵɾ��ʧ��");
    		}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    	
		return cartService.deleteCart(id);
	}
	/**
	 * �޸Ĺ��ﳵ
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	public boolean  updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Cart cart=new Cart();
		    int id=0;
//		    int productid=0;
		    int productNum=0;
			boolean result=false;
            System.out.println(request.getParameter("productNum"));
			try {
				System.out.println("try��");
				id=Integer.parseInt((request.getParameter("id")));
//				productid=Integer.getInteger(request.getParameter("productid"));
				productNum=Integer.parseInt(request.getParameter("productNum"));
				 // System.out.println(productNum);
//				  cart.setId(id);
//				  cart.setProductid(productid);
				System.out.println(Integer.parseInt(request.getParameter("productNum")));
				System.out.println(Integer.parseInt(request.getParameter("id")));
				cart.setId(id);
				cart.setProductNum(productNum);
				System.out.println("id="+id);
				System.out.println("productNum="+productNum);
				result=updateCart(cart);
				System.out.println(cart.getProductNum());
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			if(result) {
				System.out.println("���ﳵ�޸ĳɹ�");
				findAllCart(request,response);
			}else {
				System.out.println("���ﳵ�޸�ʧ��");
			}
	    	return false;
		
	}
	private boolean updateCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartService.updataeCart(cart);
	}
	/**
	 * ��ѯ���ﳵ
	 * @throws IOException 
	 * @throws ServletException 
	 * */
	public void findAllCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
    	
    	PageModel<Cart> pageModel= cartService.findByPage(_pageNo, _pageSize);
    	/*
    	 * �ڶ���products��List����
    	 * ����ѯ������Ʒ���Ϸ���request��������
    	 */
    	request.setAttribute("pageModel", pageModel);
    	request.getRequestDispatcher("showCart.jsp").forward(request,response);
		
		
		
		
	  	/*List<Cart> carts= cartService.findAllCart();
    	
    	 * �ڶ���products��List����
    	 * ����ѯ������Ʒ���Ϸ���request��������
    	 
    	request.setAttribute("carts", carts);
    	request.getRequestDispatcher("showCart.jsp").forward(request, response);
		return cartService.findAllCart();*/
	}
	/**
	 * ��ȡ���ﳵ����Ʒ����
	 * */
	public int  getCartNum() {
		return cartService.getCartNum();
	};
	
	/**�޸Ĺ��ﳵ��Ʒ����
	 * @param  request  Ҫ�޸ĵĹ��ﳵ��Id
	 * @param  response �޸ĺ������
	 * */
//	public boolean  updateCart(HttpServletRequest request,HttpServletResponse response) {
//		return  cartService.updateCart(request, response);
//	};
}
