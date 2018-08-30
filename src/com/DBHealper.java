package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHealper {
	static final String driver = "com.mysql.jdbc.Driver";  
	   static final String db = "jdbc:mysql://localhost:3306/timetempdata"; 
	   static final String user = "root";
	   static final String pass = "root";
	   
	   
	 public Connection getConnection() { 
    	 Connection connection = null;
        try{
            Class.forName(driver); 
            connection = DriverManager.getConnection(db, user, pass);               
        } 
        catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
        }
		return connection;   
    } 


public void closeConnection(Connection connection) { 
	try
    { if(connection != null)
            connection.close();
    
    } catch (Exception e) {
    	e.printStackTrace();
    } 
}



}
