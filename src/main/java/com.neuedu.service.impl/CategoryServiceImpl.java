package com.neuedu.service.impl;

import java.util.List;

import com.neuedu.dao.CategoryDao;
import com.neuedu.dao.ProductDao;
import com.neuedu.dao.impl.jdbc.CategoryDaoImpl;
import com.neuedu.dao.impl.jdbc.ProductDaoImpl;
import com.neuedu.dao.impl.jdbc.mybatis.CategoryMybatisImpl;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	CategoryDao categoryDao=new CategoryMybatisImpl();
	
	
	@Override
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.addCategory(category);
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.updateCategory(category);
	}

	@Override
	public boolean deleteCategory(int id) {
		// TODO Auto-generated method stub
		return categoryDao.deleteCategory(id);
	}

	@Override
	public Category findCategoryById(int id) {
		// TODO Auto-generated method stub
		return categoryDao.findById(id);
	}

	@Override
	public PageModel<Category> findByPage(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return categoryDao.findCategoryByPage(pageNo, pageSize);
	}

}
