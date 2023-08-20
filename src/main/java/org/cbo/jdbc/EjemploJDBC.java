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
            System.out.println("=============listar=============");
            repositorio.listar().forEach(p->System.out.println(p.getNombre()));
            System.out.println("=============listar por id=============");
            System.out.println(repositorio.porId(1L));
            System.out.println("=============INSERTAR============="); 
            Programa p = new Programa("TECNICO EN HIGIENE" , "22346567" , "2" , "FORMULADO");
            repositorio.guardar(p);
            System.out.println("mensaje guardado con exito");
            System.out.println("=============ACTUALzIAR=============");
            Programa pupdate = new Programa();
            pupdate.setId(3L);
            pupdate.setNombre("HIGIENISTA");
            repositorio.guardar(pupdate);
            System.out.println("=============ELIMINAR=============");
           
            repositorio.eliminar(3L);

          }catch(SQLException e){
            e.printStackTrace();
          }
           
    }

   
    
}
