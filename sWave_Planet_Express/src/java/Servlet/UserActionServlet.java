package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.Command;
import Command.CommandFactory;
/*
    Functionality:
    Login
    Register
    Logout
*/

/**
 * Servlet implementation class checkLoginServlet
 */
@WebServlet(urlPatterns={"/UserActionServlet"})
public class UserActionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        String forwardToJsp = null;
        String action = request.getParameter("action");
        //Check the 'action' parameter to see what the user wants...
        if (action != null) {
            CommandFactory factory = new CommandFactory();
            Command command = factory.createCommand(action);
            forwardToJsp = command.executeCommand(request, response);
        }
        
        //Get the request dispatcher object and forward the request to the appropriate JSP page...
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardToJsp);
        dispatcher.forward(request, response);
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}