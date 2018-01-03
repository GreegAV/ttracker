package commands;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class changeStatusCommand implements controller.ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("changeStatusCommand");
//        int actid = (int)request.getAttribute("activity");

//        String status = request.getParameter();
//        String prmt=request.getParameter("activity");

        //request.setAttribute("activity", activity.getActID());
        return "";
    }
}
