/**
 * Name: Karley Conroy, Isaac Bordfeld, Shiv Patel, Saiesha Sharma
 * Group 10
 * Date: 13 April 2020
 * Final project
 * Description: This is the users class
 */
import java.util.UUID;
import java.io.Serializable;

abstract class User implements Serializable{
    // user's personal info
    private String username;
    private String password;
    private String uniqueID;
    private String firstName;
    private String lastName;
    private String fullName;

    // user's role
    private String role;

    // this is a constructor with the users information
    public User(String username, String password, String firstName, String lastName, String role)
    {
        this.username = username;
        this.password = password;
        uniqueID = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.fullName = this.firstName + " " + this.lastName;
    }

    // this is a constructor with the users information
    public User(String username, String password, String firstName, String lastName)
    {
        this.username = username;
        this.password = password;
        uniqueID = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = this.firstName + " " + this.lastName;
    }

    // this is a constructor with the users information
    public User(String uniqueID, String username, String password, String firstName, String lastName, String role)
    {
        this.uniqueID = uniqueID;
        this.username = username;
        this.password = password;
        uniqueID = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.fullName = this.firstName + " " + this.lastName;
    }

    // this is the getter for the users username
    public String getUsername() {
        return username;
    }

    // this is the setter for the users username
    public void setUsername(String username) {
        this.username = username;
    }

    // this is the getter for the users password
    public String getPassword() {
        return password;
    }

    // this is the setter for the users password
    public void setPassword(String password) {
        this.password = password;
    }

    // this is the getter for the users id number
    public String getUniqueID() {
        return uniqueID;
    }

    // this is the setter for the users id number
    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    // this is the getter for the users first name
    public String getFirstName() {
        return firstName;
    }

    // this is the setter for the users first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // this is the getter for the users last name
    public String getLastName() {
        return lastName;
    }

    // this is the setter for the users last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // this is the getter for the role of the user
    public String getRole() {
        return role;
    }

    // this is the setter for the role of the user
    public void setRole(String role) {
        this.role = role;
    }

    // this is the getter for the full name
    public String getFullName() {
        return fullName;
    }

    // this is the setter for the full name
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // this is how a user would login to system to access there information
    public void login(String user, String password){
        if (user.equals(getUsername()) && password.equals(getPassword())){
            System.out.println( "Logged in");
        } else{
            System.out.println( "Log in failed");
        }
    }

    // this will create how the users information will print out and be seen by the user
    @Override
    public String toString() {
        return "User{" +
                "uniqueID='" + uniqueID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
