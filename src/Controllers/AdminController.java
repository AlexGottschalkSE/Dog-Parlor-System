package Controllers;

import Backend.DatabaseServices;
import Models.StaffMember;

public class AdminController {

    public static boolean addNewStaffMemberToDatabase(StaffMember member) {
        try {
            return DatabaseServices.insertNewStaffMember(member);
        } catch (Exception exc) {
            System.out.println(exc);
            return false;
        }
    }

    public static boolean dismissStaffMember(String name, String surname, String reason, String date) {
        try {
            return DatabaseServices.dismissStaffMember(name, surname, reason, date);
        } catch (Exception exc) {
            System.out.println(exc);
            return false;
        }
    }
}
