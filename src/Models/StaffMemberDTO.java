package Models;

public class StaffMemberDTO {

    private int staffID;
    private String name;
    private String surname;
    private int permission;
    private static String OTP;
    private String email;
    public StaffMemberDTO() {
    }

    public StaffMemberDTO(StaffMember staff) {
        this.name = staff.getName();
        this.surname = staff.getSurname();
        this.permission = staff.getPermission();
        this.staffID = staff.getStaffID();
        this.email = staff.getEmail();
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public static String getOTP() {
        return OTP;
    }

    public static void setOTP(String code) {
        OTP = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
