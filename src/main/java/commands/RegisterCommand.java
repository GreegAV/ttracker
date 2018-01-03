package commands;

import controller.ICommand;
import dao.DBOperation;
import entities.Activity;

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
    private static final Double DEFAULT_CASH = 0d;
    private static final Boolean ACTIVE = true;

    //    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
//    private JDBCUserDAO userDAO = daoFactory.getUserDAO();
//
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page;
        String login = request.getParameter(NAME);
        String password = request.getParameter(PASSWORD);

        System.out.println(login + "--------------" + password);
        List<Activity> activities = DBOperation.activityList;
//        System.out.println(activities);
//        System.out.println(DBOperation.userList);
        request.setAttribute("activities", activities);
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
        out.print("<table border='1' cellpadding='5' width='60%' align='center'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Duration</th><th>UserName</th><th>Status</th>");
        for (Activity activity : DBOperation.activityList) {
            out.print("<tr>" +
                    "<td>" + activity.getActID() + "</td>" +
                    "<td>" + activity.getActName() + "</td>" +
                    "<td>" + activity.getActDuration() + "</td>" +
                    "<td>" + activity.getUserName() + "</td>" +
                    "<td>" + activity.getActStatus() + "</td>" +
                    "</tr>");
        }
        out.print("</table>");

//        out.print("<a href='MainServlet?page=1'>1</a> ");
//        out.print("<a href='MainServlet?page=2'>2</a> ");
//        out.print("<a href='MainServlet?page=3'>3</a> ");

        out.close();


// ***********************************************************************************


        return "/home.jsp";
    }
}
