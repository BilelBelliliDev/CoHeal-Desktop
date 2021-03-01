package Entities.User;

import java.sql.Date;
import java.sql.Timestamp;
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
    protected double balance;
    protected int score;
    protected boolean isLimited;
    protected Timestamp limitedAt;
    protected boolean isDeleted;
    protected Timestamp deletedAt;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;
    

    //getters & setters

    public ArrayList<Badge> getListBadges() {
        return listBadges;
    }

    public void setListBadges(ArrayList<Badge> listBadges) {
        this.listBadges = listBadges;
    }

    public ArrayList<Role> getListRoles() {
        return listRoles;
    }

    public void setListRoles(ArrayList<Role> listRoles) {
        this.listRoles = listRoles;
    }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isIsLimited() {
        return isLimited;
    }

    public void setIsLimited(boolean isLimited) {
        this.isLimited = isLimited;
    }

    public Timestamp getLimitedAt() {
        return limitedAt;
    }

    public void setLimitedAt(Timestamp limitedAt) {
        this.limitedAt = limitedAt;
    }
    
    

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" + "listBadges=" + listBadges + ", listRoles=" + listRoles + ", userId=" + userId + ", email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", balance=" + balance + ", score=" + score + ", isLimited=" + isLimited + ", limitedAt=" + limitedAt + ", isDeleted=" + isDeleted + ", deletedAt=" + deletedAt + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

 
    
    
    
    
    
}
