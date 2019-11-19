package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import connector.ProxyHandler;
import dtos.OrderDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.StartUp;
import observers.ManagerObserver;
import service.custom.OrderService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardController implements Initializable, ManagerObserver {
    private static OrderService orderService = ProxyHandler.getInstance().getOrderService();

    @FXML
    private JFXTextField txtUName;

    @FXML
    private JFXTextField txtPW;

    @FXML
    private JFXRadioButton btnROperator;

    @FXML
    private ToggleGroup users;

    @FXML
    private JFXRadioButton btnRCheff;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<?> tblUser;

    @FXML
    private Label lblWelcome;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblOID;

    @FXML
    private JFXComboBox<?> cmbORange;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<OrderDTO> tblOrder;

    @FXML
    private Button btnChat;

    @FXML
    private Button btnRemoveOrder;

    @FXML
    private Button btnUpdateOrder;

    @FXML
    private ImageView imgOperator;

    @FXML
    private ImageView imgChef;

    @FXML
    private ImageView imgProfit;

    @FXML
    private Label lblWelcome2;

    @FXML
    private Label lblActionSelect;

    @FXML
    void btnAddAction(ActionEvent event) {

    }

    @FXML
    void btnChatAction(ActionEvent event) {

    }

    @FXML
    void btnChatMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnChatMouseExited(MouseEvent event) {

    }

    @FXML
    void btnNewMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnNewMouseExited(MouseEvent event) {

    }

    @FXML
    void btnRemoveAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnRemoveMouseExited(MouseEvent event) {

    }

    @FXML
    void btnRemoveOrderAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveOrderMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnRemoveOrderMouseExit(MouseEvent event) {

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnUpdateMouseExited(MouseEvent event) {

    }

    @FXML
    void btnUpdateOrderAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOrderMouseEntered(MouseEvent event) {

    }

    @FXML
    void btnUpdateOrderMouseExit(MouseEvent event) {

    }

    @FXML
    void cmbORangeAction(ActionEvent event) {

    }

    @FXML
    void imgChefMouseClicked(MouseEvent event) {

    }

    @FXML
    void imgChefMouseEntered(MouseEvent event) {

    }

    @FXML
    void imgChefMouseExit(MouseEvent event) {

    }

    @FXML
    void imgOperatorMouseClicked(MouseEvent event) {

    }

    @FXML
    void imgOperatorMouseEntered(MouseEvent event) {

    }

    @FXML
    void imgOperatorMouseExit(MouseEvent event) {

    }

    @FXML
    void imgProfitMouseClicked(MouseEvent event) {

    }

    @FXML
    void imgProfitMouseEntered(MouseEvent event) {

    }

    @FXML
    void imgProfitMouseExit(MouseEvent event) {

    }

    @FXML
    void lblWelcomeMouseCliecked(MouseEvent event) {
        URL url = null;
        try {
            url = new File("DINMORE_RMI_MANAGERAPPLICATION/assets/xmls/Login.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
        try {
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            StartUp.stage.setScene(scene);
            StartUp.stage.centerOnScreen();
            StartUp.stage.setMaximized(true);
            StartUp.stage.initStyle(StageStyle.UNDECORATED);
            StartUp.stage.setTitle("DINMORE | Login");
            StartUp.stage.show();
            LoginController.adminStage.close();
        } catch (IOException e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    @FXML
    void lblWelcomeMouseEntered(MouseEvent event) {
        lblWelcome.setTextFill(Paint.valueOf("red"));
    }

    @FXML
    void lblWelcomeMouseExited(MouseEvent event) {
        lblWelcome.setTextFill(Paint.valueOf("blue"));
    }

    @FXML
    void tblOMouseClicked(MouseEvent event) {

    }

    @FXML
    void tbtUserMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtPWAction(ActionEvent event) {

    }

    @FXML
    void txtSearchAction(ActionEvent event) {

    }

    @FXML
    void txtSearchKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtUnameAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTimeDate();
        try {
            UnicastRemoteObject.exportObject(this, 0);
            orderService.register(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("addOrderTime"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telOpID"));
        tblOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("cheffID"));
        tblOrder.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("cusID"));
        tblOrder.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrder.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("amount"));
        loadtblOrders();
    }

    private void loadtblOrders() {
        ArrayList<OrderDTO> allOrders = null;
        try {
            allOrders = orderService.getAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
        tblOrder.setItems(FXCollections.observableArrayList(allOrders));
    }

    private void setTimeDate () {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblDate.setText(new SimpleDateFormat("MM-dd-YYYY").format(new Date()));
                lblTime.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
            }
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void update() throws Exception {
        loadtblOrders();
    }
}