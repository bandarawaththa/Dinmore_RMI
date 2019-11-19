package service;

import service.impl.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryIMPL extends UnicastRemoteObject implements ServiceFactory {
    private static ServiceFactoryIMPL serviceFactory;

    @Override
    public SuperService getService(ServiceTypes type) throws Exception {
        switch (type) {
            case TELOPERATOR:
                return new TelOperatorServiceIMPL();
            case CUSTOMER:
                return new CustomerServiceIMPL();
            case CHEFF:
                return new CheffServiceIMPL();
            case ORDER:
                return new OrderServiceIMPL();
            case MANAGER:
                return new ManagerServiceIMPL();
            default:
                return null;
        }
    }

    private ServiceFactoryIMPL() throws RemoteException {}

    public static ServiceFactoryIMPL getInstance() throws RemoteException {
        return (null == serviceFactory) ? (serviceFactory = new ServiceFactoryIMPL()) : serviceFactory;
    }
}
