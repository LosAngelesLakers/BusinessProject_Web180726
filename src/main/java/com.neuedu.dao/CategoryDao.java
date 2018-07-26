package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;

public interface CategoryDao {

	 /**
	  * ������
	  * */
	boolean  addCategory(Category category);
	/**
	 * �鿴���
	 * */
	List<Category> findAll();
	/**
	 * �޸����
	 * */
	boolean  updateCategory(Category category);
	/**
	 * ɾ�����
	 * */
	boolean  deleteCategory(int id);
	
	/**����id��ѯ���*/
	Category  findById(int id);
	
	/*
	 * ��ҳ��ȡ����
	 */
	public PageModel<Category> findCategoryByPage(int pageNo, int pageSize);
	
	
	
}
