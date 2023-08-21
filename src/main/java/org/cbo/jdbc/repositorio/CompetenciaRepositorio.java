package org.cbo.jdbc.repositorio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cbo.jdbc.modelo.Competencia;
import org.cbo.jdbc.modelo.Programa;
import org.cbo.jdbc.util.ConexionBaseDatos;

public class CompetenciaRepositorio implements Repositorio<Competencia> {

    private Connection getConection() throws SQLException {
        return ConexionBaseDatos.obtenerInstancia();
    }

    private Competencia crearCompetencia(ResultSet resultSet) throws SQLException {
        Competencia c = new Competencia();
        c.setNombre(resultSet.getString("nombre"));
        c.setCodigo(resultSet.getString("codigo"));
        Programa p  = new Programa();
        p.setId(resultSet.getLong("programa_id"));
        p.setNombre(resultSet.getString("programa"));
        c.setPrograma(p);
        return c;
    } 

    @Override
    public List<Competencia> listar() {
        List<Competencia> competencias = new ArrayList<>(); 
        try (Statement statement = getConection().
                createStatement();
            ResultSet resultSet = statement.
                executeQuery("SELECT c.*,p.nombre as programa FROM competencias AS c  INNER JOIN programas AS p ON(c.programa_id = p.id)")){

                    while(resultSet.next()){
                        Competencia competencia =crearCompetencia(resultSet);
                        competencias.add( competencia);
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }
        return competencias;        
    }

    @Override
    public Competencia porId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'porId'");
    }


    @Override
    public void eliminar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public void guardar(Competencia t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }
    
}
