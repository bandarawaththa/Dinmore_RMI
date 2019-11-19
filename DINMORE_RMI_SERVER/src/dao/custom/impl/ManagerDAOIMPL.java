package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ManagerDAO;
import entity.ManagerEntity;
import entity.TelOperatorEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManagerDAOIMPL implements ManagerDAO {
    private Connection connection;

    @Override
    public ManagerEntity searchFromID(int id) throws Exception {
        return null;
    }

    @Override
    public boolean add(ManagerEntity entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(ManagerEntity entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(ManagerEntity entity) throws Exception {
        return false;
    }

    @Override
    public ManagerEntity search(ManagerEntity entity) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Managers WHERE UName = ?", connection, entity.getUName());
        ManagerEntity managerEntity = null;
        while (rst.next()){
            managerEntity = new ManagerEntity();
            managerEntity.setId(rst.getInt("ID"));
            managerEntity.setUName(rst.getString("UName"));
            managerEntity.setPW(rst.getString("PW"));
            managerEntity.setSalt(rst.getString("Salt"));
        }
        return managerEntity;
    }

    @Override
    public ArrayList<ManagerEntity> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Managers", connection);
        ArrayList<ManagerEntity> allEntitiies = new ArrayList<>();
        while (rst.next()){
            ManagerEntity entity = new ManagerEntity();
            entity.setId(rst.getInt("ID"));
            entity.setUName(rst.getString("UName"));
            entity.setPW(rst.getString("PW"));
            entity.setSalt(rst.getString("Salt"));
            allEntitiies.add(entity);
        }
        return allEntitiies;
    }

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection = connection;
    }
}