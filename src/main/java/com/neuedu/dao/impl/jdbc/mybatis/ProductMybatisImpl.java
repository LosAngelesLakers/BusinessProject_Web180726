package com.neuedu.dao.impl.jdbc.mybatis;

import com.neuedu.dao.ProductDao;
import com.neuedu.entity.PageModel;
import com.neuedu.entity.Product;
import com.neuedu.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductMybatisImpl  implements ProductDao{


    @Override
    public boolean addProduct(Product product) {

        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        //4.调用Mybatis的api   5.读取配置文件

        sqlSession.insert("com.neuedu.entity.Product.insertProduct",product);
        sqlSession.commit();

        return true;
    }

    @Override
    public List<Product> findAll() {
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        List<Product> list= sqlSession.selectList("com.neuedu.entity.Product.findProduct");
        sqlSession.close();
        return list;
    }

    @Override
    public boolean updateProduct(Product product) {
        String resource="mybatis-config.xml";
        Reader reader=null;
        try {
            reader = Resources.getResourceAsReader(resource);
        }catch(IOException e){
            e.printStackTrace();
        }
        SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=sf.openSession();
        sqlSession.update("com.neuedu.entity.Product.updateProduct",product);
        sqlSession.commit();
        sqlSession.close();

        return true;
    }

    @Override
    public boolean deleteProduct(int id) {
     //读取配置文件
        String resource="mybatis-config.xml";
        Reader reader=null;
        try{
            reader=Resources.getResourceAsReader(resource);
        }catch(IOException e){
            e.printStackTrace();
        }
        //获取工厂
        SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(reader);
        //获取SqlSession
        SqlSession sqlSession=sf.openSession();
        //调用Api
        sqlSession.delete("com.neuedu.entity.Product.deleteProduct",id);
        //关闭SqlSession
        sqlSession.commit();
        sqlSession.close();


        return true;
    }

    @Override
    public Product findById(int id) {
        String resource="mybatis-config.xml";
        Reader reader=null;
        try{
            reader=Resources.getResourceAsReader(resource);
        }catch(IOException e){
            e.printStackTrace();
        }
        //获取工厂
        SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(reader);
        //获取SqlSession
        SqlSession sqlSession=sf.openSession();
        //调用Api
        Product p= sqlSession.selectOne("com.neuedu.entity.Product.findById",id);
        //
        sqlSession.close();


        return p;
    }

    @Override
    public PageModel<Product> findProductByPage(int pageNo, int pageSize) {
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        //1.查询总的记录数
        int totalCount=sqlSession.selectOne("com.neuedu.entity.Product.findTotalCount");
        //2.查询某页面的数据
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("offset",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Product> list=sqlSession.selectList("com.neuedu.entity.Product.findUserbyPage",map);

        PageModel<Product> pageModel=new PageModel<Product>();
        pageModel.setTotalPage((totalCount%pageSize)==0?totalCount/pageSize:(totalCount/pageSize)+1);
        pageModel.setData(list);
        pageModel.setCurrentPage(pageNo);
        return pageModel;
    }

    @Override
    public boolean reduceProductNum(int stock, int productNum, int productid) {
        return false;
    }
}
