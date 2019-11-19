package controllers;

import connector.ProxyHandler;
import dtos.CheffDTO;
import dtos.CustomerDTO;
import dtos.OrderDTO;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.StartUp;
import observers.OrderObserver;
import service.custom.CheffService;
import service.custom.CustomerService;
import service.custom.OrderService;
import service.custom.TelOperatorService;
import tm.OrderTM;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class CompleteOrderController implements Initializable, OrderObserver {
    static OrderService orderService = ProxyHandler.getInstance().getOrderService();
    static CustomerService customerService = ProxyHandler.getInstance().getCustomerService();
    static TelOperatorService telOperatorService = ProxyHandler.getInstance().getTelOperatorService();
    static CheffService cheffService = ProxyHandler.getInstance().getCheffService();
    static ArrayList<OrderDTO> allOrders;
    static OrderDTO order;

    private Timeline timeline;
    private Text text;
    int mins = 0, secs = 0, millis = 0;
    boolean sos = true;

    @FXML
    private Label lblWelcome;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblOCount;

    @FXML
    private Label lblCusID;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblCusName;

    @FXML
    private Label lblAddress;

    @FXML
    private Button btnCount;

    @FXML
    private Label lblTelOp;

    @FXML
    private Label lblQTY;

    @FXML
    private Button btnChat;

    @FXML
    private Button btnTake;

    @FXML
    private Button btnFinish;

    @FXML
    private Button btnFinishTake;

    @FXML
    private TableView<OrderTM> tblOrder;

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
        glow.setColor(Color.DARKMAGENTA);
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
    void btnCountAction(ActionEvent event) {
        if (false == sos){
            timeline.stop();
            sos = true;
            btnCount.setStyle("-fx-background-color: green; ");
        } else {
            timeline.play();
            sos = false;
            btnCount.setStyle("-fx-background-color: red; ");
        }

    }

    @FXML
    void btnCountMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnCount);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKRED);
        if (false == sos){
            glow.setColor(Color.DARKGREEN);
        }
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnCount.setEffect(glow);
    }

    @FXML
    void btnCountMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnCount);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnCount.setEffect(null);
    }

    @FXML
    void btnFinishAction(ActionEvent event) {
        boolean added = false;
        try {
            order.setCheffID(cheffService.searchCheff(new CheffDTO(LoginController.userName)).getId());
            timeline.stop();
            order.setCount(Integer.toString(mins));
            order.setEndTime(lblTime.getText());
            added = orderService.addOrder(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (added){
            new Alert(Alert.AlertType.INFORMATION,"Order Finishing SUCCESS", ButtonType.CLOSE).show();
            resetUI();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Order Finishing FAILIER", ButtonType.CLOSE).show();
        }
    }

    @FXML
    void btnFinishMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnFinish);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKGREEN);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnFinish.setEffect(glow);
    }

    @FXML
    void btnFinishMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnFinish);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnFinish.setEffect(null);
    }

    @FXML
    void btnFinishTakeAction(ActionEvent event) {
        boolean added = false;
        try {
            order.setCheffID(cheffService.searchCheff(new CheffDTO(LoginController.userName)).getId());
            timeline.stop();
            order.setCount(Integer.toString(mins));
            order.setEndTime(lblTime.getText());
            added = orderService.addOrder(order);
        } catch (Exception e) {
            e.getMessage();
        }
        if (added){
            resetUI();
            order = allOrders.get(0);
            try {
                orderService.removeFromQueue(order);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            getOrder();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Order Finishing FAILIER", ButtonType.CLOSE).show();
        }
    }

    @FXML
    void btnFinishTakeMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnFinishTake);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKBLUE);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnFinishTake.setEffect(glow);
    }

    @FXML
    void btnFinishTakeMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnFinishTake);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnFinishTake.setEffect(null);
    }

    @FXML
    void btnTakeAction(ActionEvent event) {
        order = allOrders.get(0);
        try {
            orderService.removeFromQueue(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        getOrder();
    }

    void getOrder () {
        lblCusID.setText("CUS" + order.getCusID());
        CustomerDTO cus = null;
        try {
            cus = customerService.searchCusFromID(Integer.parseInt(order.getCusID()));
            lblTelOp.setText(telOperatorService.searchFromID(Integer.parseInt(order.getTelOpID())).getUName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        order.setStartTime(lblTime.getText());
        lblContact.setText(Integer.toString(cus.getContact()));
        lblCusName.setText(cus.getCustomerName());
        lblAddress.setText(cus.getAddress());
        lblQTY.setText(Integer.toString(order.getQty()));
        btnTake.setDisable(true);
        btnFinish.setDisable(false);
        btnFinishTake.setDisable(false);
        tblOrder.setDisable(true);
        btnCount.setDisable(false);
        btnCount.setStyle("-fx-background-color: red; ");
        timeline.play();
        sos = false;
        lblWelcome.setDisable(true);
    }

    @FXML
    void btnTakeMouseEntered(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnTake);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.DARKBLUE);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        btnTake.setEffect(glow);
    }

    @FXML
    void btnTakeMouseExited(MouseEvent event) {
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(400), btnTake);
        scaleT.setToX(1);
        scaleT.setToY(1);
        scaleT.play();
        btnTake.setEffect(null);
    }

    @FXML
    void lblWelcomeMouseCliecked(MouseEvent event) {
        URL url = null;
        try {
            url = new File("DINMORE_RMI_CHEFAPPLICATION/assets/xmls/Login.fxml").toURI().toURL();
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
            LoginController.cheffStage.close();
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
    void tblCusMouseClicked(MouseEvent event) {
        try {
            OrderTM selectedItem = tblOrder.getSelectionModel().getSelectedItem();
            OrderDTO temp = new OrderDTO();
            temp.setAddOrderTime(selectedItem.getOrderTime());
            temp.setDate(selectedItem.getOrderDate());
            temp.setCusID(selectedItem.getCustomerID());
            temp.setTelOpID(selectedItem.getOperatorID());
            temp.setQty(selectedItem.getQty());
            for (OrderDTO dto :
                    allOrders) {
                if (dto.equals(temp)){
                    order = dto;
                    try {
                        orderService.removeFromQueue(order);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    getOrder();
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("OperatorID"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("OrderTime"));
        lblWelcome.setText("Welcome " + LoginController.userName + " !");
        try {
            UnicastRemoteObject.exportObject(this, 0);
            orderService.register(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        text = new Text("00:00:000");
        timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change(btnCount);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        setTimeDate();
        resetUI();
    }

    private void change(Button btn){
        if (millis == 1000){
            secs++;
            millis = 0;
        }
        if (secs == 60){
            mins++;
            secs = 0;
        }
        btn.setText((((mins / 10) == 0) ? "0" : "") + mins + ":" + (((secs / 10) == 0) ? "0" : "") + secs + ":" + (((millis / 10) == 0) ? "00" : ((millis / 100) == 0) ? "0" : "") + millis++);
    }

    private void loadTable () {
        try {
            allOrders = orderService.getAllFromQueue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ArrayList<OrderTM> allOrder = new ArrayList<>();
        for (OrderDTO dto :
                allOrders) {
            allOrder.add(new OrderTM(dto.getTelOpID(),dto.getCusID(),dto.getQty(),dto.getAddOrderTime(),dto.getDate()));
        }
        tblOrder.setItems(FXCollections.observableArrayList(allOrder));
    }

    private void resetUI () {
        loadTable();
        lblWelcome.setDisable(false);
        btnCount.setDisable(true);
        btnFinish.setDisable(true);
        btnFinishTake.setDisable(true);
        btnTake.setDisable(false);
        lblCusID.setText("Please Take an Order");
        lblContact.setText("Please Take an Order");
        lblCusName.setText("Please Take an Order");
        lblAddress.setText("Please Take an Order");
        lblTelOp.setText("null");
        lblQTY.setText("null");
        tblOrder.setDisable(false);
        lblWelcome.setDisable(true);
        btnCount.setStyle("-fx-background-color: green; ");
        btnCount.setText("00:00:000");
        secs = 0;
        mins = 0;
        millis = 0;
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
    public void update(ArrayList<OrderDTO> orders) throws Exception {
        ArrayList<OrderTM> allOrder = new ArrayList<>();
        allOrders = orders;
        for (OrderDTO dto :
                orders) {
            allOrder.add(new OrderTM(dto.getTelOpID(),dto.getCusID(),dto.getQty(),dto.getAddOrderTime(),dto.getDate()));
        }
        tblOrder.setItems(FXCollections.observableArrayList(allOrder));
    }
}