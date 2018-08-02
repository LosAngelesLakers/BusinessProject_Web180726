import com.neuedu.dao.CategoryDao;
import com.neuedu.dao.impl.jdbc.mybatis.CategoryMybatisImpl;
import org.junit.Test;

public class CategoryTest {
    @Test
    public void testfindById(){
        CategoryDao categoryDao=new CategoryMybatisImpl();
        categoryDao.findById(23);

    }



}
