package Entities;

import java.util.ArrayList;

/**
 *
 * @author wajdi's pc
 */
public class Badge {
    //var 
    private ArrayList<User> listUsers = new ArrayList<>();

    private String badgeName;
    private String imageUrl;
    
    //constructor

    public Badge(String badgeName, String imageUrl) {
        this.badgeName = badgeName;
        this.imageUrl = imageUrl;
    }
    //getters & setters

    public ArrayList<User> getBadgeId() {
        return listUsers;
    }

    public void setBadgeId(ArrayList<User> listUsers) {
        this.listUsers = listUsers;
    }

 
    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    //toString

    @Override
    public String toString() {
        return "Badge{" + "listUsers=" + listUsers + ", badgeName=" + badgeName + ", imageUrl=" + imageUrl + '}';
    }
    
    
}
