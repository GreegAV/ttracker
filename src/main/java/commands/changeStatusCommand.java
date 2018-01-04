package commands;

import dao.DBOperation;
import entities.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class changeStatusCommand implements controller.ICommand {
    private static final int FORDEL = 1;
    private static final int FORADD = 2;
    private static final int FREE = 3;
    private static final int TAKEN = 4;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        int actid = Integer.parseInt(request.getParameter("actid"));

        Activity activity = Activity.getActByID(actid);
        User loggedUser = (User) request.getServletContext().getAttribute("loggedUser");


        if (action.equalsIgnoreCase("remove")) {
            if (activity != null) {
                activity.setActStatus(FREE);
                activity.setUserID(1);
                activity.setUserName(User.getUserNameByID(1));
                DBOperation.updateActivityDB(activity);
                response.getWriter().print(Display.showPage(loggedUser));
            }
//            if (userid == 1) {
//                System.out.println("Pending + admin");
//                response.getWriter().write("Approving of pending action by admin");
//            } else {
//                System.out.println("Pending + user");
//                response.getWriter().write("User is not allow to Delete");
//                response.getWriter().write("UserID: " + loggeduser.getUserID());
//            }
        }

        if (action.equalsIgnoreCase("approve")) {
            if (activity != null) {
                activity.setActStatus(TAKEN);
                DBOperation.updateActivityDB(activity);
                response.getWriter().print(Display.showPage(loggedUser));
            }
//            if (userid == 1) {
//                System.out.println("Approve + admin");
//                response.getWriter().write("Approve of pending action by admin");
//            } else {
//                System.out.println("Approve + user");
//                response.getWriter().write("User is not allow to Approve");
//                response.getWriter().write("UserID: " + loggeduser.getUserID());
//            }
        }

        if (action.equalsIgnoreCase("take")) {
            if (activity != null) {
                activity.setActStatus(FORADD);
                activity.setUserID(loggedUser.getUserID());
                activity.setUserName(loggedUser.getUserName());
                DBOperation.updateActivityDB(activity);
                response.getWriter().print(Display.showPage(loggedUser));
            }
//            if (userid == 1) {
//                System.out.println("Take + admin");
//                response.getWriter().write("Take action by admin");
//            } else {
//                System.out.println("Take + user");
//                response.getWriter().write("Take by user");
//                response.getWriter().write("UserID: " + loggeduser.getUserID());
//            }
        }
//        if (action.equalsIgnoreCase("free")) {
//            if (loggedUser.getUserID() == 1) {
//                System.out.println("Free + admin");
//                response.getWriter().write("Free action by admin");
//            } else {
//                System.out.println("Free + user");
//                response.getWriter().write("Free by user");
//                response.getWriter().write("UserID: " + loggedUser.getUserID());
//            }
//        }

        if (action.equalsIgnoreCase("drop")) {
            if (activity != null) {
                activity.setActStatus(FORDEL);
                DBOperation.updateActivityDB(activity);
                response.getWriter().print(Display.showPage(loggedUser));
            }
//            if (userid == 1) {
//                System.out.println("Drop + admin");
//                response.getWriter().write("Free action by admin");
//            } else {
//                System.out.println("Drop + user");
//                response.getWriter().write("Free by user");
//                response.getWriter().write("UserID: " + loggeduser.getUserID());
//            }
        }


        return "";
    }
}
