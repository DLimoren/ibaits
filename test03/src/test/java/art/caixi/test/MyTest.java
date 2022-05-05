package art.caixi.test;

import art.caixi.mapper.BookMapper;
import art.caixi.mapper.CustomerMapper;
import art.caixi.mapper.OrdersMapper;
import art.caixi.mapper.UsersMapper;
import art.caixi.pojo.Book;
import art.caixi.pojo.Customer;
import art.caixi.pojo.Orders;
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
import java.util.*;


public class MyTest {
    SqlSession sqlSession ;
    BookMapper bookMapper;
    CustomerMapper customerMapper;
    OrdersMapper ordersMapper;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    @Before
    public void openSqlSession() throws IOException {
        // 读取核心配置文件
        InputStream   in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 取出sqlSession
        sqlSession = factory.openSession();
        bookMapper = sqlSession.getMapper(BookMapper.class);
        customerMapper = sqlSession.getMapper(CustomerMapper.class);
        ordersMapper = sqlSession.getMapper(OrdersMapper.class);
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
        Users user1 = new Users();
        user1.setId(36);
        user1.setUserName("蔡希");
        user1.setAddress("湖北省黄冈市蕲春县青石镇");

        uMapper.updateBySet(user1);
        List<Users> lst1 = uMapper.getAll();
        lst1.forEach(users -> System.out.println(users));
        sqlSession.commit();
    }
    @Test
    public void testSelectByIds(){
        UsersMapper uMapper = sqlSession.getMapper(UsersMapper.class);
        Integer []arr = {2,4,6,36};
        List<Users> lst = uMapper.getByIds(arr);
        lst.forEach(users -> System.out.println(users));

    }
    @Test
    public void testDeleteByIds() throws ParseException {
        UsersMapper uMapper = sqlSession.getMapper(UsersMapper.class);
        Integer []arr = {2,4,6,36};
        uMapper.deleteByIds(arr);
        List<Users> lst = uMapper.getAll();
        lst.forEach(users -> System.out.println(users));
        List<Users> users = new ArrayList<>();
        users.add(new Users(null , null ,"1","bjpowernode"));
        users.add(new Users(null , null , null , "辽宁省大连市青泥八号"));
        uMapper.insertAll(users);
        Date begin = sf.parse("1997-01-01");
        Date end = sf.parse("2000-11-11");
        Map map = new HashMap();
        map.put("birthdayBegin" , begin);
        map.put("birthdayEnd" , end);
        uMapper.getByBirthday(begin , end);
        uMapper.getByMap(map);
        map = uMapper.getMap(35);
        System.out.println(map.get("name"));
        System.out.println(map.get("addr"));
        System.out.println(map.get("bir"));
        System.out.println(map.get("sex"));
        List<Map> mpp = uMapper.getMultiMap();
        mpp.forEach(mp->{
            System.out.println(mp.get("id"));
            System.out.println(mp.get("addr"));
            System.out.println(mp.get("name"));
            System.out.println(mp.get("sex"));
        });
        sqlSession.commit();
    }
    @Test
    public void testGetAllById(){
        Customer customer = customerMapper.getAllById(3);
        System.out.println(customer);
    }
    @Test
    public void testBookMapper(){
        List<Book> lst = bookMapper.getAll();
        lst.forEach(book-> System.out.println("book 是" + book));

    }
    @Test
    public void testgetOrder(){
        Orders orders = ordersMapper.getById(11);
        System.out.println(orders);

    }
}

