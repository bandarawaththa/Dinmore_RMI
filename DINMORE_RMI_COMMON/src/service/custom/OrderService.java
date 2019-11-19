package service.custom;

import dtos.OrderDTO;
import observers.ManagerSubject;
import observers.OrderSubject;
import service.SuperService;
import java.util.ArrayList;

public interface OrderService extends SuperService, OrderSubject, ManagerSubject {
    public void addToQueue (OrderDTO order) throws Exception;
    public void removeFromQueue (OrderDTO order) throws Exception;
    public ArrayList<OrderDTO> getAllFromQueue () throws Exception;
    public boolean addOrder(OrderDTO order)throws Exception;
    public boolean deleteOrder(OrderDTO order)throws Exception;
    public OrderDTO searchOrder(OrderDTO order)throws Exception;
    public boolean updateOrder(OrderDTO order)throws Exception;
    public ArrayList<OrderDTO> getAllOrders()throws Exception;
    public ArrayList<OrderDTO> searchAllOrders(String oID)throws Exception;
}
