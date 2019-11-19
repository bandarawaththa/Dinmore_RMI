package observers;

import java.rmi.Remote;

public interface CustomerSubject extends Remote {
    public void register(CustomerObserver ob)throws Exception;
    public void unregister(CustomerObserver ob)throws Exception;
    public void notyfyAllObservers()throws Exception;
}
