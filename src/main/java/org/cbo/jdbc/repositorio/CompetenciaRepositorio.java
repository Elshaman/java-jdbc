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

import java.sql.PreparedStatement;

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
        Competencia competencia = null;

        try (PreparedStatement preparedStatement = getConection().
                prepareStatement("SELECT c.*,p.nombre as programa FROM competencias AS c" +
                    " INNER JOIN programas AS p ON(c.programa_id = p.id) "   + 
                    " where c.id = ?")) {
                        preparedStatement.setLong(1, id);
                        try (ResultSet rs = preparedStatement.executeQuery()) {
                            if(rs.next()){
                                competencia = crearCompetencia(rs);    
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return competencia;
    }


    @Override
    public void eliminar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public void guardar(Competencia t) {
        String sql;
        if (t.getId()!= null && t.getId() > 0  ) {
            sql = "update competencias set nombre = ? ,  codigo=? , programa_id=? where id = ?";
        } else {
            sql = "insert into competencias(nombre,  codigo, programa_id) values(?,?,?)";
        } 
        try (PreparedStatement preparedStatement = getConection().prepareStatement(sql)){
            preparedStatement.setString(1, t.getNombre());
            preparedStatement.setString(2, t.getCodigo());
            preparedStatement.setLong(3, t.getPrograma().getId());
            if (t.getId()!= null && t.getId() > 0  ) {
                preparedStatement.setLong(4, t.getId());
            } 
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
