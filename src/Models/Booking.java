package Models;


public class Booking {
    private int bookingID;
    private String date;
    private String time;
    private int amountOfPets;
    private Double totalCost;
    private int customerID;
    
    public Booking(){ 
    }

    public Booking(String date, String time, int amountOfPets, Double totalCost) {
        this.date = date;
        this.time = time;
        this.amountOfPets = amountOfPets;
        this.totalCost = totalCost;
    }

    public Booking(int bookingID, String date, String time, int amountOfPets, Double totalCost, int customerID) {
        this.bookingID = bookingID;
        this.date = date;
        this.time = time;
        this.amountOfPets = amountOfPets;
        this.totalCost = totalCost;
        this.customerID = customerID;
    }
    

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAmountOfDogs() {
        return amountOfPets;
    }

    public void setAmountOfDogs(int amountOfPets) {
        this.amountOfPets = amountOfPets;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
