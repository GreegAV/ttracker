package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.MainServlet;
import entities.*;


public class DBOperation {

    //    private static ArrayList<User> simpleUserList = getSimpleUserListFromDB();
    private static Logger logger = Logger.getLogger(DBOperation.class.getName());

    public static ArrayList<Activity> activityList = getActListFromDB();
    public static ArrayList<User> userList = getUserListFromDB();

    private static ArrayList<User> getUserListFromDB() {
        ArrayList<User> fullUserList = new ArrayList<User>();
        for (User tempUser : getSimpleUserListFromDB()) {
            for (Activity actTemp : activityList) {
                if (actTemp.getUserID() == tempUser.getUserID()) {
                    actTemp.setUserName(tempUser.getUserName());
                }
            }
            fullUserList.add(tempUser);
        }
        return fullUserList;
    }

    private static ArrayList<User> getSimpleUserListFromDB() {
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
                    if (actTemp.getUserID() == userID) {
                        actList.add(actTemp);
                    }
                }
                User user = new User(userID, userLogin, userPass, userName, isAdm, actList);
                userList.add(user);
            }
            DBConnection.closeResultSet(resultSet);
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
            return userList;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
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
                int actID = resultSet.getInt(1);   //actID
                String actName = resultSet.getString(2); //actName
                long actTime = resultSet.getLong(3);   // actDuration
                int actMark = resultSet.getInt(4);    //actMarked
                int actUser = resultSet.getInt(5);   //actUserID
                Activity act = new Activity(actID, actName, actTime, actMark, actUser);   //actUserID
                activities.add(act);
            }
            DBConnection.closeResultSet(resultSet);
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
            return activities;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public static void updateActivityDB(Activity activity) {
        final String SQL = "UPDATE timetrack.activities  SET "
                + "actDuration=?, actMarked=?, WHERE" + "actID=? ";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setLong(1, activity.getActDuration());
            statement.setInt(2, activity.getActStatus());
            statement.setInt(3, activity.getActID());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

    }
}
