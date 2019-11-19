package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory factory;

    private DAOFactory() {}

    public enum DAOTypes {
        TELOPERATOR, CUSTOMER, CHEFF, ORDER, MANAGER;
    }

    public static DAOFactory getInstance() {
        return (null == factory) ? (factory = new DAOFactory()) : factory;
    }

    public SuperDAO getDAO (DAOTypes type) {
        switch (type){
            case TELOPERATOR:
                return new TelOperatorDAOIMPL();
            case CUSTOMER:
                return new CustomerDAOIMPL();
            case CHEFF:
                return new CheffDAOIMPL();
            case ORDER:
                return new OrderDAOIMPL();
            case MANAGER:
                return new ManagerDAOIMPL();
            default:
                return null;
        }
    }
}
