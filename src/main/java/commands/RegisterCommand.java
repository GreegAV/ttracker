package commands;

import controller.ICommand;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements ICommand {
    //    private static final Logger log = Logger.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");

        User loggedUser = User.isUserValid(request.getParameter("nameInput"), request.getParameter("passInput"));

        if (loggedUser != null) {
            System.out.println(loggedUser.getUserName());
            response.getWriter().print(Display.showPage(loggedUser));
            request.getServletContext().setAttribute("loggedUser", loggedUser);
        } else {
            response.getWriter().print("Username/Password error!");
            return "/index.jsp";
        }
        return "";
    }


}
