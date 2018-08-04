package com.neuedu.dao.impl.jdbc.mybatis;

import com.neuedu.dao.ILoginDao;
import com.neuedu.entity.Account;
import com.neuedu.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class LoginMybatisImpl implements ILoginDao{
    @Override
    public Account doLogin(String _username, String _password) {
        //1.读取配置文件
        String resource="mybatis-config.xml";
        // 2.
        Reader reader=null;
        SqlSession session;
        try{
            reader= Resources.getResourceAsReader(resource);
        }catch(IOException e){
            e.printStackTrace();
        }
        SqlSessionFactory sqlMapper= new SqlSessionFactoryBuilder().build(reader);
        session=sqlMapper.openSession();
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",_username);
        map.put("password",_password);
        Account account=session.selectOne("com.neuedu.entity.Account.findByUserAndPassword",map);
        System.out.println(account);
        session.close();
        return account;
    }

    @Override
    public void addToken(String token, Account acc) {
       SqlSession sqlSession= MyBatisUtils.getSqlSession();
       Map<String,Object> map=new HashMap<String,Object>();
        map.put("token",token);
        map.put("accountid",acc.accountId);
        sqlSession.update("com.neuedu.entity.Account.addToken",map);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("修改成功");
    }

    @Override
    public String findTokenByAccountid(int accountid) {
       SqlSession  sqlSession=MyBatisUtils.getSqlSession();
        String token=sqlSession.selectOne("com.neuedu.entity.Account.findTokenByAccountid",accountid);

       sqlSession.close();

      return token;
    }
}
