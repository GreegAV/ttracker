package dao;

import java.sql.*;
import java.util.ArrayList;

import entities.*;


public class DBOperation {
    public static ArrayList<Activity> activityList = getActListFromDB();
    public static ArrayList<User> userList = getUserListFromDB();

    private static ArrayList<User> getUserListFromDB() {
        String usersSQLSelect = "select * from users";
        try {
            ArrayList<User> userList = new ArrayList<User>();
            Connection connection = DBConnection.getConnection();
            Statement statement = DBConnection.getStatement(connection);
            ResultSet resultSet = DBConnection.getResultSet(statement, usersSQLSelect);
            while (resultSet.next()) {
                int userID = resultSet.getInt(1);
                String userLogin = resultSet.getString(2);
                String userPass = resultSet.getString(3);
                String userName = resultSet.getString(4);
                boolean isAdm = resultSet.getBoolean(5);

                ArrayList<Activity> actList = new ArrayList<Activity>();
                for (Activity actTemp : activityList) {
                    System.out.println(actTemp.getActID() + "\t" + actTemp.getActName());
                    if (actTemp.getUserID() == userID) {
                        actList.add(actTemp);
                    }
                }
                User user = new User(
                        userID,
                        userLogin,
                        userPass,
                        userName,
                        isAdm,
                        actList
                );
                // this.userID = userID;
                //        this.userLogin = userLogin;
                //        this.userPassword = userPassword;
                //        this.userName = userName;
                //        this.isAdmin = isAdmin;
//                user.setUserID(resultSet.getInt(1));
//                user.setUserLogin(resultSet.getString(2));
//                user.setUserPassword(resultSet.getString(3));
//                user.setUserName(resultSet.getString(4));
//                user.setAdmin(resultSet.getBoolean(5));
                userList.add(user);
            }
            DBConnection.closeResultSet(resultSet);
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Activity> getActListFromDB() {
        String actSQLSelect = "select * from activities";
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = DBConnection.getStatement(connection);
            ResultSet resultSet = DBConnection.getResultSet(statement, actSQLSelect);
            ArrayList<Activity> activities = new ArrayList<Activity>();
            while (resultSet.next()) {
                Activity act = new Activity(
                        resultSet.getInt(1),    //actID
                        resultSet.getString(2), //actName
                        resultSet.getLong(3),   // actDuration
                        resultSet.getInt(4),    //actMarked
                        resultSet.getInt(5));   //actUserID
                System.out.println(act);
                activities.add(act);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return activities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateActivity(Activity activity) {
        final String SQL = "UPDATE timetrack.activities  SET "
                + "actDuration=?,  WHERE" + "actID=? ";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setLong(1, activity.getActDuration());
            statement.setInt(2, activity.getActID());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
