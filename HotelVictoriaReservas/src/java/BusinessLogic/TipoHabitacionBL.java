package BusinessLogic;

import BusinessEntity.TipoHabitacionBE;
import DataAccessObject.TipoHabitacionDAO;
import java.util.ArrayList;

public class TipoHabitacionBL implements IBaseBL<TipoHabitacionBE> {
    private TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();

    @Override
    public boolean create(TipoHabitacionBE input) {
        return tipoHabitacionDAO.Create(input);
    }

    @Override
    public TipoHabitacionBE read(String id) {
        return tipoHabitacionDAO.Read(id);
    }

    @Override
    public ArrayList<TipoHabitacionBE> readAll() {
        return tipoHabitacionDAO.ReadAll();
    }

    @Override
    public boolean update(TipoHabitacionBE input) {
        return tipoHabitacionDAO.Update(input);
    }

    @Override
    public boolean delete(String id) {
        return tipoHabitacionDAO.Delete(id);
    }
}
