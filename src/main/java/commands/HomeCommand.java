package commands;

import controller.ICommand;
import dao.DBOperation;
import entities.Activity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class HomeCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            String spageid=request.getParameter("page");
            int pageid=Integer.parseInt(spageid);
            int total=5;
            if(pageid==1){}
            else{
                pageid=pageid-1;
                pageid=pageid*total+1;
            }
            List<Activity> list= DBOperation.activityList;

            out.print("<h1>Page No: "+spageid+"</h1>");
            out.print("<table border='1' cellpadding='5' width='60%'>");
            out.print("<tr><th>Id</th><th>Name</th><th>Duration</th><th>UserName</th><th>Status</th>");
            for(Activity activity:list){
                out.print("<tr>" +
                        "<td>"+activity.getActID()+"</td>" +
                        "<td>"+activity.getActName()+"</td>" +
                        "<td>"+activity.getActDuration()+"</td>" +
                        "<td>"+activity.getUserName()+"</td>" +
                        "<td>"+activity.getActStatus()+"</td>" +
                        "</tr>");
            }
            out.print("</table>");

            out.print("<a href='ViewServlet?page=1'>1</a> ");
            out.print("<a href='ViewServlet?page=2'>2</a> ");
            out.print("<a href='ViewServlet?page=3'>3</a> ");

            out.close();
        List<Activity> activities = DBOperation.activityList;
        System.out.println(activities);
        request.setAttribute("activities", activities);
        return "/home.jsp";
    }
}
