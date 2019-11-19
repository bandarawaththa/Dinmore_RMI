package controllers;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import connector.ProxyHandler;
import dtos.CustomerDTO;
import dtos.OrderDTO;
import dtos.TelOperatorDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.StartUp;
import observers.CustomerObserver;
import service.custom.CustomerService;
import service.custom.OrderService;
import tm.CustomerTM;

import static controllers.LoginController.telOperatorService;

public class AddOrderController implements Initializable, CustomerObserver {

    static CustomerService customerService = ProxyHandler.getInstance().getCusService();
    static OrderService orderService = ProxyHandler.getInstance().getOrderService();
    public static Stage ChatStage;
    int qty;
    double price;
    double paid;
    double balance;
    boolean doneCustomer;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblCusID;

    @FXML
    private TextField txtSearchCus;

    @FXML
    private JFXTextField txtCusName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private Button btnReserve;

    @FXML
    private Button btnRelease;

    @FXML
    private JFXTextField txtCusAddress;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnAdd;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private Label lblPrice;

    @FXML
    private JFXTextField txtPaid;

    @FXML
    private Label lblBal;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSend;

    @FXML
    private TableView<CustomerTM> tblCus;

    @FXML
    private Label lblWelcome;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblOCount;

    @FXML
    private Button btnChat;

    @FXML
    void btnChatAction(ActionEvent event) {

    }

