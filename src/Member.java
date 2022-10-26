import java.io.Serializable;

/*
    The Member class holds the information regarding an individual wishing to sign-up to the system.
    There are also two types of members. Admin & non-admin with the admin type requiring an additional code to login.

    @version 28 Apr 2019, 03:15 PM
    @author Ayon Shahrier, CSEDU-24th, Roll-05

    CSE-2112 OOP Project - Library Manager
 */

public class Member implements Serializable {
    private String password; // password for each member required to login
    private boolean isAdmin = false; // whether the member is an Admin, initially all are standard member
    private int id; // unique id given to member according to total sign-ups, required during login
    private String name;                    // individual's name
    private String email;                   // individual's email

    //constructing a member without an allocated id
    public Member(String password, String name, String email) {
        this.setName(name);
        this.setEmail(email);
        this.setId(0);
        this.setPassword(password);
    }

    //constructing a member with an allocated id
    public Member(String password, String name, String email, boolean isAdmin) {
        this.setName(name);
        this.setEmail(email);
        this.setId(0);
        this.setPassword(password);
        this.setAdmin(isAdmin);
    }

    //constructor with id & admin-state defined
    public Member(int id, String password, String name, String email, boolean isAdmin) {
        this.setName(name);
        this.setEmail(email);
        this.setId(id);
        this.setPassword(password);
        this.setAdmin(isAdmin);
    }

    //returns the name of a member
    public String getName() {
        return name;
    }
    //sets a new name for a member
    public void setName(String name) {
        this.name = name;
    }

    //returns the email of a member
    public String getEmail() {
        return email;
    }

    //sets a new email for a member
    public void setEmail(String email) {
        this.email = email;
    }

    //returns the id for a member
    public int getId() {
        return id;
    }

    //sets a new id for a member
    public void setId(int id) {
        this.id = id;
    }

    //returns the password for a member
    public String getPassword() {
        return password;
    }

    //sets a new password for a member
    public void setPassword(String password) {
        this.password = password;
    }

    //returns whether the member is an admin
    public boolean isAdmin() {
        return isAdmin;
    }

    //sets new admin-state for the member
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}