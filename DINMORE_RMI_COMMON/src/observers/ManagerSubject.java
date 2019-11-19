package observers;

import java.rmi.Remote;

public interface ManagerSubject extends Remote {
    public void register(ManagerObserver ob)throws Exception;
    public void unregister(ManagerObserver ob)throws Exception;
    public void notyfyAllManagers()throws Exception;
}
