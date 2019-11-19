package dao;

import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    public boolean add (T entity) throws Exception;
    public boolean update(T entity) throws Exception;
    public boolean delete(T entity) throws Exception;
    public T search(T entity)throws Exception;
    public ArrayList<T> getAll()throws Exception;
}
