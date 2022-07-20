package Controllers;

import Backend.DatabaseServices;
import Models.StaffMember;
import Models.StaffMemberDTO;

public class LoginController {

    public static StaffMemberDTO findStaffMember(String surname, String password) {
        // Get staff member from db
        try {
            StaffMember staffDetails = DatabaseServices.getStaffMemberByName(surname);
            // If the user is found in the database
            if (staffDetails != null) {
                // Check Password
                if (validatePassword(password, staffDetails.getPassword()) != false) {
                    // If Valid Login
                    StaffMemberDTO staffDTO = new StaffMemberDTO(staffDetails);

                    return staffDTO;
                } else {
                    // If Password Incorrect
                    return null;
                }
            } else {
                // If staff member not found
                return null;
            }
        } catch (Exception exc) {
            return null;
        }
    }

    public static boolean validatePassword(String password, String passwordHashed) {
        return (password.equals(passwordHashed));
    }
}
