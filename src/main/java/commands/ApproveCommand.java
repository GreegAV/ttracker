package commands;

import dao.DBOperation;
import entities.Activity;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApproveCommand implements controller.ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("ApproveCommand");
        Cookie cookies[]=request.getCookies();
        for (int i = 0; i <cookies.length ; i++) {
            System.out.println(cookies[i].getValue());
            response.getWriter().write(cookies[i].getValue());
        }
//        String prmt=request.getParameter("activity");

        //request.setAttribute("activity", activity.getActID());
        return "";
    }
}
