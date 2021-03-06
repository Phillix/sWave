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
import javax.servlet.annotation.MultipartConfig;
/*
    Functionality:
    Login
    Register
    Logout
    Upload
    Stream
    Search Songs
    Delete Songs
    Change Skin
*/

/**
 * Servlet implementation class checkLoginServlet
 */
@WebServlet(urlPatterns={"/UserActionServlet"})
//Only allow 100mb of uploading in a single request
//We accept files up to 55mb but filter out the ones over 16mb before it hits
//the database, this is only for making selecting a list of songs easier
@MultipartConfig(fileSizeThreshold=1024*1024*55, maxFileSize=1024*1024*55, maxRequestSize=1024*1024*100)
public class UserActionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserActionServlet() {
        super();
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
        if (forwardToJsp != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardToJsp);
            dispatcher.forward(request, response);
        }
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
