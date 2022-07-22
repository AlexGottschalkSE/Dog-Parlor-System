package Controllers;

import Backend.DatabaseServices;
import Models.StaffMember;

public class AdminController {

    //STAFF MEMBER CONTROLLER 
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

    public static boolean updateStaffMember(StaffMember updatedStaffDetails) {
        try {
            return DatabaseServices.updateStaffMember(updatedStaffDetails);
        } catch (Exception exc) {
            System.out.println(exc);
            return false;
        }
    }

    //END STAFF MEMBER CONTROLLER 
    //DOG BREED CONTROLLER
    public static boolean addNewDogBreed(String name) {
        try {
            return DatabaseServices.addNewDogBreed(name);
        } catch (Exception exc) {
            System.out.println(exc);
            return false;
        }
    }

    public static boolean removeDogBreed(String name) {
        try {
            return DatabaseServices.removeDogBreed(name);
        } catch (Exception exc) {
            System.out.println(exc);
            return false;
        }
    }

    //END DOG BREED CONTROLLER
    //CAT BREED CONTROLLER
    public static boolean addNewCatBreed(String name) {
        try {
            return DatabaseServices.addNewCatBreed(name);
        } catch (Exception exc) {
            System.out.println(exc);
            return false;
        }
    }

    public static boolean removeCatBreed(String name) {
        try {
            return DatabaseServices.removeCatBreed(name);
        } catch (Exception exc) {
            System.out.println(exc);
            return false;
        }
    }

}
