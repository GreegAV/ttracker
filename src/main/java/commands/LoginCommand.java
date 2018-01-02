package commands;

import controller.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginCommand implements ICommand {

    //    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private static final String LOGIN = "nameInput";
    private static final String PASSWORD = "passInput";
//    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
//    private JDBCUserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "";
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        System.out.println(login + "   " + password);

//        User user = userDAO.findEntity(login);


//        if (user != null && user.getPassword().equals(password) && user.isActive()) {
//            page = "/pages/home.jsp";
//            HttpSession session = request.getSession();
//            session.setAttribute("user", user);
//
//            log.info(user.getEmail() + " logged in");
//        } else {
//            page = "/pages/error.jsp";
//        }
        System.out.println("LoginCommand passed!!!");
        return "/home.jsp";
    }
}
