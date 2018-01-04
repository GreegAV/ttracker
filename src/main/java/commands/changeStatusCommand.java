package commands;

import entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class changeStatusCommand implements controller.ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().write("changeStatusCommand"+"\n\r");
        String action = request.getParameter("action");
        String actid = request.getParameter("actid");

        User loggeduser = (User) request.getServletContext().getAttribute("loggedUser");
        int userid = loggeduser.getUserID();
//        response.getWriter().write(action+"\n\r");
//        response.getWriter().write(actid);
        // request.getServletContext().setAttribute("loggedUser", loggedUser);

        if (action.equalsIgnoreCase("Pending")) {
            if (userid == 1) {
                System.out.println("Pending + admin");
                response.getWriter().write("Approving of pending action by admin");
            } else {
                System.out.println("Pending + user");
                response.getWriter().write("User is not allow to Delete");
                response.getWriter().write("UserID: " + loggeduser.getUserID());
            }
        }

        if (action.equalsIgnoreCase("Approve")) {
            if (userid == 1) {
                System.out.println("Approve + admin");
                response.getWriter().write("Approve of pending action by admin");
            } else {
                System.out.println("Approve + user");
                response.getWriter().write("User is not allow to Approve");
                response.getWriter().write("UserID: " + loggeduser.getUserID());
            }
        }

        if (action.equalsIgnoreCase("Take")) {
            if (userid == 1) {
                System.out.println("Take + admin");
                response.getWriter().write("Take action by admin");
            } else {
                System.out.println("Take + user");
                response.getWriter().write("Take by user");
                response.getWriter().write("UserID: " + loggeduser.getUserID());
            }
        }

        if (action.equalsIgnoreCase("Free")) {
            if (userid == 1) {
                System.out.println("Free + admin");
                response.getWriter().write("Free action by admin");
            } else {
                System.out.println("Free + user");
                response.getWriter().write("Free by user");
                response.getWriter().write("UserID: " + loggeduser.getUserID());
            }
        }



        return "";
    }
}
