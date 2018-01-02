package dao;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    public static Statement getStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet getResultSet(Statement statement, String sqlSelect) {
        try {
            return statement.executeQuery(sqlSelect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            //  Load the properties file
            Properties properties = new Properties();
            properties.load(new FileInputStream("db.properties"));

            // Read the props
            String dbUser = properties.getProperty("user");
            String dbPassword = properties.getProperty("password");
            String dbURL = properties.getProperty("dburl");
            String dbName = properties.getProperty("dbname");
            String dbConParams = properties.getProperty("dbconparams");
            String dbConUrl=dbURL+dbName+"?"+dbConParams;
            //  Get a connection to database
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            if (connection != null) {
                System.out.println("\nConnection successful!\n");
                return connection;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
