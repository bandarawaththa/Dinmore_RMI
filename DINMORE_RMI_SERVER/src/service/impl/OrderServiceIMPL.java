package service.impl;

import business.BOFactory;
import business.custom.OrderBO;
import dtos.OrderDTO;
import observers.ManagerObserver;
import observers.OrderObserver;
import service.custom.OrderService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class OrderServiceIMPL extends UnicastRemoteObject implements OrderService {
    private static ArrayList<OrderObserver> orderObservers = new ArrayList<>();
    private static ArrayList<ManagerObserver> managerObservers = new ArrayList<>();
    private static ArrayList<OrderDTO> allOrders = new ArrayList<>();
    private OrderBO bo = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);

    public OrderServiceIMPL() throws RemoteException {}

    @Override
    public void addToQueue(OrderDTO order) throws Exception {
        allOrders.add(order);
        notyfyAllObservers();
    }

    @Override
    public void removeFromQueue(OrderDTO order) throws Exception {
        allOrders.remove(order);
        notyfyAllObservers();
    }

    @Override
    public ArrayList<OrderDTO> getAllFromQueue() throws Exception {
        return allOrders;
    }

    @Override
    public boolean addOrder(OrderDTO order) throws Exception {
        try {
            return bo.addOrder(order);
        } finally {
            notyfyAllManagers();
        }
    }

    @Override
    public boolean deleteOrder(OrderDTO order) throws Exception {
        return bo.deleteOrder(order);
    }

    @Override
    public OrderDTO searchOrder(OrderDTO order) throws Exception {
        return bo.searchOrder(order);
    }

    @Override
    public boolean updateOrder(OrderDTO order) throws Exception {
        return false;
    }

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws Exception {
        return bo.getAllOrders();
    }

    @Override
    public ArrayList<OrderDTO> searchAllOrders(String oID) throws Exception {
        return null;
    }

    @Override
    public void register(OrderObserver ob) throws Exception {
        orderObservers.add(ob);
    }

    @Override
    public void unregister(OrderObserver ob) throws Exception {
        orderObservers.remove(ob);
    }

    @Override
    public void register(ManagerObserver ob) throws Exception {
        managerObservers.add(ob);
    }

    @Override
    public void unregister(ManagerObserver ob) throws Exception {
        managerObservers.remove(ob);
    }

    @Override
    public void notyfyAllManagers() throws Exception {
        for (ManagerObserver o : managerObservers) {
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

    @Override
    public void notyfyAllObservers() throws Exception {
        for (OrderObserver o : orderObservers) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                o.update(allOrders);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
            ).start();
        }
    }
}