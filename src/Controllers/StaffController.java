package Controllers;

import Backend.DatabaseServices;
import Models.Booking;
import Models.Customer;
import java.util.ArrayList;

public class StaffController {

    public static boolean manageDogBooking(Customer customerDetails, Booking bookingDetails, ArrayList dogs) {
        try {
            DatabaseServices.manageDogBooking(customerDetails, bookingDetails, dogs);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

}
