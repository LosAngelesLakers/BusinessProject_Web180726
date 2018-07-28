import com.neuedu.dao.ProductDao;
import com.neuedu.dao.impl.jdbc.mybatis.ProductMybatisImpl;
import com.neuedu.entity.Product;
import org.junit.Test;

import java.util.List;

public class ProductTest {
    ProductDao productDao=new ProductMybatisImpl();
    @Test
    public void insertProduct(){
        boolean re=true;
        Product produ=new Product();
        produ.setName("QQ");
        produ.setDesc("QQ");
        produ.setPrice(50);
        produ.setRule("QQ");
        produ.setImage("888");
        produ.setStock(100);
        produ.setCategory_id(101);
        re=productDao.addProduct(produ);
       System.out.println(re);

    }
    @Test
    public void deleteProduct(){
        productDao.deleteProduct(62);
        System.out.println("删除成功");
    }
    @Test
    public void updateProduct(){
        Product produ=new Product();
        boolean re=true;
        produ.setName("AA");
        produ.setDesc("BB");
        produ.setPrice(50);
        produ.setRule("CC");
        produ.setImage("888");
        produ.setStock(100);
        produ.setCategory_id(101);
        produ.setId(60);
        re=productDao.updateProduct(produ);
        System.out.println(re);

    }
    @Test
    public void findById(){
        Product produ= productDao.findById(51);
        System.out.println(produ.getName());
    }

    @Test
    public void testfindProduct(){
        List<Product> produ = productDao.findAll();
       System.out.println(produ.size());
    }
}
