package commands;

import controller.ICommand;
import dao.DBOperation;
import entities.Activity;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RegisterCommand implements ICommand {

    //    private static final Logger log = Logger.getLogger(RegisterCommand.class);
    private static final String PASSWORD = "passInput";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String NAME = "nameInput";
    private static final String PHONE_NUMBER = "phoneNumber";

    //    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
//    private JDBCUserDAO userDAO = daoFactory.getUserDAO();
//
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page;
        String login = request.getParameter(NAME);
        String password = request.getParameter(PASSWORD);

//        System.out.println(login + "--------------" + password);
//        List<Activity> activities = DBOperation.activityList;
//        System.out.println(activities);
//        System.out.println(DBOperation.userList);
//        request.setAttribute("activities", DBOperation.userList);
// ***********************************************************************************

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

//        String spageid = request.getParameter("page");
//        System.out.println(spageid);                // ==================
//        int pageid = Integer.parseInt(spageid);
//        int total = 5;
//        if (pageid == 1) {
//            System.out.println(pageid);             // ==================
//        } else {
//            pageid = pageid - 1;
//            pageid = pageid * total + 1;
//        }

//        out.print("<h1>Page No: " + spageid + "</h1>");
        out.print("<br/>");
        out.print("<table border='1' cellpadding='5' width='60%' align='center'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Duration</th><th>UserName</th><th>Status</th>");
        for (Activity activity : DBOperation.activityList) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<tr>");
            stringBuffer.append("<td>" + activity.getActID() + "</td>");
            stringBuffer.append("<td>" + activity.getActName() + "</td>");
            stringBuffer.append("<td>" + activity.getActDuration() + "</td>");
            stringBuffer.append("<td>" + activity.getUserName() + "</td>");
            stringBuffer.append("<td align='center'>");
            stringBuffer.append(" <form method='get' action='MainServlet'>");
//            stringBuffer.append("<input type='hidden' name='command' value='changeStatus'>");
//             <input type = "submit" name = "command" value = "Login" >
//             <input type = "hidden" name = "command" value = "register" >
            switch (activity.getActStatus()) {
                case 1:
//                    {
//                    if (activity.getUserID() == 1) {
//                        stringBuffer.append("<input type='submit' name='command' value='Approve'>");
//                    } else {
//                        stringBuffer.append("<input type='submit' name='command' value='Pending'>");
//                    }
//                    break;
//                }
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
//                        stringBuffer.append("<input type='submit' name='action' value='Pending'>");
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
//                        stringBuffer.append("<input type='submit' name='command' value='Free'>");
                    } else {
                        stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=Take&actid=");
                        stringBuffer.append(activity.getActID());
                        stringBuffer.append("'>");
                        stringBuffer.append("<input type='button' value='Take'>");
                        stringBuffer.append("</a>");
//                        stringBuffer.append("<input type='submit' name='command' value='Take'>");
                    }
                    break;
                }
                default:
                    break;
            }
            stringBuffer.append("</form>");
            stringBuffer.append("</td>");
            stringBuffer.append("</tr>");
            out.print(stringBuffer);
        }
        out.print("</table>");

//        out.print("<a href='MainServlet?page=1'>1</a> ");
//        out.print("<a href='MainServlet?page=2'>2</a> ");
//        out.print("<a href='MainServlet?page=3'>3</a> ");

        out.close();


// ***********************************************************************************

//        request.setAttribute("activities", DBOperation.userList);
        return "";
    }
}
