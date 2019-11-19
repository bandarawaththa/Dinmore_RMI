package connector;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ManagerService;
import service.custom.OrderService;
import service.custom.TelOperatorService;

import java.rmi.Naming;

public class ProxyHandler {
    private static ProxyHandler handler;
    private CustomerService customerService;
    private ManagerService managerService;
    private OrderService orderService;

    private ProxyHandler () {
        try {
            ServiceFactory lookup = (ServiceFactory) Naming.lookup("rmi://localhost:5050/Dinmore");
            customerService = (CustomerService) lookup.getService(ServiceFactory.ServiceTypes.CUSTOMER);
            managerService = (ManagerService) lookup.getService(ServiceFactory.ServiceTypes.MANAGER);
            orderService = (OrderService) lookup.getService(ServiceFactory.ServiceTypes.ORDER);
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public static ProxyHandler getInstance() {
        return (null == handler) ? (handler = new ProxyHandler()) : handler;
    }

    public CustomerService getCusService () {
        return customerService;
    }

    public ManagerService getManagerService () {
        return managerService;
    }

    public OrderService getOrderService () {
        return orderService;
    }
}