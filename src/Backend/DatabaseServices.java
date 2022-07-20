package Backend;

import Models.StaffMember;
import java.sql.*;

public class DatabaseServices {

    public static Statement myStmt;
    public static ResultSet myRs;

    public static String dbUrl = "jdbc:mysql://127.0.0.1:3306/clawsandpaws";
    public static String user = "student";
    public static String pass = "student";
    private static Connection connection;

    public static void main(String[] args) {
    }

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
}
