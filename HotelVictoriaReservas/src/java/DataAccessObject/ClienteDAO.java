package DataAccessObject;

import BusinessEntity.ClienteBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;
import java.sql.Connection;

public class ClienteDAO extends ConexionMySQL implements IBaseDAO<ClienteBE> {

    @Override
    public boolean Create(ClienteBE input) {
        input.setCliente_id(UUID.randomUUID().toString());

        String sql = "INSERT INTO CLIENTE (cliente_id, nombre, apellido, email, telefono, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al crear cliente: Conexión no disponible.");
                 return false;
            }
            
            ps.setString(1, input.getCliente_id());
            ps.setString(2, input.getNombre());
            ps.setString(3, input.getApellido());
            ps.setString(4, input.getEmail());
            ps.setString(5, input.getTelefono());
            ps.setTimestamp(6, Timestamp.valueOf(input.getFecha_registro() != null ? input.getFecha_registro() : java.time.LocalDateTime.now()));
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ClienteBE Read(String clienteId) {
        ClienteBE cliente = null;
        String sql = "SELECT cliente_id, nombre, apellido, email, telefono, fecha_registro FROM CLIENTE WHERE cliente_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al leer cliente: Conexión no disponible.");
                 return null;
            }
            
            ps.setString(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClienteBE();
                    cliente.setCliente_id(rs.getString("cliente_id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefono(rs.getString("telefono"));
                    Timestamp fechaRegistroTs = rs.getTimestamp("fecha_registro");
                    if (fechaRegistroTs != null) {
                        cliente.setFecha_registro(fechaRegistroTs.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al leer cliente (ID: " + clienteId + "): " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public ArrayList<ClienteBE> ReadAll() {
        ArrayList<ClienteBE> lista = new ArrayList<>();
        String sql = "SELECT cliente_id, nombre, apellido, email, telefono, fecha_registro FROM CLIENTE";
        try (Connection conn = getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al leer todos los clientes: Conexión no disponible.");
                 return lista;
            }
            
            while (rs.next()) {
                ClienteBE cliente = new ClienteBE();
                cliente.setCliente_id(rs.getString("cliente_id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                Timestamp fechaRegistroTs = rs.getTimestamp("fecha_registro");
                if (fechaRegistroTs != null) {
                    cliente.setFecha_registro(fechaRegistroTs.toLocalDateTime());
                }
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer todos los clientes: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(ClienteBE input) {
        String sql = "UPDATE CLIENTE SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE cliente_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al actualizar cliente: Conexión no disponible.");
                 return false;
            }
            
            ps.setString(1, input.getNombre());
            ps.setString(2, input.getApellido());
            ps.setString(3, input.getEmail());
            ps.setString(4, input.getTelefono());
            ps.setString(5, input.getCliente_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente (ID: " + input.getCliente_id() + "): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(String clienteId) {
        String sql = "DELETE FROM CLIENTE WHERE cliente_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al eliminar cliente: Conexión no disponible.");
                 return false;
            }
            
            ps.setString(1, clienteId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}