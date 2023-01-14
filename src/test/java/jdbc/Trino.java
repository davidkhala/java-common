package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Trino {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:trino://168.138.184.249:8080/hive/sales?user=test&password=secret&SSL=true" );
        conn.close();
    }
}
