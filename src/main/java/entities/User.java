package entities;

import dao.DBOperation;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable, UserActions {
    private int userID;
    private String userLogin;
    private String userPassword;
    private String userName;
    private boolean isAdmin = false;
    private ArrayList<Activity> actList = new ArrayList<Activity>();

    public User() {
    }

    public User(int userID, String userLogin, String userPassword, String userName, boolean isAdmin, ArrayList<Activity> actList) {
        this.userID = userID;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userName = userName;
        this.isAdmin = isAdmin;
        this.actList = actList;
    }

    public static User isUserValid(String loginName, String loginPass) {
        for (User tmpUser : DBOperation.userList) {
            if (tmpUser.getUserLogin().equalsIgnoreCase(loginName) &&
                    tmpUser.getUserPassword().equals(loginPass)) {
                return tmpUser;
            }
        }
        return null;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdmin() {
        return (this.userID == 1);
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public ArrayList<Activity> getActList() {
        return actList;
    }

    @Override
    public void addActToUserList(Activity act) {
        this.actList.add(act);
    }

    @Override
    public void changeActivityStatus(Activity activity, int actStatus) {
        activity.setActStatus(actStatus);
    }

    @Override
    public User getUserByID(int userID) {
        for (User user : DBOperation.userList) {
            if (user.userID == userID)
                return user;
        }
        return null;
    }

    @Override
    public String getUserNameByID(int userID) {
        for (User user : DBOperation.userList) {
            if (user.userID == userID)
                return user.userName;
        }
        return null;
    }

//    public boolean isUserExists(String loginName) {
//        boolean exists = false;
//        for (User tmpUser : DBOperation.userList) {
//            if (tmpUser.getUserLogin().equalsIgnoreCase(loginName))
//                exists = true;
//        }
//        return exists;
//    }

    @Override
    public String toString() {
        String result = "\n\r";
        result += "ID: " + this.userID + "\t" +
                "Login: " + this.userLogin + "\t" +
                "Pass: " + this.userPassword + "\t" +
                "Name: " + this.userName + "\t" +
                "Admin: " + this.isAdmin + "\t" +
                "Act: " + this.actList;
        return result;
    }
}
