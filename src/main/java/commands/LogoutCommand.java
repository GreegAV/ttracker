package commands;

import controller.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LogoutCommand implements ICommand {

    private static Logger logger = Logger.getLogger(LogoutCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        request.getSession(false);
        logger.log(Level.INFO, "LogoutCommand passed!!!");
        return "/index.jsp";
    }
}
