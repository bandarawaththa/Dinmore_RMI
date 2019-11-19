package main;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import security.PasswordUtils;
import service.ServiceFactoryIMPL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerStart {
    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname", "localhost");
        try {
            Registry registry = LocateRegistry.createRegistry(5050);
            registry.rebind("Dinmore", ServiceFactoryIMPL.getInstance());
//            new Alert(Alert.AlertType.CONFIRMATION,"Starting Dinmore Server ... !", ButtonType.OK).show();
            System.out.println("\n\nS\tt\ta\tr\tt\ti\tn\tg\n\t\t\t\t\t\t\t\tD\ti\tn\tm\to\tr\te\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tS\te\tr\tv\te\tr\t ... !");
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
    }
}