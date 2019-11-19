package connector;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.OrderService;
import service.custom.TelOperatorService;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class ProxyHandler {
    private static ProxyHandler handler;
    private TelOperatorService telOperatorService;
    private CustomerService customerService;
    private OrderService orderService;

    private ProxyHandler () {
        try {
            ServiceFactory lookup = (ServiceFactory) Naming.lookup("rmi://localhost:5050/Dinmore");
            telOperatorService = (TelOperatorService) lookup.getService(ServiceFactory.ServiceTypes.TELOPERATOR);
            customerService = (CustomerService) lookup.getService(ServiceFactory.ServiceTypes.CUSTOMER);
            orderService = (OrderService) lookup.getService(ServiceFactory.ServiceTypes.ORDER);
        } catch (Exception e) {
            e.getMessage();
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public static ProxyHandler getInstance() {
        return (null == handler) ? (handler = new ProxyHandler()) : handler;
    }

    public TelOperatorService getTelOperatorService () {
        return telOperatorService;
    }

    public CustomerService getCusService () {
        return customerService;
    }

    public OrderService getOrderService () {
        return orderService;
    }
}