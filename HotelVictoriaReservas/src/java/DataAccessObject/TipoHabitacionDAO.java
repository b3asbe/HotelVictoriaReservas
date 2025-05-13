package DataAccessObject;

import BusinessEntity.TipoHabitacionBE;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TipoHabitacionDAO extends ConexionMySQL implements IBaseDAO<TipoHabitacionBE> {

    @Override
    public boolean Create(TipoHabitacionBE input) {
        String sql = "INSERT INTO TIPO_HABITACION (nombre_tipo, descripcion, precio_noche) VALUES (?, ?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, input.getNombre_tipo());
            ps.setString(2, input.getDescripcion());
            ps.setBigDecimal(3, input.getPrecio_noche());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    input.setTipo_id(generatedKeys.getInt(1));
                } else {
     
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear tipo de habitación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public TipoHabitacionBE Read(String id) {
        TipoHabitacionBE tipoHabitacion = null;
        String sql = "SELECT tipo_id, nombre_tipo, descripcion, precio_noche FROM TIPO_HABITACION WHERE tipo_id = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id)); // Convertir String a int
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tipoHabitacion = new TipoHabitacionBE();
                    tipoHabitacion.setTipo_id(rs.getInt("tipo_id"));
                    tipoHabitacion.setNombre_tipo(rs.getString("nombre_tipo"));
                    tipoHabitacion.setDescripcion(rs.getString("descripcion"));
                    tipoHabitacion.setPrecio_noche(rs.getBigDecimal("precio_noche"));
                }
            }
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al leer tipo de habitación: " + e.getMessage());
        }
        return tipoHabitacion;
    }

    @Override
    public ArrayList<TipoHabitacionBE> ReadAll() {
        ArrayList<TipoHabitacionBE> lista = new ArrayList<>();
        String sql = "SELECT tipo_id, nombre_tipo, descripcion, precio_noche FROM TIPO_HABITACION";
        try (Statement stmt = getConexion().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TipoHabitacionBE tipoHabitacion = new TipoHabitacionBE();
                tipoHabitacion.setTipo_id(rs.getInt("tipo_id"));
                tipoHabitacion.setNombre_tipo(rs.getString("nombre_tipo"));
                tipoHabitacion.setDescripcion(rs.getString("descripcion"));
                tipoHabitacion.setPrecio_noche(rs.getBigDecimal("precio_noche"));
                lista.add(tipoHabitacion);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer todos los tipos de habitación: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(TipoHabitacionBE input) {
        String sql = "UPDATE TIPO_HABITACION SET nombre_tipo = ?, descripcion = ?, precio_noche = ? WHERE tipo_id = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, input.getNombre_tipo());
            ps.setString(2, input.getDescripcion());
            ps.setBigDecimal(3, input.getPrecio_noche());
            ps.setInt(4, input.getTipo_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar tipo de habitación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(String id) { 
        String sql = "DELETE FROM TIPO_HABITACION WHERE tipo_id = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id)); // Convertir String a int
            return ps.executeUpdate() > 0;
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al eliminar tipo de habitación: " + e.getMessage());
            return false;
        }
    }
}