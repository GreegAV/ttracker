//package dao;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class DBOperation {
//
//    public static Statement getStatement(Connection connection) {
//        try {
//            return connection.createStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static ResultSet getResultSet(Statement statement, String sqlSelect) {
//        try {
//            return statement.executeQuery(sqlSelect);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void closeResultSet(ResultSet resultSet) {
//        if (resultSet != null) {
//            try {
//                resultSet.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void closeStatement(Statement statement) {
//        if (statement != null) {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void closeConnection(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
