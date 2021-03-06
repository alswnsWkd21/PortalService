package kr.ac.jejunu;

import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicTreeUI;
import java.sql.*;

public class UserDao {
    private final JdbcTemplate jdbcTemplate;


    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(int id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum)->{
            try {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            } catch (EmptyResultDataAccessException e) {
//                e.printStackTrace();
//                null출력
                return null;
            }

        });
    }

    public Integer insert(User user) throws SQLException {
        String sql= "insert into userinfo(name, password) values (?, ?)";
        Object [] params = new Object[]{user.getName(), user.getPassword()};
        KeyHolder keyHolder  = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(con-> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for(int i =0; i<params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void update(User user) throws SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object [] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object [] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }

}