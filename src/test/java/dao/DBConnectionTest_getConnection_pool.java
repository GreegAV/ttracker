package dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static dao.ConfigManager.DBNAME;
import static org.junit.Assert.*;

public class DBConnectionTest_getConnection_pool {

    @Test
    public void getConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Connection connection = null;

        ConfigManager configManager = ConfigManager.getInstance();
        String dbUser = configManager.getProperty(ConfigManager.USERNAME);
        String dbPassword = configManager.getProperty(ConfigManager.PASSWORD);
        String dbURL = configManager.getProperty(ConfigManager.URL);
        String dbName = configManager.getProperty(ConfigManager.DBNAME);
        String dbConectionParams = configManager.getProperty(ConfigManager.CONNECTIONPARAMS);
        String driver = configManager.getProperty(ConfigManager.DRIVER);
        String dbConnectionString = dbURL + dbName + dbConectionParams;
        //  Get a connection to database
        Class.forName(driver).newInstance();
        connection = DriverManager.getConnection(dbConnectionString, dbUser, dbPassword);
        assertNotNull(connection);
    }
}