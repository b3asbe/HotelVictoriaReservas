package BusinessLogic;

import BusinessEntity.HabitacionBE;
import DataAccessObject.HabitacionDAO;
import java.util.ArrayList;

public class HabitacionBL implements IBaseBL<HabitacionBE> {
    private HabitacionDAO habitacionDAO = new HabitacionDAO();

    @Override
    public boolean create(HabitacionBE input) {
        return habitacionDAO.Create(input);
    }

    @Override
    public HabitacionBE read(String id) {
        return habitacionDAO.Read(id);
    }

    @Override
    public ArrayList<HabitacionBE> readAll() {
        return habitacionDAO.ReadAll();
    }

    @Override
    public boolean update(HabitacionBE input) {
        return habitacionDAO.Update(input);
    }

    @Override
    public boolean delete(String id) {
        return habitacionDAO.Delete(id);
    }
}
