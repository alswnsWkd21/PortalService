package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserDaoTest {

    private UserDao userDao;
    private DaoFactory daoFactory;

//    private UserDao hallauserDao;

    @Before
    public void setup(){
        daoFactory=new DaoFactory();
//        ApplicationContext applicationContext = new GenericXmlApplicationContext("classpath:daoFactory.xml"); xml방식
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//        jejuuserDao  = daoFactory.userDao();
        userDao=applicationContext.getBean("userDao", UserDao.class);
    }
    @Test
    public void update() throws SQLException, ClassNotFoundException {
        User user = new User();
        Integer id = insertUserTest(user);

        user.setId(id);
        user.setName("교수님");
        user.setPassword("1234");
        userDao.update(user);

        User updatedUser = userDao.get(id);
        assertThat(updatedUser.getId(), is(user.getId()));
        assertThat(updatedUser.getName(), is(user.getName()));
        assertThat(updatedUser.getPassword(), is(user.getPassword()));
    }

    private Integer insertUserTest(User user) throws ClassNotFoundException, SQLException {
        user.setName("헐크") ;
        user.setPassword("1111");
        return userDao.insert(user);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {

        int id =1;
        User user = userDao.get(1);
        assertThat(user.getId(), is(    1));
        assertThat(user.getName(), is(  "정민준"));
        assertThat(user.getPassword(), is(  "1234"));
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = new User();
        Integer id = insertUserTest(user);
        userDao.delete(id);
        User deletedUser = userDao.get(id);
        assertThat(deletedUser, nullValue());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        Integer id = insertUserTest(user);
//        User insertedUser =userDao.insert(user);
//        제대로 됬는지 검증하기 위해

        User insertedUser = userDao.get(id);
        //제대로된 결과 확인하기
        assertThat(insertedUser.getId(), is(id));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

}
