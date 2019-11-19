package dao.custom;

import dao.CrudDAO;
import entity.CustomerEntity;

import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerEntity> {
    public ArrayList<CustomerEntity> searchAll(String cus) throws Exception;
    public CustomerEntity searchFromCusID (int id) throws Exception;
}
