package org.cbo.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static String url = "jdbc:mysql://localhost:3310/bansaa";
    private static String username = "root";
    private static String password = "admin";
    private static Connection connection;

    public static Connection obtenerInstancia() throws SQLException{
        
            return  DriverManager.getConnection(url, username, password);
        
    }

}
