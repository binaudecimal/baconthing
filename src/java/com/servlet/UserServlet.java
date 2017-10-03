package com.servlet;

import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.database.dataAccess.UserDAO;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserDAO userDAO;
    private User user;
    private HttpSession session;
    private static final String EM = "errorMessage";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            session = request.getSession();
            String action = request.getParameter("action");
            RequestDispatcher homeDispatcher = request.getRequestDispatcher("/jsp/home.jsp");
            RequestDispatcher userDispatcher = request.getRequestDispatcher("/jsp/user.jsp");
            
            String username, password, firstname, lastname, usertype;
            long id;
            boolean active;
            
            throwAway(request,response,session);
            
            switch (action) {
                case "getUserProfile":
                    userDispatcher.forward(request, response);
                    break;
                case "updateUserProfile":
                    System.out.println(request.getParameter("u"));
                    userDispatcher.forward(request,response);
                    break;
                case "updateUser":
                    username = request.getParameter("username");
                    password= request.getParameter("password");
                    firstname= request.getParameter("firstname");
                    lastname= request.getParameter("lastname");
                    usertype= request.getParameter("usertype");
                    id= Long.parseLong(request.getParameter("id").trim());
                    active= Boolean.parseBoolean(request.getParameter("active"));
                    String cpassword = request.getParameter("cpassword");
                    
                    if(!password.equals(cpassword)){
                        request.setAttribute(EM, "Password does not match");
                        //include current user object first before returning to user
                        userDispatcher.forward(request,response);
                    }
                    userDispatcher.forward(request,response);
                    break;
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void throwAway(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception{
        if(session!= null){
            if(session.getAttribute("user")== null){
                session.invalidate();
                request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
            }
        } 
    }
}
