package entities;

public interface UserActions {

    User getUserByID(int userID);

    String getUserNameByID(int userID);

    void addActToUserList(Activity activity);

    void changeActivityStatus(Activity activity, int actStatus);

}
