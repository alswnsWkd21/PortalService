package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserDaoTest {

    private UserDao jejuuserDao;
//    private UserDao hallauserDao;

    @Before
    public void setup(){
        jejuuserDao  = new UserDao();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        jejuuserDao = new UserDao();
        int id =1;
        User user = jejuuserDao.get(1);
        assertThat(user.getId(), is(    1));
        assertThat(user.getName(), is(  "정민준"));
        assertThat(user.getPassword(), is(  "1234"));
    }


    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName("헐크") ;
        user.setPassword("1111");
        Integer id =jejuuserDao.insert(user);
//        User insertedUser =userDao.insert(user);
//        제대로 됬는지 검증하기 위해

        User insertedUser = jejuuserDao.get(id);
        //제대로된 결과 확인하기
        assertThat(insertedUser.getId(), is(id));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }


////    한라대에 대한 메서드
//    @Test
//    public void hallaGet() throws SQLException, ClassNotFoundException {
//        hallauserDao = new HallaUserDao();
//        int id =1;
//        User user = hallauserDao.get(1);
//        assertThat(user.getId(), is(    1));
//        assertThat(user.getName(), is(  "정민준"));
//        assertThat(user.getPassword(), is(  "1234"));
//    }
//
//
//    @Test
//    public void hallaAdd() throws SQLException, ClassNotFoundException {
//        User user = new User();
//        user.setName("헐크") ;
//        user.setPassword("1111");
//        Integer id =hallauserDao.insert(user);
////        User insertedUser =userDao.insert(user);
////        제대로 됬는지 검증하기 위해
//
//        User insertedUser = hallauserDao.get(id);
//        //제대로된 결과 확인하기
//        assertThat(insertedUser.getId(), is(id));
//        assertThat(insertedUser.getName(), is(user.getName()));
//        assertThat(insertedUser.getPassword(), is(user.getPassword()));
//    }
}
