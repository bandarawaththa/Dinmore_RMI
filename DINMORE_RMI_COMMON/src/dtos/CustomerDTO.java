package dtos;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

    public CustomerDTO() {}

    public CustomerDTO(String customerID) {
        this.customerID = customerID;
    }

    public CustomerDTO(int contact) {
        this.contact = contact;
    }

    private String customerID;
    private String customerName;
    private int contact;
    private String Address;

    public CustomerDTO(String customerID, String customerName, int contact, String address) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.contact = contact;
        Address = address;
    }

    public CustomerDTO(String customerName, int contact, String address) {
        this.customerName = customerName;
        this.contact = contact;
        Address = address;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}