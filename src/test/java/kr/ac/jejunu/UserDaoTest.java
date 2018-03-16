package kr.ac.jejunu;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserDaoTest {

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        int id =1;
        User user = userDao.get(1);
        assertThat(user.getId(), is(    1));
        assertThat(user.getName(), is(  "정민준"));
        assertThat(user.getPassword(), is(  "1234"));

    }
}
