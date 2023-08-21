package org.cbo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.cbo.jdbc.modelo.Competencia;
import org.cbo.jdbc.repositorio.CompetenciaRepositorio;
import org.cbo.jdbc.repositorio.Repositorio;
import org.cbo.jdbc.util.ConexionBaseDatos;

public class EjemploJDBC {

    public static void main(String[] args) {
          try(Connection conn = ConexionBaseDatos.obtenerInstancia()){
            Repositorio<Competencia> repositorio = new CompetenciaRepositorio();
            System.out.println("=============listar=============");
            repositorio.listar().forEach(p->System.out.println(p.toString()));
          }catch(SQLException e){
            e.printStackTrace();
          }
           
    }

   
    
}
