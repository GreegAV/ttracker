package commands;

import controller.ICommand;
import dao.DBConnection;
import dao.DBOperation;
import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutCommand implements ICommand {

    private static Logger logger = org.apache.log4j.Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        request.getSession(false);
        //TODO commit changes to DB
        logger.info("LogoutCommand passed!!!");
        return "/index.jsp";
    }
}
