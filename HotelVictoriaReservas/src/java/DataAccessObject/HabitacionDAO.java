package DataAccessObject;

import BusinessEntity.HabitacionBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

public class HabitacionDAO extends ConexionMySQL implements IBaseDAO<HabitacionBE> {

    @Override
    public boolean Create(HabitacionBE input) {
        String sql = "INSERT INTO HABITACION (numero, piso, tipo_id, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al crear habitación: Conexión no disponible.");
                 return false;
            }
            
            ps.setString(1, input.getNumero());
            ps.setInt(2, input.getPiso());
            ps.setInt(3, input.getTipo_id());
            ps.setString(4, input.getEstado());
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    input.setHabitacion_id(generatedKeys.getInt(1));
                } else {
                    System.err.println("Error al crear habitación: No se obtuvo el ID generado.");
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear habitación: " + e.getMessage());

            return false;
        }
    }

    @Override
    public HabitacionBE Read(String idInput) {
        HabitacionBE habitacion = null;

        String sql = "SELECT habitacion_id, numero, piso, tipo_id, estado FROM HABITACION WHERE habitacion_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al leer habitación: Conexión no disponible.");
                 return null;
            }
            
            ps.setInt(1, Integer.parseInt(idInput));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    habitacion = new HabitacionBE();
                    habitacion.setHabitacion_id(rs.getInt("habitacion_id"));
                    habitacion.setNumero(rs.getString("numero"));
                    habitacion.setPiso(rs.getInt("piso"));
                    habitacion.setTipo_id(rs.getInt("tipo_id"));
                    habitacion.setEstado(rs.getString("estado"));


                }
            }
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al leer habitación (ID: " + idInput + "): " + e.getMessage());
        }
        return habitacion;
    }

    @Override
    public ArrayList<HabitacionBE> ReadAll() {
        ArrayList<HabitacionBE> lista = new ArrayList<>();
        String sql = "SELECT habitacion_id, numero, piso, tipo_id, estado FROM HABITACION";
        try (Connection conn = getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al leer todas las habitaciones: Conexión no disponible.");
                 return lista;
            }
            
            while (rs.next()) {
                HabitacionBE habitacion = new HabitacionBE();
                habitacion.setHabitacion_id(rs.getInt("habitacion_id"));
                habitacion.setNumero(rs.getString("numero"));
                habitacion.setPiso(rs.getInt("piso"));
                habitacion.setTipo_id(rs.getInt("tipo_id"));
                habitacion.setEstado(rs.getString("estado"));
                lista.add(habitacion);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer todas las habitaciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(HabitacionBE input) {
        String sql = "UPDATE HABITACION SET numero = ?, piso = ?, tipo_id = ?, estado = ? WHERE habitacion_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al actualizar habitación: Conexión no disponible.");
                 return false;
            }
            
            ps.setString(1, input.getNumero());
            ps.setInt(2, input.getPiso());
            ps.setInt(3, input.getTipo_id());
            ps.setString(4, input.getEstado());
            ps.setInt(5, input.getHabitacion_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar habitación (ID: " + input.getHabitacion_id() + "): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(String idInput) {
        String sql = "DELETE FROM HABITACION WHERE habitacion_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al eliminar habitación: Conexión no disponible.");
                 return false;
            }

            ps.setInt(1, Integer.parseInt(idInput));
            return ps.executeUpdate() > 0;
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al eliminar habitación (ID: " + idInput + "): " + e.getMessage());
            return false;
        }
    }
}