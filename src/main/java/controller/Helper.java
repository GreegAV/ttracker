package controller;

import commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class Helper {
//    private static final Logger LOGGER = Logger.getLogger(Helper.class);
    private static Helper instance = null;
    private HashMap<String, ICommand> commands = new HashMap<>();

    private Helper() {
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
//        commands.put("phones", new PhonesCommand());
//        commands.put("laptops", new LaptopsCommand());
//        commands.put("logout", new LogoutCommand());
//        commands.put("bucket", new CartCommand());
//        commands.put("admin", new AdminCommand());
//        commands.put("delete", new DeleteProductCommand());
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
//        System.out.println("command.toString: "+command.toString());
        System.out.println("request.getParameter: "+request.getParameter("command"));
        if (command == null) {
            command = new HomeCommand();
            System.out.println("Переход на домашнюю страницу.");
        }
        return command;
    }
}