    @FXML
    void btnChatMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnChat);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKBLUE);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnChat.setEffect(glow);
    }

    @FXML
    void btnChatMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnChat);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnChat.setEffect(null);
    }

    @FXML
    void btnAddAction(ActionEvent event) {
        boolean added = false;
        try {
            added = customerService.addCustomer(new CustomerDTO(txtCusName.getText(), Integer.parseInt(txtContact.getText()), txtCusAddress.getText()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (added){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Added Successfully ... !", ButtonType.CLOSE).show();
            CustomerDTO cus = null;
            try {
                cus = customerService.searchCustomer(new CustomerDTO(Integer.parseInt(txtContact.getText())));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            lblCusID.setText("Customer ID : " + cus.getCustomerID());
            doneCustomer = true;
            txtCusName.setText(cus.getCustomerName());
            txtContact.setText(Integer.toString(cus.getContact()));
            txtCusAddress.setText(cus.getAddress());
            txtQty.requestFocus();
            btnAdd.setDisable(true);
            btnReserve.setDisable(false);
            btnRelease.setDisable(true);
            btnRemove.setDisable(true);
            btnUpdate.setDisable(true);
        } else {
            new Alert(Alert.AlertType.WARNING,"Customer Added Failier ... !", ButtonType.CLOSE).show();
        }
        loadtblCus();
    }

    @FXML
    void paneKeyPressed(KeyEvent event) {
        System.out.println("pressed .....................");
    }

    public void resetUI () {
        txtSearchCus.setText("");
        lblCusID.setText("Please select a Customer");
        txtCusName.setText("");
        txtContact.setText("");
        txtCusAddress.setText("");
        loadtblCus();
        btnReserve.setDisable(true);
        btnRelease.setDisable(true);
        btnRemove.setDisable(true);
        btnUpdate.setDisable(true);
        btnAdd.setDisable(false);
        btnReset.setDisable(false);
        btnSend.setDisable(true);
        txtCusName.requestFocus();
        txtQty.setText("");
        lblPrice.setText("");
        txtPaid.setText("");
        lblBal.setText("");
        txtPaid.setDisable(true);
        doneCustomer = false;
    }

    @FXML
    void btnNewMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnAdd);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKGREEN);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnAdd.setEffect(glow);
    }

    @FXML
    void btnNewMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnAdd);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnAdd.setEffect(null);
    }

    @FXML
    void btnReleaseAction(ActionEvent event) {
        String id = lblCusID.getText().substring(14);
        boolean released = false;
        try {
            released = customerService.release(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
        if (released){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer release success ... !", ButtonType.CLOSE).show();
            btnReserve.setDisable(false);
            btnRelease.setDisable(true);
            btnRemove.setDisable(true);
            btnUpdate.setDisable(true);
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Selected customer is reserved by another user ... !", ButtonType.CLOSE).show();
        }
    }

    @FXML
    void btnReleaseMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnRelease);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKMAGENTA);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnRelease.setEffect(glow);
    }

    @FXML
    void btnReleaseMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnRelease);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnRelease.setEffect(null);
    }

    @FXML
    void btnRemoveAction(ActionEvent event) {
        boolean removed = false;
        try {
            boolean reserved = customerService.reserve(lblCusID.getText().substring(14));
            if (reserved) {
                removed = customerService.deleteCustomer(new CustomerDTO(lblCusID.getText().substring(17)));
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Selected customer is already reserved ... !", ButtonType.CLOSE).show();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (removed){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer removal success ... !", ButtonType.CLOSE).show();
            try {
                customerService.release(lblCusID.getText().substring(14));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            resetUI();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Customer removal failier ... !", ButtonType.CLOSE).show();
        }
    }

    @FXML
    void btnRemoveMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnRemove);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKRED);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnRemove.setEffect(glow);
    }

    @FXML
    void btnRemoveMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnRemove);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnRemove.setEffect(null);
    }

    @FXML
    void btnReserveAction(ActionEvent event) {
        String id = lblCusID.getText().substring(14);
        boolean reserved = false;
        try {
            reserved = customerService.reserve(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
        if (reserved){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer reservation success ... !", ButtonType.CLOSE).show();
            btnReserve.setDisable(true);
            btnRelease.setDisable(false);
            btnRemove.setDisable(false);
            btnUpdate.setDisable(false);
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Selected customer is already reserved ... !", ButtonType.CLOSE).show();
        }
    }

    @FXML
    void btnReserveMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnReserve);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKMAGENTA);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnReserve.setEffect(glow);
    }

    @FXML
    void btnReserveMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnReserve);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnReserve.setEffect(null);
    }

    @FXML
    void btnResetAction(ActionEvent event) {
        resetUI();
    }

    @FXML
    void btnResetMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnReset);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKRED);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnReset.setEffect(glow);
    }

    @FXML
    void btnResetMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnReset);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnReset.setEffect(null);
    }

    @FXML
    void btnSendAction(ActionEvent event) {
        if (doneCustomer){
            OrderDTO order = new OrderDTO();
            order.setAddOrderTime(lblTime.getText());
            order.setCusID(lblCusID.getText().substring(17));
            order.setQty(Integer.parseInt(txtQty.getText()));
            order.setDate(lblDate.getText());
            try {
                order.setTelOpID(telOperatorService.searchTelOperator(new TelOperatorDTO(LoginController.userName)).getId().substring(2));
                orderService.addToQueue(order);

//                InputStre
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            resetUI();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Customer selection Invalid ... !\n Please try again", ButtonType.CLOSE).show();
            txtSearchCus.requestFocus();
        }
    }

    @FXML
    void btnSendMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnSend);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKGREEN);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnSend.setEffect(glow);
    }

    @FXML
    void btnSendMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnSend);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnSend.setEffect(null);
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        boolean updated = false;
        try {
            boolean reserved = customerService.reserve(lblCusID.getText().substring(14));
            if (reserved) {
                updated = customerService.updateCustomer(new CustomerDTO(lblCusID.getText().substring(17), txtCusName.getText(), Integer.parseInt(txtContact.getText()), txtCusAddress.getText()));
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Selected customer is already reserved ... !", ButtonType.CLOSE).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
        if (updated){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer update SUCCESS ... !", ButtonType.CLOSE).show();
            boolean released = false;
            try {
                released = customerService.release(lblCusID.getText().substring(14));
            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
            }
            if (released){
                new Alert(Alert.AlertType.INFORMATION,"Customer release SUCCESS ... !", ButtonType.CLOSE).show();
            }
            resetUI();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Customer update FAILIER ... !", ButtonType.CLOSE).show();
        }
    }

    @FXML
    void btnUpdateMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnUpdate);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKBLUE);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnUpdate.setEffect(glow);
    }

    @FXML
    void btnUpdateMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnUpdate);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnUpdate.setEffect(null);
    }

    @FXML
    void lblWelcomeMouseCliecked(MouseEvent event) {
        URL url = null;
        try {
            url = new File("DINMORE_RMI_TELOPERATOR/assets/xmls/Login.fxml").toURI().toURL();
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
            LoginController.orderStage.close();
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
    void mouseEntered(MouseEvent event) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKBLUE);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        txtSearchCus.setEffect(glow);
    }

    @FXML
    void mouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), txtSearchCus);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        txtSearchCus.setEffect(null);
    }

    @FXML
    void tblCusMouseClicked(MouseEvent event) {
        try {
            CustomerTM cus = tblCus.getSelectionModel().getSelectedItem();
            lblCusID.setText("Customer ID : " + cus.getCustomerID());
            doneCustomer = true;
            txtCusName.setText(cus.getCustomerName());
            txtContact.setText(Integer.toString(cus.getContactNumber()));
            txtCusAddress.setText(cus.getAddress());
            txtQty.requestFocus();
            btnAdd.setDisable(true);
            btnReserve.setDisable(false);
            btnRelease.setDisable(true);
            btnRemove.setDisable(true);
            btnUpdate.setDisable(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void txtContactAction(ActionEvent event) {
        CustomerDTO cus = null;
        try {
            cus = customerService.searchCustomer(new CustomerDTO(Integer.parseInt(txtContact.getText())));
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
        if (cus != null){
            lblCusID.setText("Customer ID : " + cus.getCustomerID());
            doneCustomer = true;
            txtCusName.setText(cus.getCustomerName());
            txtContact.setText(Integer.toString(cus.getContact()));
            txtCusAddress.setText(cus.getAddress());
            txtQty.requestFocus();
            btnAdd.setDisable(true);
            btnReserve.setDisable(false);
            btnRelease.setDisable(true);
            btnRemove.setDisable(true);
            btnUpdate.setDisable(true);
        } else {
            txtCusAddress.requestFocus();
        }
    }

    @FXML
    void txtCusAddressAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    @FXML
    void txtCusNameAction(ActionEvent event) {
        txtContact.requestFocus();
    }

    @FXML
    void txtPaidAction(ActionEvent event) {
        paid = Integer.parseInt(txtPaid.getText());
        balance = paid - price;
        lblBal.setText("Balance (rs) : " + balance + " /=");
        if (balance >= 0) {
            btnSend.setDisable(false);
            lblBal.setTextFill(Paint.valueOf("green"));
        } else {
            lblBal.setTextFill(Paint.valueOf("red"));
            btnSend.setDisable(true);
        }
    }

    @FXML
    void txtQtyAction(ActionEvent event) {
        try {
            qty = Integer.parseInt(txtQty.getText());
        } catch (Exception e){
            new Alert(Alert.AlertType.INFORMATION, "ERROR \nPlease input qty again", ButtonType.CLOSE).show();
        }
        price  = qty * 100;
        lblPrice.setText("Price (rs) : " + price + " /=");
        lblPrice.setTextFill(Paint.valueOf("blue"));
        txtPaid.setText("");
        btnSend.setDisable(true);
        txtPaid.setDisable(false);
        txtPaid.requestFocus();
    }

    @FXML
    void txtSearchCusKeyPressed(KeyEvent event) {
        ArrayList<CustomerDTO> allCustomersdto = null;
        try {
            allCustomersdto = customerService.searchAllCustomers(txtSearchCus.getText());
        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
        ArrayList<CustomerTM> allCus = new ArrayList<>();
        for (CustomerDTO dto :
                allCustomersdto) {
            allCus.add(new CustomerTM(dto.getCustomerID(),dto.getCustomerName(),dto.getContact(),dto.getAddress()));
        }
        tblCus.setItems(FXCollections.observableArrayList(allCus));
    }

    @FXML
    void txtSearchcusAction(ActionEvent event) {
        txtCusName.requestFocus();
    }

    @FXML
    void initialize() {
        assert lblCusID != null : "fx:id=\"lblCusID\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert txtSearchCus != null : "fx:id=\"txtSearchCus\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert txtCusName != null : "fx:id=\"txtCusName\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert txtContact != null : "fx:id=\"txtContact\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert btnReserve != null : "fx:id=\"btnReserve\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert btnRelease != null : "fx:id=\"btnRelease\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert txtCusAddress != null : "fx:id=\"txtCusAddress\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert btnRemove != null : "fx:id=\"btnRemove\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert txtQty != null : "fx:id=\"txtQty\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert lblPrice != null : "fx:id=\"lblPrice\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert txtPaid != null : "fx:id=\"txtPaid\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert lblBal != null : "fx:id=\"lblBal\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert btnSend != null : "fx:id=\"btnSend\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert tblCus != null : "fx:id=\"tblCus\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert lblWelcome != null : "fx:id=\"lblWelcome\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'AddOrder.fxml'.";
        assert lblOCount != null : "fx:id=\"lblOCount\" was not injected: check your FXML file 'AddOrder.fxml'.";

    }

    private void handleKeyPress(KeyEvent event) {
        System.out.println("key pressed .....................");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblWelcome.setText("Welcome " + LoginController.userName + " !");
        setTimeDate();
        tblCus.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        tblCus.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        tblCus.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ContactNumber"));
        tblCus.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Address"));
        try {
            UnicastRemoteObject.exportObject(this, 0);
            customerService.register(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        resetUI();
    }

    private void loadtblCus () {
        ArrayList<CustomerDTO> allCustomersdto = null;
        try {
            allCustomersdto = customerService.getAllCustomers();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.INFORMATION,e.getMessage(), ButtonType.CLOSE).show();
        }
        ArrayList<CustomerTM> allCus = new ArrayList<>();
        for (CustomerDTO dto :
                allCustomersdto) {
            allCus.add(new CustomerTM(dto.getCustomerID(),dto.getCustomerName(),dto.getContact(),dto.getAddress()));
        }
        tblCus.setItems(FXCollections.observableArrayList(allCus));
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
        loadtblCus();
    }
}