package com.neuedu.dao.impl.jdbc.mybatis;

import com.neuedu.dao.CategoryDao;
import com.neuedu.entity.Category;
import com.neuedu.entity.PageModel;
import com.neuedu.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryMybatisImpl implements CategoryDao{
    @Override
    public boolean addCategory(Category category) {
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        sqlSession.insert("com.neuedu.entity.Category.addCategory",category);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }


    @Override
    public List<Category> findAll() {
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        List<Category> list=sqlSession.selectList("com.neuedu.entity.Category.findAll");
        sqlSession.close();

        return list;
    }

    @Override
    public boolean updateCategory(Category category) {
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        sqlSession.update("com.neuedu.entity.Category.updateCategory",category);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    @Override
    public boolean deleteCategory(int id) {
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        sqlSession.delete("com.neuedu.entity.Category.deleteCategory",id);
        sqlSession.commit();
        sqlSession.close();

        return true;
    }

    @Override
    public Category findById(int id) {
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        Category category=sqlSession.selectOne("com.neuedu.entity.Category.findById",id);
        System.out.println(category.getCreat_time());
        sqlSession.close();

        return category;
    }

    @Override
    public PageModel<Category> findCategoryByPage(int pageNo, int pageSize) {
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        //获取总的记录数
        int totalCount=sqlSession.selectOne("com.neuedu.entity.Category.findTotalCount");
        //获取某页面的数据
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("offset",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Category> list=sqlSession.selectList("com.neuedu.entity.Category.findCategorybyPage",map);

        //设置分页模型
        PageModel<Category> pageModel=new PageModel<Category>();
        pageModel.setTotalPage((totalCount%pageSize)==0?totalCount/pageSize:(totalCount/pageSize)+1);
        pageModel.setData(list);
        pageModel.setCurrentPage(pageNo);

        return pageModel;
    }
}
