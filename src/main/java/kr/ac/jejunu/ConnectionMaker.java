package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker {
    public Connection getConnection() throws ClassNotFoundException, SQLException;
//        Class.forName("com.mysql.jdbc.Driver");//이 드라이버를 로딩시켜서 이드라이버의 자원을 사용할 수 있음
//        return DriverManager.getConnection("jdbc:mysql://localhost/ujeju?characterEncoding=utf-8", "root", "alswns8516!");

}