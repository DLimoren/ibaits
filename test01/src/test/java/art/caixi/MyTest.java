package art.caixi;

import art.caixi.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    SqlSession sqlSession;
    @Before
    public void openSqlSession() throws IOException {
        // 读取核心配置文件
        InputStream in =  Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 取出SqlSession对象
        sqlSession = factory.openSession();
    }
    @After
    public void closeSqlSession(){
        sqlSession.close();
    }
    @Test
    public void testGetAll() throws IOException {
//        完成查询操作
        List<Student> list = sqlSession.selectList("zar.getAll");
        list.forEach(student-> System.out.println(student));
//        关闭sqlSession
        sqlSession.commit();
    }

    @Test
    public void testGetById() throws IOException {

        // 按主键查学生
        Student stu = sqlSession.selectOne("zar.getById",1);
        System.out.println(stu);
        // 关闭SqlSession
        sqlSession.commit();
    }
    @Test
    public void testGetByName() throws IOException{
        // 调用方法
        List<Student> list = sqlSession.selectList("zar.getByName","王");
        list.forEach(student-> System.out.println(student));
        // 关闭sqlSession
        sqlSession.commit();

    }
    @Test
    public void testInsert() throws IOException{
        // 调用方法
        int num = sqlSession.insert("zar.insert",new Student("熊垚","woduogudu@outlook.com",22));
        System.out.println("num ======= > " + num );
        List<Student> list = sqlSession.selectList("zar.getAll");
        list.forEach(student -> System.out.println(student));
        sqlSession.commit();
    }
    @Test
    public void testDeleteById() throws IOException {
        // 调用方法
        int num = sqlSession.delete("zar.deleteById" , 1);
        System.out.println("已删除" + num + "条记录");
        // 关闭Sqlsession对象
        List<Student> list  = sqlSession.selectList("zar.getAll" );
        list.forEach(student -> System.out.println(student));
        sqlSession.commit();
    }
    @Test
    public void testUpdate() throws IOException {
        // 调用方法
        int num = sqlSession.update("zar.update" , new Student(3,"王奕" , "woduogudu@outlook.com",22));
        System.out.println("更新的语句数量："  + num );
        sqlSession.commit();
    }
}
