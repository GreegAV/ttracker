package dao;

import java.sql.*;
import java.util.ArrayList;

import entities.*;

import static dao.DBConnection.*;


public class DAO {

    public static ArrayList<User> getUserListFromDB() {
        String usersSQLSelect = "select * from users";
        try {
            ArrayList<User> userList = new ArrayList<User>();
            ResultSet resultSet = getResultSet(getStatement(getConnection()), usersSQLSelect);
            while (resultSet.next()) {

                int userID = resultSet.getInt(1);
                String userLogin = resultSet.getString(2);
                String userPass = resultSet.getString(3);
                String userName = resultSet.getString(4);
                boolean isAdm = resultSet.getBoolean(5);
                System.out.println(userID + "\t" +
                        userLogin + "\t" +
                        userPass + "\t" +
                        userName + "\t" +
                        isAdm + "\t"
                );
                ArrayList<Activity> actList = new ArrayList<Activity>();
                for (Activity actTemp : getActListFromDB()) {
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
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Activity> getActListFromDB() {
        String actSQLSelect = "select * from activities";
        try {
            ResultSet resultSet = getResultSet(getStatement(getConnection()), actSQLSelect);
            ArrayList<Activity> activities = new ArrayList<Activity>();
            while (resultSet.next()) {
                Activity act = new Activity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5));
                System.out.println(act);
                activities.add(act);
            }
            return activities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateActivity(Activity activity) {
        final String SQL = "UPDATE timetrack.activities  SET "
                + "actDuration=?,  WHERE" + "actID=? ";

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
