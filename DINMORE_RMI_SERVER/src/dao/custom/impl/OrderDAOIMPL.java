package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import entity.OrderEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDAOIMPL implements OrderDAO {
    private Connection connection;

    @Override
    public boolean add(OrderEntity entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Orders(CusID, OperatorID, CheffID, QTY, AddOrderTime, StartTime, EndTime, CheffTime, Date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", connection, entity.getCusID(), entity.getTelOpID(), entity.getCheffID(), entity.getQty(), entity.getAddOrderTime(), entity.getStartTime(), entity.getEndTime(), entity.getCount(), entity.getDate());
    }

    @Override
    public boolean update(OrderEntity entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(OrderEntity entity) throws Exception {
        return false;
    }

    @Override
    public OrderEntity search(OrderEntity entity) throws Exception {
        return null;
    }

    @Override
    public ArrayList<OrderEntity> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Orders ORDER BY ID DESC", connection);
        ArrayList<OrderEntity> allEntitiies = new ArrayList<>();
        while (rst.next()){
            allEntitiies.add(new OrderEntity(rst.getInt("ID"), rst.getString("AddOrderTime"), rst.getInt("CusID"), rst.getInt("OperatorID"), rst.getInt("QTY"), rst.getInt("CheffID"), rst.getString("StartTime"), rst.getString("EndTime"), rst.getInt("CheffTime"), rst.getString("Date")));
        }
        return allEntitiies;
    }

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection = connection;
    }
}