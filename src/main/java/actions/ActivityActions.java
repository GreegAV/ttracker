package actions;

import entities.Activity;

public interface ActivityActions {

    Activity getActByID(int actID);

    String getActNameByID(int actID);

    void addDuration(long time);
}
