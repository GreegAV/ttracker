package commands;

import dao.DAOUser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterNewUserCommand implements controller.ICommand {
    private static Logger logger = Logger.getLogger(RegisterNewUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String newUserName = request.getParameter("nameInput");
        String newUserLogin = request.getParameter("loginInput");
        String newUserPass = request.getParameter("passInput");

        if (DAOUser.isUserLoginExists(newUserLogin)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<center>")
                    .append("<img src='error.png'><br /><br />")
                    .append("Логин " + newUserLogin + " уже существует в базе!")
                    .append("<br /><br />")
                    .append("<form method='post' action='MainServlet'>")
                    .append("<input type='hidden' name='command' value='addUser'>")
                    .append("<input type='submit' value='Попробовать снова'>")
                    .append("</form>");
            response.getWriter().print(stringBuffer);
        } else {
            DAOUser.addNewUser(newUserLogin, newUserName, newUserPass);
        }

//        User loggedUser = User.isUserValid(request.getParameter("nameInput"), request.getParameter("passInput"));

//        if (loggedUser != null) {
//            logger.info(loggedUser.getUserName() + " logged in.");
//            request.getServletContext().setAttribute("loggedUser", loggedUser);
//            response.getWriter().print(Display.showPage(loggedUser, request, 1));
//        } else {
//            logger.info("Username/Password error!");
//            return "/error.jsp";
//        }
        return "/index.jsp";
    }
}
