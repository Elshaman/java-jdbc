package org.cbo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.cbo.jdbc.modelo.Competencia;
import org.cbo.jdbc.modelo.Programa;
import org.cbo.jdbc.repositorio.CompetenciaRepositorio;
import org.cbo.jdbc.repositorio.ProgramaRepositorio;
import org.cbo.jdbc.repositorio.Repositorio;
import org.cbo.jdbc.util.ConexionBaseDatos;

public class EjemploJDBC {

    public static void main(String[] args) {
         
            CompetenciaRepositorio repositorio = new CompetenciaRepositorio();
            ProgramaRepositorio repoprogr = new ProgramaRepositorio();

            System.out.println("=============listar=============");
            repositorio.listar().forEach(p->System.out.println(p.toString()));
            System.out.println("=============listar por id=============");
            Competencia c = repositorio.porId(1L);
            System.out.println(c);
            System.out.println("=============guardar=============");
            Competencia c1 = new Competencia();
            c1.setNombre("EVALUAR PROPUESTA TECNICA");
            c1.setCodigo("234567");
            Programa p = repoprogr.porId(1L);
            c1.setPrograma(p);
            repositorio.guardar(c1);
            System.out.println("=============eliminar=============");
            repositorio.eliminar(18L);
            System.out.println("=============sctualizar=============");
            /*Competencia c2 = new Competencia();
            c2.setId(1L);
            c2.setNombre("CULIAR RICO CON NANO");
            c2.setPrograma());
            repositorio.guardar(c2);*/
         
           
    }

   
    
}
