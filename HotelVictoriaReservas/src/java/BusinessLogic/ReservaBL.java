package BusinessLogic;

import BusinessEntity.ReservaBE;
import DataAccessObject.ReservaDAO;
import java.util.ArrayList;

public class ReservaBL implements IBaseBL<ReservaBE> {
    private ReservaDAO reservaDAO = new ReservaDAO();

    @Override
    public boolean create(ReservaBE input) {
        return reservaDAO.Create(input);
    }

    @Override
    public ReservaBE read(String id) {
        return reservaDAO.Read(id);
    }

    @Override
    public ArrayList<ReservaBE> readAll() {
        return reservaDAO.ReadAll();
    }

    @Override
    public boolean update(ReservaBE input) {
        return reservaDAO.Update(input);
    }

    @Override
    public boolean delete(String id) {
        return reservaDAO.Delete(id);
    }
}
