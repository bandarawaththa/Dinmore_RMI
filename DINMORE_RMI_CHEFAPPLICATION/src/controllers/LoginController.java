package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import connector.ProxyHandler;
import dtos.CheffDTO;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.StartUp;
import service.custom.CheffService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    static CheffService cheffService = ProxyHandler.getInstance().getCheffService();
    public static Stage cheffStage;
    CheffDTO cheff;
    public static String userName;

    @FXML
    private VBox MasterVBox;

    @FXML
    private Label lblWelcome;

    @FXML
    private JFXTextField txtUName;

    @FXML
    private JFXComboBox<String> cmbUID;

    @FXML
    private JFXPasswordField txtPW;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink lblForgot;

    @FXML
    void ExitMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnExit);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKRED);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnExit.setEffect(glow);
    }

    @FXML
    void ExitMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnExit);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnExit.setEffect(null);
    }

    @FXML
    void LoginMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnLogin);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKBLUE);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnLogin.setEffect(glow);
    }

    @FXML
    void LoginMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnLogin);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnLogin.setEffect(null);
    }

    @FXML
    void btnExitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnLoginAction(ActionEvent event) {
        login();
    }

    @FXML
    void cmbUIDAction(ActionEvent event) {
        searchUser(new CheffDTO(cmbUID.getSelectionModel().getSelectedItem()));
        txtUName.setText(cmbUID.getSelectionModel().getSelectedItem());
    }

    void searchUser (CheffDTO dto){
        try {
            cheff = cheffService.searchCheff(dto);
            if (cheff != null) {
                txtPW.setDisable(false);
                btnLogin.setDisable(false);
                txtPW.requestFocus();
                lblWelcome.setTextFill(Color.BLUE);
                lblWelcome.setText("Hello " + cheff.getUName() + ".. !");
                cmbUID.getSelectionModel().select(dto.getUName());
            } else {
                new Alert(Alert.AlertType.INFORMATION,"No user account found ... ! \n please try again", ButtonType.CLOSE).show();
                txtUName.setText("");
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    void login () {
        userName = txtUName.getText();
        if (null == cheff){
            new Alert(Alert.AlertType.INFORMATION,"Invalid UserName entry ... ! \n please try again", ButtonType.CLOSE).show();
        } else {
            boolean checked = false;
            try {
                checked = cheffService.checkPW(txtPW.getText());
            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
            }
            if (checked){
                URL url = null;
                try {
                    url = new File("DINMORE_RMI_CHEFAPPLICATION/assets/xmls/CompleteOrder.fxml").toURI().toURL();
                    Parent root = FXMLLoader.load(url);
                    Scene scene = new Scene(root);
                    scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
                        if ( event.getCode() == KeyCode.F6) {
                            URL url2 = null;
                            try {
                                url2 = new File("DINMORE_RMI_CHEFAPPLICATION/assets/xmls/Login.fxml").toURI().toURL();
                                Parent root2 = FXMLLoader.load(url2);
                                Scene scene2 = new Scene(root2);
                                StartUp.stage.setScene(scene2);
                                StartUp.stage.centerOnScreen();
                                StartUp.stage.initStyle(StageStyle.UNDECORATED);
                                StartUp.stage.setMaximized(true);
                                StartUp.stage.setTitle("DINMORE | Login");
                                StartUp.stage.show();
                                cheffStage.close();
                            } catch (IOException e) {
                                new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
                            }
                        }
                    });
                    cheffStage = new Stage();
                    cheffStage.setScene(scene);
                    cheffStage.centerOnScreen();
                    cheffStage.setMaximized(true);
                    cheffStage.setTitle("DINMORE Kitchen Officer | Complete Order");
                    cheffStage.show();
                    StartUp.stage.close();
                } catch (IOException e) {
                    new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Invalid password entry ... ! \n please try again", ButtonType.CLOSE).show();
                lblForgot.setTextFill(Paint.valueOf("red"));
                txtPW.setText("");
            }
        }
    }

    @FXML
    void lblForgotAction(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION,"no functions decided yet ... !", ButtonType.CLOSE).show();
    }

    @FXML
    void txtPWAction(ActionEvent event) {
        login();
    }

    @FXML
    void txtUNameAction(ActionEvent event) {
        CheffDTO cheffDTO= new CheffDTO(txtUName.getText());
        searchUser(cheffDTO);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<CheffDTO> allCheffs = null;
        try {
            allCheffs = cheffService.getAllCheffs();
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
        for (CheffDTO cheff :
                allCheffs) {
            cmbUID.getItems().add(cheff.getUName());
        }
    }
}