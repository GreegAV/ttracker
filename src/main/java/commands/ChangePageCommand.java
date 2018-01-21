package commands;

import controller.ICommand;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangePageCommand implements ICommand {
    private static Logger logger = LoggerFactory.getLogger(ChangePageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User loggedUser = (User) request.getServletContext().getAttribute("loggedUser");
        Display.curPage = Integer.parseInt(request.getParameter("Page"));
        System.out.println(Display.curPage);
        logger.info("Changing page to "+Display.curPage);
        response.getWriter().print(Display.showPage(loggedUser, request));

        return "/MainServlet";
    }


}