package com.neuedu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtils {
    static SqlSessionFactory sf=null;
    static{
        String resource="mybatis-config.xml";
        Reader reader=null;
        try{
            reader= Resources.getResourceAsReader(resource);
            //获取工厂
             sf=new SqlSessionFactoryBuilder().build(reader);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public static SqlSession getSqlSession() {
        return sf.openSession();
    }


}
