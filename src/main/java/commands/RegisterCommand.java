package commands;

import controller.ICommand;
import dao.DBConnection;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterCommand implements ICommand {
    private static Logger logger = Logger.getLogger(RegisterCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User loggedUser = User.isUserValid(request.getParameter("nameInput"), request.getParameter("passInput"));

        if (loggedUser != null) {
            logger.log(Level.INFO, loggedUser.getUserName());
            response.getWriter().print(Display.showPage(loggedUser));
            request.getServletContext().setAttribute("loggedUser", loggedUser);
        } else {
            response.getWriter().print("Username/Password error!");
            return "/index.jsp";
        }
        return "";
    }


}
