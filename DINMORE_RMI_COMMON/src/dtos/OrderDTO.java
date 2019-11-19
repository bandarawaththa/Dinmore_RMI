package dtos;

import java.io.Serializable;

public class OrderDTO implements Serializable {
    private String OrderID;
    private String addOrderTime;
    private String cusID;
    private String telOpID;
    private int qty;
    private String cheffID;
    private String startTime;
    private String endTime;
    private String count;
    private String date;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public OrderDTO() {}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OrderDTO ? ((OrderDTO)obj).addOrderTime.equalsIgnoreCase(this.addOrderTime) && ((OrderDTO)obj).date.equalsIgnoreCase(this.date) && ((OrderDTO)obj).cusID.equalsIgnoreCase(this.cusID) && ((OrderDTO)obj).telOpID.equalsIgnoreCase(this.telOpID) && ((OrderDTO)obj).qty==this.qty: false;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getAddOrderTime() {
        return addOrderTime;
    }

    public void setAddOrderTime(String addOrderTime) {
        this.addOrderTime = addOrderTime;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getTelOpID() {
        return telOpID;
    }

    public void setTelOpID(String telOpID) {
        this.telOpID = telOpID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getCheffID() {
        return cheffID;
    }

    public void setCheffID(String cheffID) {
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}