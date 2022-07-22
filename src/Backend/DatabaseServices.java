package Backend;

import Models.StaffMember;
import java.sql.*;

public class DatabaseServices {

    public static Statement myStmt;
    public static ResultSet myRs;

    public static String dbUrl = "jdbc:mysql://102.182.112.47/clawsandpaws";
    public static String user = "root";
    public static String pass = "Alexgott2013#";
    private static Connection connection;

    public static void main(String[] args) {
    }

    //The following method establishes connection with the database. 
    //Call getConn() and assign it to the global variable connection for use. (See getStaffMemberByName() method for example)
    public static Connection getConn() throws SQLException {
        Connection myConn;
        try {
            myConn = DriverManager.getConnection(dbUrl, user, pass);
            return myConn;
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }

    //Takes the surname of the staff member trying to log in and runs a query to find this staff member in the database.
    //Returns object StaffMember, populated with the details of the found staff member.
    //If no staff member is found, it returns null.
    public static StaffMember getStaffMemberByName(String surname) throws Exception {
        try {
            connection = getConn();
            PreparedStatement staffDetails = connection.prepareStatement(
                    "SELECT * FROM staff WHERE surname = ?");
            staffDetails.setString(1, surname);
            ResultSet staffDetailsRs = staffDetails.executeQuery();

            if (staffDetailsRs.next()) {//Found user in database
                StaffMember member = new StaffMember();
                member.setStaffID(staffDetailsRs.getInt("staffID"));
                member.setName(staffDetailsRs.getString("name"));
                member.setSurname(staffDetailsRs.getString("surname"));
                member.setPassword(staffDetailsRs.getString("password"));
                member.setEmail(staffDetailsRs.getString("email"));
                member.setPermission(staffDetailsRs.getInt("permission"));

                return member;
            } else {//No user found
                return null;
            }
        } catch (Exception exc) {
            System.out.println(exc);
            throw exc;
        } finally {
            connection.close();
        }
    }

    public static boolean insertNewStaffMember(StaffMember staff) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO STAFF(name, surname, password, email, permission) VALUES (?,?,?,?,?)");
                statement.setString(1, staff.getName());
                statement.setString(2, staff.getSurname());
                statement.setString(3, staff.getPassword());
                statement.setString(4, staff.getEmail());
                statement.setInt(5, staff.getPermission());
                statement.executeUpdate();
                return true;
            }
        } catch (Exception exc) {
            System.out.println(exc);

        } finally {
            connection.close();
        }
        return false;
    }

    public static boolean dismissStaffMember(String name, String surname, String reason, String date) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement dismissalStatement = connection.prepareStatement(
                        "INSERT INTO STAFFDISMISSALS(name, surname, reason, dateOfDismissal) VALUES (?,?,?,?)");
                dismissalStatement.setString(1, name);
                dismissalStatement.setString(2, surname);
                dismissalStatement.setString(3, reason);
                dismissalStatement.setString(4, date);
                dismissalStatement.executeUpdate();

                PreparedStatement removalStatement = connection.prepareStatement(
                        "DELETE FROM STAFF WHERE surname = ?");
                removalStatement.setString(1, surname);
                removalStatement.executeUpdate();

                return true;
            }
        } catch (Exception exc) {
            System.out.println(exc);

        } finally {
            connection.close();
        }
        return false;
    }
}
