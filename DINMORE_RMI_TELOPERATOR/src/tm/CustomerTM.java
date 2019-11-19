package tm;

public class CustomerTM {
    private String CustomerID;
    private String CustomerName;
    private int ContactNumber;
    private String Address;

    public CustomerTM() {}

    public CustomerTM(String customerID, String customerName, int contactNumber, String address) {
        CustomerID = customerID;
        CustomerName = customerName;
        ContactNumber = contactNumber;
        Address = address;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
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