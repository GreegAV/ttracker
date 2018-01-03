package commands;

import dao.DBOperation;
import entities.*;

public class Display {
    public static StringBuffer showPage(User user) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Hello, " + user.getUserName() + ".");
        stringBuffer.append("<br/>");
        if (user.isAdmin()) {
            stringBuffer.append("<table border='1' cellpadding='5' width='60%' align='center'>");
            stringBuffer.append("<tr><th>Id</th><th>Name</th><th>Duration</th><th>UserName</th><th>Status</th>");
        } else {
            stringBuffer.append("<table border='1' cellpadding='4' width='60%' align='center'>");
            stringBuffer.append("<tr><th>Id</th><th>Name</th><th>Duration</th><th>Status</th>");
        }
        for (Activity activity : DBOperation.activityList) {
            if (user.getUserID()==activity.getUserID()| user.isAdmin()) {
                stringBuffer.append("<tr>");
                stringBuffer.append("<td>" + activity.getActID() + "</td>");
                stringBuffer.append("<td>" + activity.getActName() + "</td>");
                stringBuffer.append("<td>" + activity.getActDuration() + "</td>");
                if (user.isAdmin()) {
                    stringBuffer.append("<td>" + activity.getUserName() + "</td>");
                }
                stringBuffer.append("<td align='center'>");
                stringBuffer.append(" <form method='get' action='MainServlet'>");
                switch (activity.getActStatus()) {
                    case 1:
                    case 2: {
                        if (activity.getUserID() == 1) {
                            stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=Approve&actid=");
                            stringBuffer.append(activity.getActID());
                            stringBuffer.append("'>");
                            stringBuffer.append("<input type='button' value='Approve'>");
                            stringBuffer.append("</a>");
                        } else {
                            stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=Pending&actid=");
                            stringBuffer.append(activity.getActID());
                            stringBuffer.append("'>");
                            stringBuffer.append("<input type='button' value='Pending'>");
                            stringBuffer.append("</a>");
                        }
                        break;
                    }
                    case 3: {
                        if (activity.getUserID() == 1) {
                            stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=Free&actid=");
                            stringBuffer.append(activity.getActID());
                            stringBuffer.append("'>");
                            stringBuffer.append("<input type='button' value='Free'>");
                            stringBuffer.append("</a>");
                        } else {
                            stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=Take&actid=");
                            stringBuffer.append(activity.getActID());
                            stringBuffer.append("'>");
                            stringBuffer.append("<input type='button' value='Take'>");
                            stringBuffer.append("</a>");
                        }
                        break;
                    }
                    default:
                        break;
                }
                stringBuffer.append("</form>");
                stringBuffer.append("</td>");
                stringBuffer.append("</tr>");
            }
        }
        stringBuffer.append("</table>");
        return stringBuffer;
    }
}
