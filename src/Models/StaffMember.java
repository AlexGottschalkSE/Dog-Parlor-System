package Models;

public class StaffMember {

    private int staffID = 0;
    private String name;
    private String surname;
    private String password;
    private int permission;
    private String email;

    public StaffMember() {
    }


    public StaffMember(String name, String surname, String password, int permission, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.permission = permission;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
