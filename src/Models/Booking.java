package Models;

import java.sql.Date;
import java.sql.Time;

public class Booking {
    private int bookingID;
    private Date date;
    private Time time;
    private int amountOfDogs;
    private Double totalCost;
    private int customerID;
    
    public Booking(){ 
    }

    public Booking(Date date, Time time, int amountOfDogs, Double totalCost) {
        this.date = date;
        this.time = time;
        this.amountOfDogs = amountOfDogs;
        this.totalCost = totalCost;
    }

    public Booking(int bookingID, Date date, Time time, int amountOfDogs, Double totalCost, int customerID) {
        this.bookingID = bookingID;
        this.date = date;
        this.time = time;
        this.amountOfDogs = amountOfDogs;
        this.totalCost = totalCost;
        this.customerID = customerID;
    }
    

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getAmountOfDogs() {
        return amountOfDogs;
    }

    public void setAmountOfDogs(int amountOfDogs) {
        this.amountOfDogs = amountOfDogs;
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
