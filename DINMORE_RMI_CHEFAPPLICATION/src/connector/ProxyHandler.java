package connector;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import service.ServiceFactory;
import service.custom.CheffService;
import service.custom.CustomerService;
import service.custom.OrderService;
import service.custom.TelOperatorService;

import java.rmi.Naming;

public class ProxyHandler {
    private static ProxyHandler handler;
    private CheffService cheffService;
    private OrderService orderService;
    private CustomerService customerService;
    private TelOperatorService telOperatorService;

    private ProxyHandler () {
        try {
            ServiceFactory lookup = (ServiceFactory) Naming.lookup("rmi://localhost:5050/Dinmore");
            cheffService = (CheffService) lookup.getService(ServiceFactory.ServiceTypes.CHEFF);
            orderService = (OrderService) lookup.getService(ServiceFactory.ServiceTypes.ORDER);
            customerService = (CustomerService) lookup.getService(ServiceFactory.ServiceTypes.CUSTOMER);
            telOperatorService = (TelOperatorService) lookup.getService(ServiceFactory.ServiceTypes.TELOPERATOR);
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public static ProxyHandler getInstance() {
        return (null == handler) ? (handler = new ProxyHandler()) : handler;
    }

    public CheffService getCheffService () {
        return cheffService;
    }

    public OrderService getOrderService () {
        return orderService;
    }

    public CustomerService getCustomerService () {
        return customerService;
    }

    public TelOperatorService getTelOperatorService () {
        return telOperatorService;
    }
}