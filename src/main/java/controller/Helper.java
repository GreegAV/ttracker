package controller;


import commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class Helper {
//    private static final Logger LOGGER = Logger.getLogger(Helper.class);
    private static Helper instance = null;
    private HashMap<String, ICommand> commands = new HashMap<>();

    private Helper() {
        commands.put("Logout", new LogoutCommand());
        commands.put("Login", new RegisterCommand());
//        commands.put("Approve", new ApproveCommand());
//        commands.put("Pending", new PendingCommand());
//        commands.put("Free", new FreeCommand());
//        commands.put("Take", new TakeCommand());
        commands.put("changeStatus", new changeStatusCommand());
//        commands.put("order", new OrderCommand());
//        commands.put("addProduct", new AddNewProductCommand());
//        commands.put("addMoney", new AddMoneyCommand());
    }

    public static Helper getInstance() {
        if (instance == null) {
            synchronized (Helper.class) {
                if (instance == null)
                    instance = new Helper();
            }
        }
        return instance;
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        System.out.println("request.getParameter: "+request.getParameter("command"));
        if (command == null) {
            command = new HomeCommand();
            System.out.println("Переход на домашнюю страницу.");
        }
        return command;
    }
}
