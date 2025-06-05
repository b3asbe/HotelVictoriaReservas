package BusinessLogic;

import java.util.ArrayList;

public interface IBaseBL<T> {
    public boolean create(T input);
    public T read(String id);
    public ArrayList<T> readAll();
    public boolean update(T input);
    public boolean delete(String id);
}
