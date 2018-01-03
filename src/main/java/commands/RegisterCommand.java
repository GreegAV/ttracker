package commands;

import controller.ICommand;
import dao.DBOperation;
import entities.Activity;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RegisterCommand implements ICommand {

    //    private static final Logger log = Logger.getLogger(RegisterCommand.class);
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PHONE_NUMBER = "phoneNumber";

    //    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
//    private JDBCUserDAO userDAO = daoFactory.getUserDAO();
//
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        User loggedUser = User.isUserValid(request.getParameter("nameInput"), request.getParameter("passInput"));

        if (loggedUser != null) {
            System.out.println(loggedUser.getUserName());
            response.getWriter().print(Display.showPage(loggedUser));
        }

        return "";
    }


}
