package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.TelOperatorDAO;
import entity.TelOperatorEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TelOperatorDAOIMPL implements TelOperatorDAO {
    private Connection connection;

    @Override
    public boolean add(TelOperatorEntity entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(TelOperatorEntity entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(TelOperatorEntity entity) throws Exception {
        return false;
    }

    @Override
    public TelOperatorEntity search(TelOperatorEntity entity) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM TelOperators WHERE UName = ?", connection, entity.getUName());
        TelOperatorEntity telOperatorEntity = null;
        while (rst.next()){
            telOperatorEntity = new TelOperatorEntity(rst.getInt("ID"),rst.getString("UName"), rst.getString("PW"), rst.getString("Salt"));
        }
        return telOperatorEntity;
    }

    @Override
    public ArrayList<TelOperatorEntity> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM TelOperators", connection);
        ArrayList<TelOperatorEntity> allEntitiies = new ArrayList<>();
        while (rst.next()){
            allEntitiies.add(new TelOperatorEntity(rst.getInt("ID"),rst.getString("UName"), rst.getString("PW"), rst.getString("Salt")));
        }
        return allEntitiies;
    }

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection = connection;
    }

    @Override
    public TelOperatorEntity searchFromID(int id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM TelOperators WHERE ID = ?", connection, id);
        TelOperatorEntity telOperatorEntity = null;
        while (rst.next()){
            telOperatorEntity = new TelOperatorEntity(rst.getInt("ID"),rst.getString("UName"), rst.getString("PW"), rst.getString("Salt"));
        }
        return telOperatorEntity;
    }
}