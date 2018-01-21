package commands;

import dao.DBOperation;
import entities.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Display {
    static final int ITEMS_PER_USERPAGE = 5;
    static final int ITEMS_PER_ADMINPAGE = 10;
    static int itemsInDB = DBOperation.getNumberOfActivities();
    static int curPage = 0;
    static int adminPages = (itemsInDB % ITEMS_PER_ADMINPAGE > 0) ? (1 + itemsInDB / ITEMS_PER_ADMINPAGE) : (itemsInDB / ITEMS_PER_ADMINPAGE);
    static int numUserActivities = 0;

    public static StringBuffer showPage(User user, HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("<center>");
        stringBuffer.append("<h2>" + user.getUserName() + ".</h2>");
        stringBuffer.append("<br/>");

        if (user.isAdmin()) {
            formatAdminPage(stringBuffer);
        } else {
            formatUserPage(user, stringBuffer, request);
        }

        stringBuffer.append("</table>");
        stringBuffer.append("<br/><br/><br/>");
        stringBuffer.append("<center>");

        int userPages = (numUserActivities % ITEMS_PER_USERPAGE > 0) ? (1 + numUserActivities / ITEMS_PER_USERPAGE) : (numUserActivities / ITEMS_PER_USERPAGE);


        stringBuffer.append("<table border=0><tr><td width=25%>");
        stringBuffer.append("<a href='/MainServlet?command=Logout'>");
        stringBuffer.append("<input type='button' name='command' value='Logout'>");
        stringBuffer.append("</a>");
        stringBuffer.append("</td>");
        stringBuffer.append("<td width=75%>");
        stringBuffer.append("<center>");
        if (userPages > 1) {
            stringBuffer.append(" | ");
            for (int i = 0; i < userPages; i++) {
                stringBuffer.append("<a href='/MainServlet?command=ChangePage&Page=" + (i + 1) + "'>");
                stringBuffer.append(i + 1);
                stringBuffer.append("</a>");
                stringBuffer.append(" | ");
            }
        }
        stringBuffer.append("</td>");
        stringBuffer.append("<br/><br/><br/>");

        return stringBuffer;
    }

    private static StringBuffer formatAdminPage(StringBuffer stringBuffer) {
        stringBuffer.append("<table border='1' cellpadding='5' width='60%' align='center'>");
        stringBuffer.append("<tr><th>Id</th><th>Name</th><th>Duration</th><th>UserName</th><th>Status</th>");
///////////////////
        for (Activity activity : DBOperation.getActListFromDB()) {
            addLine2AdminTable(stringBuffer, activity);
        }
//////////////////
        return stringBuffer;
    }

    private static StringBuffer formatUserPage(User user, StringBuffer stringBuffer, HttpServletRequest request) {
        stringBuffer.append("<table border='1' cellpadding='5' width='60%' align='center'>");
        stringBuffer.append("<tr><th>Id</th><th>Name</th><th>Duration</th><th>Status</th><th>Add time</th>");
        for (Activity activity : DBOperation.getActListFromDB()) {
            if (user.getUserID() == activity.getUserID() | activity.getUserID() == 1) {
                numUserActivities++;
                addLine2UserTable(stringBuffer, activity, request);
            }
        }
        return stringBuffer;
    }

    private static void addLine2AdminTable(StringBuffer stringBuffer, Activity activity) {
        stringBuffer.append("<tr>");
        stringBuffer.append("<td>" + activity.getActID() + "</td>");
        stringBuffer.append("<td>" + activity.getActName() + "</td>");
        stringBuffer.append("<td>" + activity.getActDuration() + "</td>");
        stringBuffer.append("<td>" + activity.getUserNameByID(activity.getUserID()) + "</td>");
        stringBuffer.append("<td align='center'>");
        stringBuffer.append(" <form method='get' action='MainServlet'>");
        switch (activity.getActStatus()) {
            /* Marked 4 Del*/
            case 1: {
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=remove&actid=");
                stringBuffer.append(activity.getActID());
                stringBuffer.append("'>");
                stringBuffer.append("<input type='button' value='Remove'>");
                stringBuffer.append("</a>");
                break;
            }
            /*Marked 4 Add */
            case 2: {
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=approve&actid=");
                stringBuffer.append(activity.getActID());
                stringBuffer.append("'>");
                stringBuffer.append("<input type='button' value='Approve'>");
                stringBuffer.append("</a>");
                break;
            }
            /*Marked as Free */
            case 3: {
                stringBuffer.append("<input type='button' value='Free'>");
                break;
            }
            /*Marked as Taken */
            default: {
                stringBuffer.append("<input type='button' value='Taken'>");
                break;
            }
        }
        stringBuffer.append("</form>");
        stringBuffer.append("</td>");
        stringBuffer.append("</tr>");
    }

    private static void addLine2UserTable(StringBuffer stringBuffer, Activity activity, HttpServletRequest request) {
        stringBuffer.append("<tr>");
        stringBuffer.append("<td>" + activity.getActID() + "</td>");
        stringBuffer.append("<td>" + activity.getActName() + "</td>");
        stringBuffer.append("<td>" + activity.getActDuration() + "</td>");
        stringBuffer.append("<td align='center'>");
//        stringBuffer.append(" <form method='get' action='MainServlet'>");
        switch (activity.getActStatus()) {
            /* Marked 4 Del*/
            case 1: {
                stringBuffer.append("<input type='button' value='Pending deletion'>");
                break;
            }
            /* Marked 4 Add*/
            case 2: {
                stringBuffer.append("<input type='button' value='Pending addition'>");
                break;
            }
            /*Marked as Free */
            case 3: {
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=take&actid=");
                stringBuffer.append(activity.getActID());
                stringBuffer.append("'>");
                stringBuffer.append("<input type='button' value='Take'>");
                stringBuffer.append("</a>");
                break;
            }
            /*Marked as Taken */
            default: {
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=drop&actid=");
                stringBuffer.append(activity.getActID());
                stringBuffer.append("'>");
                stringBuffer.append("<input type='button' value='Drop'>");
                stringBuffer.append("</a>");
                break;
            }
        }
        stringBuffer.append("</td>");
        stringBuffer.append("<td width='15%'>");

        if (activity.getUserID() != 1 & activity.getActStatus() == 4) {
            stringBuffer.append("<form method='get' action='MainServlet'>");
            stringBuffer.append("<input type='hidden' name='command' value='addTime'>");
            stringBuffer.append("<p><input name='actid=");
            stringBuffer.append(activity.getActID());
            stringBuffer.append("&amp;amount' type='number' min='1' max='86400' size='2'>&nbsp;&nbsp;");
            stringBuffer.append("<input type='submit' value='Добавить время'>");                  //          +!!!!
            stringBuffer.append("</p>");
            stringBuffer.append("</form>");
        } else
            stringBuffer.append("&nbsp;");
        ///////////////////////////////
        stringBuffer.append("</td>");
        stringBuffer.append("</tr>");
    }

}
