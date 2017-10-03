package com.servlet;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

import com.model.User;
import com.database.dbutil.DbUtil;
import com.database.dataAccess.UserDAO;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String EM = "errorMessage";
    DbUtil dbUtil = new DbUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String action = request.getParameter("action");
            RequestDispatcher indexDispatcher = request.getRequestDispatcher("/jsp/index.jsp");
            RequestDispatcher homeDispatcher = request.getRequestDispatcher("/jsp/home.jsp");

            switch (action) {
                case "login":
                    //get username password,
                    //check if any is null
                    HttpSession session = request.getSession();
                    String username = request.getParameter("username").trim().toLowerCase();
                    String password = request.getParameter("password").trim();
                    if (username == "" || password == "") {
                        request.setAttribute(EM, "One of the fields is null");
                        request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
                    } else {
                        Connection connection = dbUtil.getConnection();
                        if (connection != null) {
                            UserDAO userDAO = new UserDAO(connection);
                            LinkedList<User> users = userDAO.getUser(username);
                            if (users.size() > 1) {
                                //multiple usernames found
                                request.setAttribute(EM, "Server Error, Unable to Login");
                                indexDispatcher.forward(request, response);
                            } else if (users.size() == 0) {
                                //username not found
                                System.out.println("user not found");
                                request.setAttribute(EM, "Invalid Username/Password");
                                request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
                            } else {
                                User user = users.pop();
                                System.out.println(user);
                                if (!user.getPassword().trim().equals(password)) {
                                    //incorrect password
                                    System.out.println("Incorrect password");
                                    request.setAttribute(EM, "Invalid Username/Password");
                                    indexDispatcher.forward(request, response);
                                }
                                session.setAttribute("user", user);
                                session.setAttribute("connection", connection);
                                request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
                            }

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
        try {
            if (request.getSession().getAttribute("user") == null) {
                request.setAttribute(EM, "User is not Logged in");
            }
            Connection connection = (Connection) (request.getSession().getAttribute("connection"));
            if (connection != null) {
                connection.close();
            }
            request.getSession().invalidate();
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
