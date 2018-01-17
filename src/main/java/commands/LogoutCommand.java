package commands;

import controller.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutCommand implements ICommand {

    static Logger logger = LoggerFactory.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        request.getSession(false);
        logger.info("LogoutCommand passed!!!");
//        logger.log(Level.INFO, "LogoutCommand passed!!!");
        return "/index.jsp";
    }
}
