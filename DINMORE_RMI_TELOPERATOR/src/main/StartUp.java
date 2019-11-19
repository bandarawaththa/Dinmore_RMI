package main;

import dtos.CustomerDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class StartUp extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        try {
            System.out.println("\n \n Thread Is Sleeping for Server Startup ... ! \n \n");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
                  System.out.println(e.getMessage());
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            URL url = new File("DINMORE_RMI_TELOPERATOR/assets/xmls/Login.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("DINMORE | Login");
            primaryStage.setMaximized(true);
            stage = primaryStage;
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
    }
}