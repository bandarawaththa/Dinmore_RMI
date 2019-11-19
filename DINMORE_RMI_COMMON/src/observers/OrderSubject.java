package observers;

import dtos.OrderDTO;

import java.rmi.Remote;
import java.util.ArrayList;

public interface OrderSubject extends Remote {
    public void register(OrderObserver ob)throws Exception;
    public void unregister(OrderObserver ob)throws Exception;
    public void notyfyAllObservers()throws Exception;
}
