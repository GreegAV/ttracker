package dao;

import entities.User;

public class DAOUser {
    public static String getUserNameByID(int userID) {
        for (User user : DAOOperation.getUserListFromDB()) {
            if (user.getUserID() == userID)
                return user.getUserName();
        }
        return null;
    }
}
