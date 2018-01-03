package commands;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class changeStatusCommand implements controller.ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("changeStatusCommand"+"\n\r");

        String action = request.getParameter("action");
        System.out.println(action);
        String actid = request.getParameter("actid");
        System.out.println(actid);
        response.getWriter().write(action+"\n\r");
        response.getWriter().write(actid);

//        String prmt=request.getParameter("activity");

        //request.setAttribute("activity", activity.getActID());
        return "";
    }
}
