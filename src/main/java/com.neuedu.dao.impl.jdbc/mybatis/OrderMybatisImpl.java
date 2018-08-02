package com.neuedu.dao.impl.jdbc.mybatis;

import com.neuedu.dao.OrderDao;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.UserOrder;
import com.neuedu.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMybatisImpl implements OrderDao{
    @Override
    public boolean createOrder(UserOrder userorder) {
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        sqlSession.insert("com.neuedu.entity.UserOrder.createOrder",userorder);
        sqlSession.commit();
        sqlSession.close();

        return true;
    }

    @Override
    public int generateOrderId() {
        return 0;
    }

    @Override
    public PageModel<UserOrder> findUserOrderByPage(int pageNo, int pageSize) {
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        //获取总的记录数
        int totalCount=sqlSession.selectOne("com.neuedu.entity.UserOrder.findTotalCount");

        //查询某页面的数据
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("offset",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<UserOrder> list=sqlSession.selectList("com.neuedu.entity.UserOrder.findUserOrderByPage",map);

        //获取分页模型
        PageModel<UserOrder> pageModel=new PageModel<UserOrder>();
        pageModel.setTotalPage((totalCount%pageSize)==0?totalCount/pageSize:(totalCount/pageSize)+1);
        pageModel.setData(list);
        pageModel.setCurrentPage(pageNo);

        return pageModel;
    }
}
