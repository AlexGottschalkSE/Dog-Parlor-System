package Backend;

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
}
