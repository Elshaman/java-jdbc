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

        try (Connection connection = getConection();
             Statement statement = connection.createStatement();
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
       try(Connection connection = getConection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM programas WHERE id = ?")){
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
        String sql;
        if (t.getId()!= null && t.getId() > 0  ) {
            sql = "update programas set nombre = ? , version = ? , codigo=? , estado =? where id = ?";
        } else {
            sql = "insert into programas(nombre, version, codigo, estado) values(?,?,?,?)";
        } 
        try (
            Connection connection = getConection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, t.getNombre());
            preparedStatement.setString(2, t.getVersion());
            preparedStatement.setString(3, t.getCodigo());
            preparedStatement.setString(4, t.getEstado());
            if (t.getId()!= null && t.getId() > 0  ) {
                preparedStatement.setLong(5, t.getId());
            } 
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
       try (        Connection connection = getConection();
                    PreparedStatement stmt = connection.
                    prepareStatement("DELETE FROM programas WHERE id=?")){
                        stmt.setLong(1, id);
                        stmt.executeUpdate();
       } catch (SQLException e) {
        e.printStackTrace();
       }
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
