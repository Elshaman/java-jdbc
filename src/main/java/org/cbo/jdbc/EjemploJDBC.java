package org.cbo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.cbo.jdbc.modelo.Programa;
import org.cbo.jdbc.repositorio.ProgramaRepositorio;
import org.cbo.jdbc.repositorio.Repositorio;
import org.cbo.jdbc.util.ConexionBaseDatos;

public class EjemploJDBC {

    public static void main(String[] args) {
          try(Connection conn = ConexionBaseDatos.obtenerInstancia()){
            Repositorio<Programa> repositorio = new ProgramaRepositorio();
            repositorio.listar().forEach(p->System.out.println(p.getNombre()));
            System.out.println(repositorio.porId(1L));
          }catch(SQLException e){
            e.printStackTrace();
          }
           
    }

   
    
}
