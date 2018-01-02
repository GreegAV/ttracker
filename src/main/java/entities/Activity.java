package entities;

import actions.ActivityActions;
import dao.DAO;

import java.io.Serializable;

public class Activity implements Serializable, ActivityActions {
    private int actID;
    private String actName;
    private long actDuration;
    private int actStatus;
    private int userID;
    private String userName;

    public Activity() {
    }

    public Activity(int actID, String actName, long actDuration, int userID, int actStatus) {
        this.actID = actID;
        this.actName = actName;
        this.actDuration = actDuration;
        this.actStatus = actStatus;
        this.userID = userID;
        this.userName=getUserNameByID(userID);
    }

    public String getUserName(){
        return userName;
    }
    public int getActStatus() {
        return actStatus;
    }

    public void setActStatus(int actStatus) {
        this.actStatus = actStatus;
    }

    public int getActID() {
        return actID;
    }

    public void setActID(int actID) {
        this.actID = actID;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public long getActDuration() {
        return actDuration;
    }

    public void setActDuration(long actDuration) {
        this.actDuration = actDuration;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Activity getActByID(int actID) {
        for (Activity activity : DAO.getActListFromDB()) {
            if (activity.actID == actID) return activity;
        }
        return null;
    }

    public String getActNameByID(int actID) {
        for (Activity activity : DAO.getActListFromDB()) {
            if (activity.actID == actID) return activity.actName;
        }
        return "";
    }

    public void addDuration(long time) {
        this.actDuration += time;
    }

    private String getUserNameByID(int userID){
        for (User user : DAO.getUserListFromDB()) {
            if (user.getUserID() == userID) return user.getUserName();
        }
        return "";
    }


}
