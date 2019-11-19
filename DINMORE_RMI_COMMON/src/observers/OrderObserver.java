package observers;

import dtos.OrderDTO;

import java.rmi.Remote;
import java.util.ArrayList;

public interface OrderObserver extends Remote {
    public void update(ArrayList<OrderDTO> orders)throws Exception;
}
