package DataAccessObject;

import BusinessEntity.ReservaBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Connection;

public class ReservaDAO extends ConexionMySQL implements IBaseDAO<ReservaBE> {

    @Override
    public boolean Create(ReservaBE input) {
        String sql = "INSERT INTO RESERVA (cliente_id, habitacion_id, usuario_id, fecha_inicio, fecha_fin, fecha_reserva, estado_reserva, numero_huespedes) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al crear reserva: Conexión no disponible.");
                 return false;
            }
            
            ps.setString(1, input.getCliente_id());
            ps.setInt(2, input.getHabitacion_id());
            ps.setString(3, input.getUsuario_id());
            ps.setDate(4, Date.valueOf(input.getFecha_inicio()));
            ps.setDate(5, Date.valueOf(input.getFecha_fin()));
            ps.setTimestamp(6, Timestamp.valueOf(input.getFecha_reserva() != null ? input.getFecha_reserva() : java.time.LocalDateTime.now()));
            ps.setString(7, input.getEstado_reserva());
            ps.setInt(8, input.getNumero_huespedes());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    input.setReserva_id(generatedKeys.getInt(1));
                } else {
                     System.err.println("Error al crear reserva: No se obtuvo el ID generado.");
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear reserva: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ReservaBE Read(String idInput) {
        ReservaBE reserva = null;
        String sql = "SELECT reserva_id, cliente_id, habitacion_id, usuario_id, fecha_inicio, fecha_fin, " +
                     "fecha_reserva, estado_reserva, numero_huespedes FROM RESERVA WHERE reserva_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al leer reserva: Conexión no disponible.");
                 return null;
            }
            
            ps.setInt(1, Integer.parseInt(idInput));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    reserva = new ReservaBE();
                    reserva.setReserva_id(rs.getInt("reserva_id"));
                    reserva.setCliente_id(rs.getString("cliente_id"));
                    reserva.setHabitacion_id(rs.getInt("habitacion_id"));
                    reserva.setUsuario_id(rs.getString("usuario_id"));
                    
                    Date fechaInicioDb = rs.getDate("fecha_inicio");
                    if(fechaInicioDb != null) reserva.setFecha_inicio(fechaInicioDb.toLocalDate());
                    
                    Date fechaFinDb = rs.getDate("fecha_fin");
                    if(fechaFinDb != null) reserva.setFecha_fin(fechaFinDb.toLocalDate());
                    
                    Timestamp fechaReservaDb = rs.getTimestamp("fecha_reserva");
                    if(fechaReservaDb != null) reserva.setFecha_reserva(fechaReservaDb.toLocalDateTime());
                    
                    reserva.setEstado_reserva(rs.getString("estado_reserva"));
                    reserva.setNumero_huespedes(rs.getInt("numero_huespedes"));
                }
            }
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al leer reserva (ID: " + idInput + "): " + e.getMessage());
        }
        return reserva;
    }

    @Override
    public ArrayList<ReservaBE> ReadAll() {
        ArrayList<ReservaBE> lista = new ArrayList<>();
        String sql = "SELECT reserva_id, cliente_id, habitacion_id, usuario_id, fecha_inicio, fecha_fin, " +
                     "fecha_reserva, estado_reserva, numero_huespedes FROM RESERVA";
        try (Connection conn = getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al leer todas las reservas: Conexión no disponible.");
                 return lista;
            }
            
            while (rs.next()) {
                ReservaBE reserva = new ReservaBE();
                reserva.setReserva_id(rs.getInt("reserva_id"));
                reserva.setCliente_id(rs.getString("cliente_id"));
                reserva.setHabitacion_id(rs.getInt("habitacion_id"));
                reserva.setUsuario_id(rs.getString("usuario_id"));

                Date fechaInicioDb = rs.getDate("fecha_inicio");
                if(fechaInicioDb != null) reserva.setFecha_inicio(fechaInicioDb.toLocalDate());
                
                Date fechaFinDb = rs.getDate("fecha_fin");
                if(fechaFinDb != null) reserva.setFecha_fin(fechaFinDb.toLocalDate());
                
                Timestamp fechaReservaDb = rs.getTimestamp("fecha_reserva");
                if(fechaReservaDb != null) reserva.setFecha_reserva(fechaReservaDb.toLocalDateTime());

                reserva.setEstado_reserva(rs.getString("estado_reserva"));
                reserva.setNumero_huespedes(rs.getInt("numero_huespedes"));
                lista.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer todas las reservas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(ReservaBE input) {
        String sql = "UPDATE RESERVA SET cliente_id = ?, habitacion_id = ?, usuario_id = ?, fecha_inicio = ?, " +
                     "fecha_fin = ?, estado_reserva = ?, numero_huespedes = ? WHERE reserva_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al actualizar reserva: Conexión no disponible.");
                 return false;
            }
            
            ps.setString(1, input.getCliente_id());
            ps.setInt(2, input.getHabitacion_id());
            ps.setString(3, input.getUsuario_id());
            ps.setDate(4, Date.valueOf(input.getFecha_inicio()));
            ps.setDate(5, Date.valueOf(input.getFecha_fin()));
            ps.setString(6, input.getEstado_reserva());
            ps.setInt(7, input.getNumero_huespedes());
            ps.setInt(8, input.getReserva_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar reserva (ID: " + input.getReserva_id() + "): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(String idInput) {
        String sql = "DELETE FROM RESERVA WHERE reserva_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al eliminar reserva: Conexión no disponible.");
                 return false;
            }
            
            ps.setInt(1, Integer.parseInt(idInput));
            return ps.executeUpdate() > 0;
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al eliminar reserva (ID: " + idInput + "): " + e.getMessage());
            return false;
        }
    }
}