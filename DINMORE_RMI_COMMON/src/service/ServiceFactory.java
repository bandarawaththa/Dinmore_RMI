package service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {
    public enum ServiceTypes {
        TELOPERATOR, CUSTOMER, CHEFF, ORDER, MANAGER;
    }

    public SuperService getService (ServiceTypes type) throws Exception;
}
