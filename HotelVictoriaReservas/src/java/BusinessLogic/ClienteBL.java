package BusinessLogic;

import BusinessEntity.ClienteBE;
import DataAccessObject.ClienteDAO;
import java.util.ArrayList;

public class ClienteBL implements IBaseBL<ClienteBE> {
    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public boolean create(ClienteBE input) {
        return clienteDAO.Create(input);
    }

    @Override
    public ClienteBE read(String id) {
        return clienteDAO.Read(id);
    }

    @Override
    public ArrayList<ClienteBE> readAll() {
        return clienteDAO.ReadAll();
    }

    @Override
    public boolean update(ClienteBE input) {
        return clienteDAO.Update(input);
    }

    @Override
    public boolean delete(String id) {
        return clienteDAO.Delete(id);
    }
}
