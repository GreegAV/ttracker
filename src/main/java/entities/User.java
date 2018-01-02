package entities;

import actions.UserActions;
import dao.DBOperation;

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
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public ArrayList<Activity> getActList() {
        return actList;
    }

    public void addActToUserList(Activity act) {
        this.actList.add(act);
    }

    @Override
    public void changeActivityStatus(Activity activity, int actStatus) {
        activity.setActStatus(actStatus);
    }

    public User getUserByID(int userID) {
        for (User user : DBOperation.userList) {
            if (user.userID == userID)
                return user;
        }
        return null;
    }

    public String getUserNameByID(int userID) {
        for (User user : DBOperation.userList) {
            if (user.userID == userID)
                return user.userName;
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "";
        result += "ID: " + this.userID + "\t" +
                "Login: " + this.userLogin + "\t" +
                "Pass: " + this.userPassword + "\t" +
                "Name: " + this.userName + "\t" +
                "Admin: " + this.isAdmin + "\n\r" +
                "Act: " + this.actList;
        return result;
    }
}
