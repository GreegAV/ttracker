package commands;

import controller.ICommand;
import dao.DBOperation;
import entities.Activity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegisterCommand implements ICommand {

//    private static final Logger log = Logger.getLogger(RegisterCommand.class);
    private static final String PASSWORD = "passInput";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String NAME = "nameInput";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final Double DEFAULT_CASH = 0d;
    private static final Boolean ACTIVE = true;
    //    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
//    private JDBCUserDAO userDAO = daoFactory.getUserDAO();
//
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String login = request.getParameter(NAME);
        String password = request.getParameter(PASSWORD);
//        String firstName = request.getParameter(FIRST_NAME);
//        String lastName = request.getParameter(LAST_NAME);
//        String phoneNumber = request.getParameter(PHONE_NUMBER);
//        User user = User.newBuilder()
//                .setEmail(login)
//                .setPassword(password)
//                .setFirstName(firstName)
//                .setLastName(lastName)
//                .setPhoneNumber(phoneNumber)
//                .setCash(DEFAULT_CASH)
//                .setActive(ACTIVE)
//                .build();

//        if (userDAO.addNew(user)) {
//            page = "/pages/home.jsp";
//            HttpSession session = request.getSession();
//            session.setAttribute("user", user);
//            log.info(user.getEmail() + " registered");
//        } else {
//            page = "/pages/error.jsp";
//        }
        System.out.println(login+"--------------"+password);
        List<Activity> activities = DBOperation.activityList;
        System.out.println(activities);
        request.setAttribute("activities", activities);
        return "/home.jsp";
    }
}
