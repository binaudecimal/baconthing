package com.servlet;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.database.dbutil.DbUtil;
import java.sql.Connection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String EM = "errorMessage";
    DbUtil dbUtil = new DbUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "login":
                    //get username password,
                    //check if any is null
                    String username = request.getParameter("username").trim();
                    String password = request.getParameter("password").trim();
                    if (username == "" || password == "") {
                        request.setAttribute(EM, "One of the fields is null");
                        request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
                    } else {
                        //must be correct input
                        //Insert DAO operation to get user inforamtion here\

                        request.getSession().setAttribute("user", new User(username, password));
                        Connection connection = dbUtil.getConnection();

                        if (connection != null) {
                            request.getSession().setAttribute("connection", connection);
                            request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
                        } else {
                            request.setAttribute(EM, "Unable to Login");
                            request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
                        }
                    }
                    break;
                default:
                    request.setAttribute("errorMessage", "Unable to Process Request");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try{
            if(request.getSession().getAttribute("user")==null){
                request.setAttribute(EM, "User is not Logged in");
            }
            Connection connection = (Connection)(request.getSession().getAttribute("connection"));
            if(connection != null) connection.close();
            request.getSession().invalidate();
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
