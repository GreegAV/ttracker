package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static Logger logger = LoggerFactory.getLogger(DBConnection.class);

    public static ResultSet getResultSet(Statement statement, String sqlSelect) {
        try {
            return statement.executeQuery(sqlSelect);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static Statement getStatement(Connection connection) {
        try {
            if (connection != null) {
                return connection.createStatement();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String dbUser = "root";
            String dbPassword = "root";
            String dbURL = "jdbc:mysql://localhost:3306/";
            String dbName = "timetrack";
            String dbConParams = "?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String dbConUrl = dbURL + dbName + dbConParams;

            //  Get a connection to database
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbConUrl, dbUser, dbPassword);
            if (connection != null) {
                System.out.println("\nConnection successful!\n");
                return connection;
            } else System.out.println("Connection failed!");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

}
