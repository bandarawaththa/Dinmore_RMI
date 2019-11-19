package observers;

import java.rmi.Remote;

public interface CustomerObserver extends Remote {
    public void update()throws Exception;
}
