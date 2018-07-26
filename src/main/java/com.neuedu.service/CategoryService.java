package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;

public interface CategoryService {

	/**添加类别*/
    public  boolean addCategory(Category category);	
    /**查询类别*/
    public  List<Category> findAll();
    /**修改类别*/
    public  boolean  updateCategory(Category category);
    /**删除类别*/
    public  boolean deleteCategory(int id);
    /**根据id查询类别信息*/
    public  Category  findCategoryById(int id);
    /*
     * 分页获取数据
     */
    public PageModel<Category> findByPage(Integer pageNo, Integer pageSize);
}
