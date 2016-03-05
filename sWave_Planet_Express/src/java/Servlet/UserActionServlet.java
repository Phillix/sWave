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
*/

/**
 * Servlet implementation class checkLoginServlet
 */
@WebServlet(urlPatterns={"/UserActionServlet"})
//Only allow files under 16mb to be uploaded and no more than 100mb of uploading in a single request
@MultipartConfig(location="/tmp", fileSizeThreshold=16777216, maxFileSize=16777216, maxRequestSize=104857600)
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