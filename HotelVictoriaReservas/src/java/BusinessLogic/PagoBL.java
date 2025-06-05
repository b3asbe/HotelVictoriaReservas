package BusinessLogic;

import BusinessEntity.PagoBE;
import DataAccessObject.PagoDAO;
import java.util.ArrayList;

public class PagoBL implements IBaseBL<PagoBE> {
    private PagoDAO pagoDAO = new PagoDAO();

    @Override
    public boolean create(PagoBE input) {
        return pagoDAO.Create(input);
    }

    @Override
    public PagoBE read(String id) {
        return pagoDAO.Read(id);
    }

    @Override
    public ArrayList<PagoBE> readAll() {
        return pagoDAO.ReadAll();
    }

    @Override
    public boolean update(PagoBE input) {
        return pagoDAO.Update(input);
    }

    @Override
    public boolean delete(String id) {
        return pagoDAO.Delete(id);
    }
}
