package org.cbo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.cbo.jdbc.util.ConexionBaseDatos;

public class EjemploJDBC {

    public static void main(String[] args) {
          
            try (Connection conn = ConexionBaseDatos.obtenerInstancia();
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
