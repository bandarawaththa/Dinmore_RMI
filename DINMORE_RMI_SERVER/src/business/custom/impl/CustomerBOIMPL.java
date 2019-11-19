package business.custom.impl;

import business.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dbHandler.DBConnection;
import dtos.CustomerDTO;
import entity.CustomerEntity;
import java.sql.Connection;
import java.util.ArrayList;

public class CustomerBOIMPL implements CustomerBO {
    CustomerDAO dao = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO cus) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            dao.setConnection(connection);
            return dao.add(new CustomerEntity(cus.getCustomerName(), cus.getContact(), cus.getAddress()));
        }
    }

    @Override
    public boolean deleteCustomer(CustomerDTO cus) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            dao.setConnection(connection);
            return dao.delete(new CustomerEntity(Integer.parseInt(cus.getCustomerID())));
        }
    }

    @Override
    public CustomerDTO searchCustomer(CustomerDTO cus) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            dao.setConnection(connection);
            CustomerEntity entity = dao.search(new CustomerEntity(0,"",cus.getContact(),""));
            return (null != entity) ? new CustomerDTO("CUS" + entity.getCustomerID(), entity.getCustomerName(), entity.getContactNumber(), entity.getAddress()) : null;
        }
    }

    @Override
    public boolean updateCustomer(CustomerDTO cus) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            dao.setConnection(connection);
            return dao.update(new CustomerEntity(Integer.parseInt(cus.getCustomerID()), cus.getCustomerName(), cus.getContact(), cus.getAddress()));
        }
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()){
            dao.setConnection(connection);
            ArrayList<CustomerEntity> allEntities = dao.getAll();
            ArrayList<CustomerDTO> allDTOS = new ArrayList<>();
            for (CustomerEntity entity :
                    allEntities) {
                allDTOS.add(new CustomerDTO("CUS" + entity.getCustomerID(), entity.getCustomerName(), entity.getContactNumber(), entity.getAddress()));
            }
            return allDTOS;
        }
    }

    @Override
    public ArrayList<CustomerDTO> searchAllCustomers(String cus) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()){
            dao.setConnection(connection);
            ArrayList<CustomerEntity> allEntities = dao.searchAll(cus);
            ArrayList<CustomerDTO> allDTOS = new ArrayList<>();
            for (CustomerEntity entity :
                    allEntities) {
                allDTOS.add(new CustomerDTO("CUS" + entity.getCustomerID(), entity.getCustomerName(), entity.getContactNumber(), entity.getAddress()));
            }
            return allDTOS;
        }
    }

    @Override
    public CustomerDTO searchFromCusID(int id) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            dao.setConnection(connection);
            CustomerEntity entity = dao.searchFromCusID(id);
            return (null != entity) ? new CustomerDTO(Integer.toString(entity.getCustomerID()), entity.getCustomerName(), entity.getContactNumber(), entity.getAddress()) : null;
        }
    }
}