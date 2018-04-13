package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(int id) throws ClassNotFoundException, SQLException {
        //mysql driver load
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
            preparedStatement = statementStrategy.makeStatement(connection);
            resultSet = preparedStatement.executeQuery();
//            preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
//            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }


        //        결과를 리턴한다.
        return user;

    }

    public Integer insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer id;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new InsertUserStatementStrategy(user);
            statementStrategy.makeStatement(connection);
//            preparedStatement = connection.prepareStatement(
//                    "insert into userinfo(name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement = connection.prepareStatement("select last_insert_id()");
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            id = resultSet.getInt(1);
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
        return id;
    }


    public void update(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer id;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new UpdateUserStatementStrategy(user);
            preparedStatement = statementStrategy.makeStatement(connection);
            makePrepareStatement(user, connection);
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }


    }

    private void makePrepareStatement(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update userinfo set name = ?, password = ? where id = ?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getId());
        preparedStatement.executeUpdate();
    }


    public void delete(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new DeletUserStatementStrategy(id);
            preparedStatement = statementStrategy.makeStatement(connection);
//            preparedStatement = connection.prepareStatement(
//                    "delete from userinfo where id = ?");
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
    }
}

