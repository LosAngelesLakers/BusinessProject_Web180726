package com.neuedu.dao.impl.jdbc.mybatis;

import com.neuedu.dao.ILoginDao;
import com.neuedu.entity.Account;
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
        //1.∂¡»°≈‰÷√Œƒº˛
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
        return null;
    }

    @Override
    public void addToken(String token, Account acc) {

    }

    @Override
    public String findTokenByAccountid(int accountid) {
        return null;
    }
}
