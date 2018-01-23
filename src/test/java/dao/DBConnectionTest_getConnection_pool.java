package dao;

import org.junit.Test;

import java.sql.*;
import javax.sql.*;

import static dao.ConfigManager.*;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBConnectionTest_getConnection_pool {

    @Test
    public void getConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Connection connection = null;
//            String dbUser = "root";
//            String dbPassword = "root";
//            String dbURL = "jdbc:mysql://localhost:3306/";
//            String dbName = "timetrack";
//            String dbConectionParams = "?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//            String dbConnectionString = dbURL + dbName + dbConectionParams;
//
//            //  Get a connection to database
//            Class.forName("com.mysql.jdbc.Driver");
        ConfigManager configManager = ConfigManager.getInstance();
        String dbUser = configManager.getProperty(ConfigManager.USERNAME);
        String dbPassword = configManager.getProperty(ConfigManager.PASSWORD);
        String dbURL = configManager.getProperty(ConfigManager.URL);
        String dbName = configManager.getProperty(DBNAME);
        String dbConectionParams = configManager.getProperty(ConfigManager.CONNECTIONPARAMS);
        String driver = configManager.getProperty(ConfigManager.DRIVER);
        String dbConnectionString = dbURL + dbName + dbConectionParams;
        //  Get a connection to database
        Class.forName(driver).newInstance();
        connection = DriverManager.getConnection(dbConnectionString, dbUser, dbPassword);
        assertNotNull(connection);
    }
}