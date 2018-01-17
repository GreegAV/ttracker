package commands;

import controller.ICommand;
import entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements ICommand {
    static Logger logger = LoggerFactory.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User loggedUser = User.isUserValid(request.getParameter("nameInput"), request.getParameter("passInput"));

        if (loggedUser != null) {
            logger.info(loggedUser.getUserName());
            response.getWriter().print(Display.showPage(loggedUser));
            request.getServletContext().setAttribute("loggedUser", loggedUser);
        } else {
//            response.getWriter().print("Username/Password error!");
            logger.info("Username/Password error!");

            return "/index.jsp";
        }
        return "";
    }


}
