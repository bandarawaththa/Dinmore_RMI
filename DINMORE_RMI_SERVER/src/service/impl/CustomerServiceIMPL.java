package service.impl;

import business.BOFactory;
import business.custom.CustomerBO;
import dtos.CustomerDTO;
import observers.CustomerObserver;
import reservation.impl.ReservationIMPL;
import service.custom.CustomerService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Logger;

public class CustomerServiceIMPL extends UnicastRemoteObject implements CustomerService {
    private CustomerBO bo = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    private static ReservationIMPL<CustomerService> reservation = new ReservationIMPL<>();
    private static ArrayList<CustomerObserver> customerObservers = new ArrayList<>();

    public CustomerServiceIMPL() throws RemoteException {}

    @Override
    public boolean addCustomer(CustomerDTO cus) throws Exception {
        notyfyAllObservers();
        return bo.addCustomer(cus);
    }

    @Override
    public boolean deleteCustomer(CustomerDTO cus) throws Exception {
        notyfyAllObservers();
        return bo.deleteCustomer(cus);
    }

    @Override
    public CustomerDTO searchCustomer(CustomerDTO cus) throws Exception {
        return bo.searchCustomer(cus);
    }

    @Override
    public boolean updateCustomer(CustomerDTO cus) throws Exception {
        notyfyAllObservers();
        return bo.updateCustomer(cus);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        return bo.getAllCustomers();
    }

    @Override
    public ArrayList<CustomerDTO> searchAllCustomers(String cus) throws Exception {
        return bo.searchAllCustomers("%" + cus + "%");
    }

    @Override
    public CustomerDTO searchCusFromID(int id) throws Exception {
        return bo.searchFromCusID(id);
    }

    @Override
    public boolean reserve(Object key) throws Exception {
        return reservation.reserved(key, this);
    }

    @Override
    public boolean release(Object key) throws Exception {
        return reservation.released(key, this);
    }

    @Override
    public void register(CustomerObserver ob) throws Exception {
        customerObservers.add(ob);
    }

    @Override
    public void unregister(CustomerObserver ob) throws Exception {
        customerObservers.remove(ob);
    }

    @Override
    public void notyfyAllObservers() throws Exception {
        for (CustomerObserver o : customerObservers) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                o.update();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
            ).start();
        }
    }
}