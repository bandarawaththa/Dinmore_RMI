package tm;

public class OrderTM {
    String OperatorID;
    String CustomerID;
    int qty;
    String OrderTime;
    String orderDate;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public OrderTM() {}

    public OrderTM(String operatorID, String customerID, int qty, String orderTime, String orderDate) {
        OperatorID = operatorID;
        CustomerID = customerID;
        this.qty = qty;
        OrderTime = orderTime;
        this.orderDate = orderDate;
    }

    public String getOperatorID() {
        return OperatorID;
    }

    public void setOperatorID(String operatorID) {
        OperatorID = operatorID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }
}
