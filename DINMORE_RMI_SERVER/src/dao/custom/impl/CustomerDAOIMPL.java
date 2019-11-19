package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.CustomerEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAOIMPL implements CustomerDAO {

    private Connection connection;

    @Override
    public boolean add(CustomerEntity entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Customers(Name,Contact,Address) VALUES(?, ?, ?)", connection, entity.getCustomerName(), entity.getContactNumber(), entity.getAddress());
    }

    @Override
    public boolean update(CustomerEntity entity) throws Exception {
        return CrudUtil.execute("UPDATE Customers SET Name = ?, Contact = ?, Address = ? WHERE ID = ?", connection, entity.getCustomerName(), entity.getContactNumber(), entity.getAddress(), entity.getCustomerID());
    }

    @Override
    public boolean delete(CustomerEntity entity) throws Exception {
        return CrudUtil.execute("DELETE FROM Customers WHERE ID = ?", connection, entity.getCustomerID());
    }

    @Override
    public CustomerEntity search(CustomerEntity entity) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customers WHERE Contact = ?", connection, entity.getContactNumber());
        while (rst.next()){
            return new CustomerEntity(rst.getInt("ID"), rst.getString("Name"), rst.getInt("Contact"), rst.getString("Address"));
        }
        return null;
    }

    @Override
    public ArrayList<CustomerEntity> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customers", connection);
        ArrayList<CustomerEntity> allEntitiies = new ArrayList<>();
        while (rst.next()){
            allEntitiies.add(new CustomerEntity(rst.getInt("ID"), rst.getString("Name"), rst.getInt("Contact"), rst.getString("Address")));
        }
        return allEntitiies;
    }

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection = connection;
    }

    @Override
    public ArrayList<CustomerEntity> searchAll(String cus) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customers WHERE ID like ? || Name like ? || Contact like ? || Address like ?", connection, cus, cus, cus, cus);
        ArrayList<CustomerEntity> allEntitiies = new ArrayList<>();
        while (rst.next()){
            allEntitiies.add(new CustomerEntity(rst.getInt("ID"), rst.getString("Name"), rst.getInt("Contact"), rst.getString("Address")));
        }
        return allEntitiies;
    }

    @Override
    public CustomerEntity searchFromCusID(int id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customers WHERE ID = ?", connection, id);
        while (rst.next()){
            return new CustomerEntity(rst.getInt("ID"), rst.getString("Name"), rst.getInt("Contact"), rst.getString("Address"));
        }
        return null;
    }
}