package coheal.entities.user;

import java.util.ArrayList;

/**
 *
 * @author wajdi's pc
 */
public class Badge {
    //var 
    private ArrayList<User> listUsers = new ArrayList<>();
    private int badgeId;
    private String badgeName;
    private String imageUrl;

    public ArrayList<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(ArrayList<User> listUsers) {
        this.listUsers = listUsers;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
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

    //getters & setters
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //toString

    @Override
    public String toString() {
        return "Badge{" + "listUsers=" + listUsers + ", badgeId=" + badgeId + ", badgeName=" + badgeName + ", imageUrl=" + imageUrl + '}';
    }

    
    
}
