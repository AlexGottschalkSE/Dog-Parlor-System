package Backend;

import Models.Booking;
import Models.Customer;
import Models.Dog;
import Models.StaffMember;
import java.sql.*;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;

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

    public static boolean updateStaffMember(StaffMember newDetails) throws SQLException {
        try {
            connection = getConn();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE STAFF SET surname = ?, password = ?, email = ?, permission = ? WHERE surname = ?");
            statement.setString(1, newDetails.getSurname());
            statement.setString(2, newDetails.getPassword());
            statement.setString(3, newDetails.getEmail());
            statement.setInt(4, newDetails.getPermission());
            statement.setString(5, newDetails.getName());
            statement.executeUpdate();
            return true;
        } catch (Exception exc) {
        } finally {
            connection.close();
        }
        return false;
    }

    public static boolean addNewDogBreed(String name) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO DOGBREEDS(name) VALUES (?)");
                statement.setString(1, name);
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

    public static boolean removeDogBreed(String name) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement removalStatement = connection.prepareStatement(
                        "DELETE FROM DOGBREEDS WHERE name = ?");
                removalStatement.setString(1, name);
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

    public static boolean addNewCatBreed(String name) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO CATBREEDS(name) VALUES (?)");
                statement.setString(1, name);
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

    public static boolean removeCatBreed(String name) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement removalStatement = connection.prepareStatement(
                        "DELETE FROM CATBREEDS WHERE name = ?");
                removalStatement.setString(1, name);
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

    //STAFF BACKEND FUNCTIONALITY
    public static void manageDogBooking(Customer customerDetails, Booking bookingDetails, ArrayList dogs) throws SQLException {
        try {
            connection = getConn();

            if (connection != null) {
                int foundCustomerID = checkForCustomerExistance(customerDetails);
                if (foundCustomerID > 0) {
                    //Found customer in database
                    addBooking(foundCustomerID, bookingDetails);
                    addDogs(foundCustomerID, dogs);
                } else {
                    int newCustomerID = addNewCustomer(customerDetails);
                    addBooking(newCustomerID, bookingDetails);
                    addDogs(newCustomerID, dogs);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            connection.close();
        }
    }

    public static int checkForCustomerExistance(Customer customerDetails) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement addBookingStatement = connection.prepareStatement(
                        "SELECT * FROM CUSTOMERS WHERE EMAIL = ?");
                addBookingStatement.setString(1, customerDetails.getEmail());
                ResultSet customerRs = addBookingStatement.executeQuery();

                if (customerRs.next()) {
                    int customerID = (customerRs.getInt("customerID"));
                    return customerID;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            connection.close();
        }
        return 0;
    }

    //METHOD TO ADD BOOKING TO DATABASE
    public static boolean addBooking(int customerID, Booking bookingDetails) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement addBookingStatement = connection.prepareStatement(
                        "INSERT INTO BOOKINGS(DateOfBooking, TimeOfBooking, AmountOfPets, TotalCost, CustomerID) VALUES (?,?,?,?,?)");
                addBookingStatement.setString(1, bookingDetails.getDate());
                addBookingStatement.setString(2, bookingDetails.getTime());
                addBookingStatement.setInt(3, bookingDetails.getAmountOfDogs());
                addBookingStatement.setDouble(4, bookingDetails.getTotalCost());
                addBookingStatement.setInt(5, customerID);
                addBookingStatement.executeUpdate();

                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            connection.close();
        }
        return false;

    }

    public static boolean addDogs(int customerID, ArrayList<Dog> dogs) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement addDog = connection.prepareStatement(
                        "INSERT INTO DOGS(name, size, visits, customerID, dogBreedID) VALUES (?,?,?,?,?)");
                for (Dog dogDetails : dogs) {
                    addDog.setString(1, dogDetails.getName());
                    addDog.setString(2, dogDetails.getSize());
                    addDog.setInt(3, 5);
                    addDog.setInt(4, customerID);
                    addDog.setDouble(5, dogDetails.getBreed());
                    addDog.executeUpdate();
                }

                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            connection.close();
        }
        return false;

    }

    public static int addNewCustomer(Customer customerDetails) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO CUSTOMERS(name, surname, phoneNumber, email) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, customerDetails.getName());
                statement.setString(2, customerDetails.getSurname());
                statement.setString(3, customerDetails.getPhoneNumber());
                statement.setString(4, customerDetails.getEmail());
                statement.executeUpdate();

                ResultSet key = statement.getGeneratedKeys();
                int newUserID = 0;
                if (key.next()) {
                    newUserID = key.getInt(1);
                }
                return newUserID;
            }
        } catch (Exception exc) {
            System.out.println(exc);

        } finally {
            connection.close();
        }
        return 0;
    }
}
