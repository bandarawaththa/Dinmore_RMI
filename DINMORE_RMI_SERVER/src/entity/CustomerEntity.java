package entity;

public class CustomerEntity {
    private int CustomerID;
    private String CustomerName;
    private int ContactNumber;
    private String Address;

    public CustomerEntity(int customerID, String customerName, int contactNumber, String address) {
        CustomerID = customerID;
        CustomerName = customerName;
        ContactNumber = contactNumber;
        Address = address;
    }

    public CustomerEntity(String customerName, int contactNumber, String address) {
        CustomerName = customerName;
        ContactNumber = contactNumber;
        Address = address;
    }

    public CustomerEntity() {}

    public CustomerEntity(int customerID) {
        this.CustomerID = customerID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public int getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(int contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}