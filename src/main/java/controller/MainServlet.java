package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(MainServlet.class.getName());

    private Helper helper = Helper.getInstance();

    public MainServlet() {
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;

        try {
            ICommand command = helper.getCommand(request);
            page = command.execute(request, response);
            logger.log(Level.INFO, "Redirect to "+page);
        } catch (ServletException | IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
            page = "/error.jsp";
        }
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}