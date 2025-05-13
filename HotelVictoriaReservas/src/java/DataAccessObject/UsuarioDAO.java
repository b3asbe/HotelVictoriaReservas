package DataAccessObject;

import BusinessEntity.UsuarioBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import java.sql.Connection;

public class UsuarioDAO extends ConexionMySQL implements IBaseDAO<UsuarioBE> {
    @Override
    public boolean Create(UsuarioBE input) {
        input.setUsuario_id(UUID.randomUUID().toString());

        String sql = "INSERT INTO USUARIO (usuario_id, usuario, clave_hash, rol) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al crear usuario: Conexión no disponible.");
                 return false;
            }
            ps.setString(1, input.getUsuario_id());
            ps.setString(2, input.getUsuario());
            ps.setString(3, input.getClave_hash());
            ps.setString(4, input.getRol());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
            return false;
        }
    }
    @Override
    public UsuarioBE Read(String usuarioId) {
        UsuarioBE usuario = null;
        String sql = "SELECT usuario_id, usuario, clave_hash, rol FROM USUARIO WHERE usuario_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al leer usuario: Conexión no disponible.");
                 return null;
            }
            
            ps.setString(1, usuarioId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new UsuarioBE();
                    usuario.setUsuario_id(rs.getString("usuario_id"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setClave_hash(rs.getString("clave_hash"));
                    usuario.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al leer usuario (ID: " + usuarioId + "): " + e.getMessage());
        }
        return usuario;
    }

    @Override
    public ArrayList<UsuarioBE> ReadAll() {
        ArrayList<UsuarioBE> lista = new ArrayList<>();
        String sql = "SELECT usuario_id, usuario, clave_hash, rol FROM USUARIO";
        try (Connection conn = getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al leer todos los usuarios: Conexión no disponible.");
                 return lista;
            }
            
            while (rs.next()) {
                UsuarioBE usuario = new UsuarioBE();
                usuario.setUsuario_id(rs.getString("usuario_id"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setClave_hash(rs.getString("clave_hash"));
                usuario.setRol(rs.getString("rol"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer todos los usuarios: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(UsuarioBE input) {
        String sql = "UPDATE USUARIO SET usuario = ?, clave_hash = ?, rol = ? WHERE usuario_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al actualizar usuario: Conexión no disponible.");
                 return false;
            }

            ps.setString(1, input.getUsuario());
            ps.setString(2, input.getClave_hash());
            ps.setString(3, input.getRol());
            ps.setString(4, input.getUsuario_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario (ID: " + input.getUsuario_id() + "): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(String usuarioId) {
        String sql = "DELETE FROM USUARIO WHERE usuario_id = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                 System.err.println("Error al eliminar usuario: Conexión no disponible.");
                 return false;
            }
            
            ps.setString(1, usuarioId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario (ID: " + usuarioId + "): " + e.getMessage());
            return false;
        }
    }
}