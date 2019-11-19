package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CheffDAO;
import entity.CheffEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CheffDAOIMPL implements CheffDAO {
    private Connection connection;

    @Override
    public boolean add(CheffEntity entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(CheffEntity entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(CheffEntity entity) throws Exception {
        return false;
    }

    @Override
    public CheffEntity search(CheffEntity entity) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Cheffs WHERE UName = ?", connection, entity.getUName());
        CheffEntity CheffEntity = null;
        while (rst.next()){
            CheffEntity = new CheffEntity(rst.getInt("ID"), rst.getString("UName"), rst.getString("PW"), rst.getString("Salt"));
        }
        return CheffEntity;
    }

    @Override
    public ArrayList<CheffEntity> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Cheffs", connection);
        ArrayList<CheffEntity> allEntitiies = new ArrayList<>();
        while (rst.next()){
            allEntitiies.add(new CheffEntity(rst.getInt("ID"), rst.getString("UName"), rst.getString("PW"), rst.getString("Salt")));
        }
        return allEntitiies;
    }

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection = connection;
    }
}