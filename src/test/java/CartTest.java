import com.neuedu.dao.CartDao;
import com.neuedu.dao.impl.jdbc.mybatis.CartMybatisImpl;
import com.neuedu.entity.Cart;

import org.junit.Test;

import java.util.List;

public class CartTest{
    @Test
    public void testfindAllCar(){
        CartDao cartDao=new CartMybatisImpl();
        List<Cart> list=cartDao.findAllCart();
        System.out.println(list);

    }

}
