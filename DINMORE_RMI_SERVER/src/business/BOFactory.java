package business;

import business.custom.impl.*;

public class BOFactory {
    private static BOFactory factory;

    private BOFactory() {}

    public enum BOTypes {
        TELOPERATOR, CUSTOMER, CHEFF, ORDER, MANAGER;
    }

    public static BOFactory getInstance () {
        return (null == factory) ? (factory = new BOFactory()) : factory;
    }

    public SuperBO getBO (BOTypes type) {
        switch (type){
            case TELOPERATOR:
                return new TelOperatorBOIMPL();
            case CUSTOMER:
                return new CustomerBOIMPL();
            case CHEFF:
                return new CheffBOIMPL();
            case ORDER:
                return new OrderBOIMPL();
            case MANAGER:
                return new ManagerBOIMPL();
            default:
                return null;
        }
    }
}
