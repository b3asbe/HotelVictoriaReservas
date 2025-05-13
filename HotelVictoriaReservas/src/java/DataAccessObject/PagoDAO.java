package DataAccessObject;

import BusinessEntity.PagoBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Connection;

public class PagoDAO extends ConexionMySQL implements IBaseDAO<PagoBE> {

    @Override
    public boolean Create(PagoBE input) {
        String sql = "INSERT INTO PAGO (reserva_id, fecha_pago, monto, metodo_pago, estado_pago) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al crear pago: Conexión no disponible.");
                 return false;
            }

            ps.setInt(1, input.getReserva_id());
            ps.setTimestamp(2, Timestamp.valueOf(input.getFecha_pago()));
            ps.setBigDecimal(3, input.getMonto());
            ps.setString(4, input.getMetodo_pago());
            ps.setString(5, input.getEstado_pago());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    input.setPago_id(generatedKeys.getInt(1));
                } else {
                    System.err.println("Error al crear pago: No se obtuvo el ID generado.");
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear pago: " + e.getMessage());
            return false;
        }
    }

    @Override
    public PagoBE Read(String idInput) {
        PagoBE pago = null;
        String sql = "SELECT pago_id, reserva_id, fecha_pago, monto, metodo_pago, estado_pago FROM PAGO WHERE pago_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al leer pago: Conexión no disponible.");
                 return null;
            }

            ps.setInt(1, Integer.parseInt(idInput));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pago = new PagoBE();
                    pago.setPago_id(rs.getInt("pago_id"));
                    pago.setReserva_id(rs.getInt("reserva_id"));
                    
                    Timestamp fechaPagoDb = rs.getTimestamp("fecha_pago");
                    if (fechaPagoDb != null) pago.setFecha_pago(fechaPagoDb.toLocalDateTime());
                    
                    pago.setMonto(rs.getBigDecimal("monto"));
                    pago.setMetodo_pago(rs.getString("metodo_pago"));
                    pago.setEstado_pago(rs.getString("estado_pago"));
                }
            }
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al leer pago (ID: " + idInput + "): " + e.getMessage());
        }
        return pago;
    }

    @Override
    public ArrayList<PagoBE> ReadAll() {
        ArrayList<PagoBE> lista = new ArrayList<>();
        String sql = "SELECT pago_id, reserva_id, fecha_pago, monto, metodo_pago, estado_pago FROM PAGO";
        try (Connection conn = getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al leer todos los pagos: Conexión no disponible.");
                 return lista;
            }

            while (rs.next()) {
                PagoBE pago = new PagoBE();
                pago.setPago_id(rs.getInt("pago_id"));
                pago.setReserva_id(rs.getInt("reserva_id"));

                Timestamp fechaPagoDb = rs.getTimestamp("fecha_pago");
                if (fechaPagoDb != null) pago.setFecha_pago(fechaPagoDb.toLocalDateTime());
                
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setMetodo_pago(rs.getString("metodo_pago"));
                pago.setEstado_pago(rs.getString("estado_pago"));
                lista.add(pago);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer todos los pagos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(PagoBE input) {
        String sql = "UPDATE PAGO SET reserva_id = ?, fecha_pago = ?, monto = ?, metodo_pago = ?, estado_pago = ? WHERE pago_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al actualizar pago: Conexión no disponible.");
                 return false;
            }

            ps.setInt(1, input.getReserva_id());
            ps.setTimestamp(2, Timestamp.valueOf(input.getFecha_pago()));
            ps.setBigDecimal(3, input.getMonto());
            ps.setString(4, input.getMetodo_pago());
            ps.setString(5, input.getEstado_pago());
            ps.setInt(6, input.getPago_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar pago (ID: " + input.getPago_id() + "): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(String idInput) {
        String sql = "DELETE FROM PAGO WHERE pago_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al eliminar pago: Conexión no disponible.");
                 return false;
            }

            ps.setInt(1, Integer.parseInt(idInput));
            return ps.executeUpdate() > 0;
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al eliminar pago (ID: " + idInput + "): " + e.getMessage());
            return false;
        }
    }
}