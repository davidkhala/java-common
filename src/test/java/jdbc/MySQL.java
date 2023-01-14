package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySQL {
    public static void main(String args[]) throws Exception {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://168.138.162.164:3306/mysql?" + "user=root&password=password&&serverTimezone=UTC");

        // Do something with the Connection
        conn.close();

    }
}
