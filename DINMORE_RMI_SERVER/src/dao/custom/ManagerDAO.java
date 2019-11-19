package dao.custom;

import dao.CrudDAO;
import entity.ManagerEntity;

public interface ManagerDAO extends CrudDAO<ManagerEntity> {
    public ManagerEntity searchFromID (int id) throws Exception;
}
