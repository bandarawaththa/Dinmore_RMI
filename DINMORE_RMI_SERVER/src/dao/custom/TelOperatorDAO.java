package dao.custom;

import dao.CrudDAO;
import entity.TelOperatorEntity;

public interface TelOperatorDAO extends CrudDAO<TelOperatorEntity> {
    public TelOperatorEntity searchFromID (int id) throws Exception;
}
