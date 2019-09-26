package util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author yq
 */
public class DbForMysql {
    private static final String USERNAME="root";
    private static final String PASSWORD="0506";
    private static final String URL="jdbc:mysql://localhost:3306/myfirst_db";
    private static Connection connection =null;
    static {
/*        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return connection;
    }
}
