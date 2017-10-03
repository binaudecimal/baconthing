package com.database.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String DB_URL = "jdbc:derby://localhost:1527/LabExam";
    
    static final String USER = "bacon";
    static final String PASS = "burger";
    
    Connection conn = null;
    
    public Connection getConnection() {
       try
        {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("################Connection Successfully made!###########################");
        }catch(SQLException se){
                 se.printStackTrace();
                }catch(Exception e){
                    e.printStackTrace();
                }
       return conn;
    }
}
