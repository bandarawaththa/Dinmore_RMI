package dbHandler;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private BasicDataSource basicDataSource;

    private DBConnection () {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/Dinmore");
        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxTotal(5);
    }

    public static DBConnection getInstance(){
        return (null == dbConnection) ? (dbConnection = new DBConnection()) : dbConnection;
    }

    public Connection getConnection () throws SQLException {
        return basicDataSource.getConnection();
    }
}
