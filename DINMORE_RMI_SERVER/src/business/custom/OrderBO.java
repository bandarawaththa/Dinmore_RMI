package business.custom;

import business.SuperBO;
import dtos.OrderDTO;

import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    public boolean addOrder(OrderDTO order)throws Exception;
    public boolean deleteOrder(OrderDTO order)throws Exception;
    public OrderDTO searchOrder(OrderDTO order)throws Exception;
    public boolean updateOrder(OrderDTO order)throws Exception;
    public ArrayList<OrderDTO> getAllOrders()throws Exception;
    public ArrayList<OrderDTO> searchAllOrders(String oID)throws Exception;
}
