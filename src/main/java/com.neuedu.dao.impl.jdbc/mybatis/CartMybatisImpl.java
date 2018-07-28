package com.neuedu.dao.impl.jdbc.mybatis;

import com.neuedu.dao.CartDao;
import com.neuedu.entity.Cart;
import com.neuedu.entity.PageModel;
import com.neuedu.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartMybatisImpl implements CartDao {
    @Override
    public boolean addCart(Cart cart) {
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        sqlSession.insert("com.neuedu.entity.Cart.insertCart",cart);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    @Override
    public boolean deleteCart(int id) {
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        sqlSession.selectOne("com.neuedu.entity.Cart.deleteCar",id);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    @Override
    public boolean updataeCart(Cart cart) {
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("productNum",cart.getProductNum());
        map.put("id",cart.getId());
        sqlSession.update("com.neuedu.entity.Cart.updataeCar",map);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    @Override
    public List<Cart> findAllCart() {
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        List<Cart> list=sqlSession.selectList("com.neuedu.entity.Cart.findAllCart");
        sqlSession.close();
        return list;
    }

    @Override
    public int getCartNum() {
        return 0;
    }

    @Override
    public boolean updateCart(int id, int num) {
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("productNum",num);
        map.put("id",id);

        sqlSession.update("com.neuedu.entity.Cart.updataeCar",map);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    @Override
    public void clearCart() {

    }

    @Override
    public Cart findCartById(int id) {
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        Cart cart=sqlSession.selectOne("com.neuedu.entity.Cart.findCartById",id);
        sqlSession.close();
        return cart;
    }

    @Override
    public PageModel<Cart> findCartByPage(int pageNo, int pageSize) {

    SqlSession sqlSession=MyBatisUtils.getSqlSession();
        //获取总记录数
    int totalCount=sqlSession.selectOne("com.neuedu.entity.Cart.findTotalCount");
        //2.查询某页面的数据
     Map<String,Object> map=new HashMap<String,Object>();
        map.put("offset",pageNo);
        map.put("pageSize",pageSize);
        List<Cart>list=sqlSession.selectList("com.neuedu.entity.Cart.findCartByPage",map);
        PageModel pageModel=new PageModel();
        pageModel.setCurrentPage(pageNo);
        pageModel.setData(list);
        pageModel.setTotalPage((totalCount%pageSize)==0?totalCount/pageSize:(totalCount/pageSize)+1);


        return pageModel;
    }
}
