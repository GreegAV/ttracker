package dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest_getConnection_old {

    @Test
    public void getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
            String dbUser = "root";
            String dbPassword = "root";
            String dbURL = "jdbc:mysql://localhost:3306/";
            String dbName = "timetrack";
            String dbConectionParams = "?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String dbConnectionString = dbURL + dbName + dbConectionParams;

            //  Get a connection to database
            Class.forName("com.mysql.jdbc.Driver");
            assertNotNull(DriverManager.getConnection(dbConnectionString, dbUser, dbPassword));
    }
}