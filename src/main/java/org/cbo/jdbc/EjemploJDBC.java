package org.cbo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemploJDBC {

    public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3310/bansaa";
            String username = "root";
            String password = "admin";
            try (Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement =  conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * from competencias")){
                
                while(resultSet.next()){
                    System.out.println(resultSet.getString("nombre"));
                    
                }
               
            } catch (SQLException e) {
                System.out.println(e.getMessage());    
            }
    }

   
    
}
