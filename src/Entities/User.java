package Entities;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author wajdi's pc
 */
public class User {
    //user var
    
    private ArrayList<Badge> listBadges = new ArrayList<>();
    private ArrayList<Role> listRoles = new ArrayList<>();
    protected int userId;
    protected String email;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected Date dateOfBirth;
    
    //User constructor
    public User(int userId, String email, String password, String firstName, String lastName, Date dateOfBirth) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    //getters & setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    //to string
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + '}'+"/n";
    }
    
    
    
    
}
