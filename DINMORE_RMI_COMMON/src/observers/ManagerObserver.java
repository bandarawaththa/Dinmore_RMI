package observers;

import java.rmi.Remote;

public interface ManagerObserver extends Remote {
    public void update()throws Exception;
}
