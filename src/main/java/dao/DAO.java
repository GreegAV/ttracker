package dao;

import entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import static dao.DBConnection.*;


public class DAO {

    public static ArrayList<User> getUserListFromDB() {
        ArrayList<User> userList = new ArrayList<User>();
        String usersSQLSelect = "select * from users";
        try {
            ResultSet resultSet = getResultSet(getStatement(getConnection()), usersSQLSelect);
            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("userID"));
                user.setUserLogin(resultSet.getString("userLogin"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setUserName(resultSet.getString("userName"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
                for (Activity actTemp : getActListFromDB()) {
                    if (actTemp.getUserID() == user.getUserID())
                        user.addActToUserList(actTemp);
                }
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Activity> getActListFromDB() {
        ArrayList<Activity> activities = new ArrayList<Activity>();
        String actSQLSelect = "select * from activities";
        try {
            ResultSet resultSet = getResultSet(getStatement(getConnection()), actSQLSelect);
            while (resultSet.next()) {
                activities.add(new Activity(
                        resultSet.getInt("actID"),
                        resultSet.getString("actName"),
                        resultSet.getLong("actDuration"),
                        resultSet.getInt("userID"),
                        resultSet.getInt("actStatus")));
            }
            return activities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateActivity(Activity activity) {
        final String SQL = "UPDATE timetrack.activities  SET "
                + "actDuration=?,  WHERE"   + "actID=? ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setLong(1, activity.getActDuration());
            statement.setInt(2, activity.getActID());
            statement.executeUpdate();

        } catch (SQLException e) {
//            log.info(e);
        }

//        log.info("Updated user in db " + user.getEmail());
//        return findEntity(user.getEmail());
    }
//    public void addUser(User user) {
////        FullBase.getUserList().add(user);
//        //TODO add user to DB
//    }

//    public void changeActivityStatus(Activity activity, int actStatus) {
////        FullBase.getActList().add(activity);
//        //TODO add activity to DB
//    }
}
