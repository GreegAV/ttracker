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
            if (user.getUserID() == activity.getUserID() | user.isAdmin() | activity.getUserID()==1) {
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
                    /* Marked 4 Del*/
                    case 1:{
                        if (user.getUserID() == 1) {
                            stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=remove&actid=");
                            stringBuffer.append(activity.getActID());
                            stringBuffer.append("'>");
                            stringBuffer.append("<input type='button' value='Remove'>");
                            stringBuffer.append("</a>");
                        } else {
//                            stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=Pending&actid=");
//                            stringBuffer.append(activity.getActID());
//                            stringBuffer.append("'>");
                            stringBuffer.append("<input type='button' value='Pending'>");
//                            stringBuffer.append("</a>");
                        }
                        break;
                    }

                     /*Marked 4 Add */
                    case 2: {
                        if (user.getUserID() == 1) {
                            stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=approve&actid=");
                            stringBuffer.append(activity.getActID());
                            stringBuffer.append("'>");
                            stringBuffer.append("<input type='button' value='Approve'>");
                            stringBuffer.append("</a>");
                        } else {
//                            stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=Pending&actid=");
//                            stringBuffer.append(activity.getActID());
//                            stringBuffer.append("'>");
                            stringBuffer.append("<input type='button' value='Pending'>");
//                            stringBuffer.append("</a>");
                        }
                        break;
                    }

                    /*Marked as Free */
                    case 3: {
                        if (user.getUserID() == 1) {
                            stringBuffer.append("<input type='button' value='Free'>");
                        } else {
                            stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=take&actid=");
                            stringBuffer.append(activity.getActID());
                            stringBuffer.append("'>");
                            stringBuffer.append("<input type='button' value='Take'>");
                            stringBuffer.append("</a>");
                        }
                        break;
                    }

                    /*Marked as Taken */
                    default:{
                        if (user.getUserID() == 1) {
                            stringBuffer.append("<input type='button' value='Taken'>");
                        } else {
                            stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=drop&actid=");
                            stringBuffer.append(activity.getActID());
                            stringBuffer.append("'>");
                            stringBuffer.append("<input type='button' value='Drop'>");
                            stringBuffer.append("</a>");
                        }
                        break;
                    }
                }

                stringBuffer.append("</td>");
                stringBuffer.append("</tr>");
            }
        }
        stringBuffer.append("</table>");
        stringBuffer.append("<br/><br/><br/>");
        stringBuffer.append("<input type='submit' name='command' value='Logout'>");
        stringBuffer.append("</form>");
        return stringBuffer;
    }
}
