package entity;

public class OrderEntity {
    private int OrderID;
    private String addOrderTime;
    private int cusID;
    private int telOpID;
    private int qty;
    private int cheffID;
    private String startTime;
    private String endTime;
    private int count;
    private String date;

    public OrderEntity(int orderID, String addOrderTime, int cusID, int telOpID, int qty, int cheffID, String startTime, String endTime, int count, String date) {
        OrderID = orderID;
        this.addOrderTime = addOrderTime;
        this.cusID = cusID;
        this.telOpID = telOpID;
        this.qty = qty;
        this.cheffID = cheffID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.count = count;
        this.date = date;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getAddOrderTime() {
        return addOrderTime;
    }

    public void setAddOrderTime(String addOrderTime) {
        this.addOrderTime = addOrderTime;
    }

    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public int getTelOpID() {
        return telOpID;
    }

    public void setTelOpID(int telOpID) {
        this.telOpID = telOpID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getCheffID() {
        return cheffID;
    }

    public void setCheffID(int cheffID) {
        this.cheffID = cheffID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
