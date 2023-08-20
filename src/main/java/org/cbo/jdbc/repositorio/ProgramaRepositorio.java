package org.cbo.jdbc.repositorio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.cbo.jdbc.modelo.Programa;
import org.cbo.jdbc.util.ConexionBaseDatos;

public class ProgramaRepositorio implements Repositorio<Programa> {

    private Connection getConection() throws SQLException {
        return ConexionBaseDatos.obtenerInstancia();
    }

    @Override
    public List<Programa> listar() {
        List<Programa> programas = new ArrayList<>();
        try (Statement statement = getConection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM programas")) {
                while(resultSet.next()){
                    Programa p = crearPrograma(resultSet);
                    programas.add( p);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return programas;
    }


    @Override
    public Programa porId(Long id) {
       Programa programa = null;
       try(PreparedStatement statement = getConection().prepareStatement("SELECT * FROM programas WHERE id = ?")){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                programa = crearPrograma(rs);
            }
            rs.close();
       }catch(SQLException e){
            e.printStackTrace();
       }
       return programa;
    }

    @Override
    public void guardar(Programa t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    private Programa crearPrograma(ResultSet resultSet) throws SQLException {
        Programa p = new Programa();
        p.setId(resultSet.getLong("id"));
        p.setNombre(resultSet.getString("nombre"));
        p.setCodigo(resultSet.getString("codigo"));
        p.setVersion(resultSet.getString("version"));
        p.setEstado(resultSet.getString("estado"));
        return p;
    }

}
