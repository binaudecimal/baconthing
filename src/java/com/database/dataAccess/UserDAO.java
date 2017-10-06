package com.database.dataAccess;

import java.sql.Connection;
import com.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDAO {
    private Connection connection;
    private User user;
    private PreparedStatement ps;
    
    private static final String ID = "ID";
    private static final String UN = "username";
    private static final String PW = "password";
    private static final String FN = "firstname";
    private static final String LN = "lastname";
    private static final String UT = "usertype";
    private static final String UA = "active";
    
    

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public LinkedList<User> getUser(String username) throws SQLException, Exception{
        LinkedList<User> result = new LinkedList<User>();
        ps = connection.prepareStatement("SELECT * from UserDb where username = ?");
        ps.setString(1, username);
        result = toList(ps.executeQuery());
        ps.close();
        
        return result;
    }
    
    public LinkedList<User> viewUsers(String username) throws SQLException, Exception{
        LinkedList<User> result = new LinkedList<User>();
        ps = connection.prepareStatement("SELECT * from UserDb");
        result = toList(ps.executeQuery());
        ps.close();
        
        return result;
    }
    
    public LinkedList<User> toList(ResultSet rs) throws Exception{
        LinkedList<User> result = new LinkedList<User>();
        while(rs.next()){
            User user = new User(
                rs.getString(UN),
                rs.getString(PW),
                rs.getString(FN),
                rs.getString(PW),
                rs.getString(UT),
                rs.getLong(ID), 
                rs.getBoolean(UA)
            );
            result.push(user);
        }
        return result;
    }
    
}
