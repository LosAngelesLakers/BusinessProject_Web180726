import com.neuedu.dao.ILoginDao;
import com.neuedu.dao.impl.jdbc.mybatis.LoginMybatisImpl;
import org.junit.Test;

public class UserTest {

    @Test
    public  void testLogin(){
        ILoginDao loginDao=new LoginMybatisImpl();
        loginDao.doLogin("admin","21232f297a57a5a743894a0e4a801fc3");

    }

}
