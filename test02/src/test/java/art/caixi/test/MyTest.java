package art.caixi.test;

import art.caixi.mapper.UsersMapper;
import art.caixi.pojo.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MyTest {
    SqlSession sqlSession ;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    @Before
    public void openSqlSession() throws IOException {
        // 读取核心配置文件
        InputStream   in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 取出sqlSession
        sqlSession = factory.openSession();
    }

    @After
    public void closeSqlSession(){
        sqlSession.close();
    }

    @Test
    public void testGetAll() throws ParseException {
        UsersMapper uMapper = sqlSession.getMapper(UsersMapper.class);
//        List<Users> list = uMapper.getAll();
//        list.forEach(user-> System.out.println(user));

//        uMapper.delete(6);
//        uMapper.insert(new Users("王奕" , sf.parse("1998-10-30") , "2" , "宣城市"));

        uMapper.update(new Users(4,"熊垚",sf.parse("1999-11-08"),"2","黄冈市"));
        List<Users> list2 = (List<Users>) uMapper.getByName("小");
        list2.forEach(user-> System.out.println(user));
        uMapper.getById(6);
        List<Users> list3 = uMapper.getAll();
        list3.forEach(user-> System.out.println(user));
        sqlSession.commit();
    }
//    @Test
//    public void testGetByCondition(){
//        UsersMapper uMapper  = sqlSession.getMapper(UsersMapper.class);
//        Users user = new Users();
//        List<Users> list =  uMapper.getByCondition(user);
//        list.forEach(u-> System.out.println(u));
//    }
}

