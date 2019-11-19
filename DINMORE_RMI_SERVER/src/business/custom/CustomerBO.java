package business.custom;

import business.SuperBO;
import dtos.CustomerDTO;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO cus)throws Exception;
    public boolean deleteCustomer(CustomerDTO cus)throws Exception;
    public CustomerDTO searchCustomer(CustomerDTO cus)throws Exception;
    public boolean updateCustomer(CustomerDTO cus)throws Exception;
    public ArrayList<CustomerDTO> getAllCustomers()throws Exception;
    public ArrayList<CustomerDTO> searchAllCustomers(String cus)throws Exception;
    public CustomerDTO searchFromCusID (int id) throws Exception;
}
