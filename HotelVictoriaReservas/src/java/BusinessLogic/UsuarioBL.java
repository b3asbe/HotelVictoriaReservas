package BusinessLogic;

import BusinessEntity.UsuarioBE;
import DataAccessObject.UsuarioDAO;
import java.util.ArrayList;

public class UsuarioBL implements IBaseBL<UsuarioBE> {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public boolean create(UsuarioBE input) {
        return usuarioDAO.Create(input);
    }

    @Override
    public UsuarioBE read(String id) {
        return usuarioDAO.Read(id);
    }

    @Override
    public ArrayList<UsuarioBE> readAll() {
        return usuarioDAO.ReadAll();
    }

    @Override
    public boolean update(UsuarioBE input) {
        return usuarioDAO.Update(input);
    }

    @Override
    public boolean delete(String id) {
        return usuarioDAO.Delete(id);
    }
}
