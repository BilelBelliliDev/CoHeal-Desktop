package coheal.entities.Recipe;
import java.sql.Timestamp;

public class RecipeCategory {
    private int catId;
    private String name;
    private String imgUrl;
    private boolean isDeleted;
    private Timestamp deletedAt;
    private Timestamp created_At;
    private Timestamp modified_at;

    public RecipeCategory(){};
    
    public RecipeCategory(int catId, String name, String imgUrl, boolean isDeleted, Timestamp deletedAt, Timestamp created_At, Timestamp modified_at) {
        this.catId = catId;
        this.name = name;
        this.imgUrl = imgUrl;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
        this.created_At = created_At;
        this.modified_at = modified_at;
    }

    public RecipeCategory(int aInt, String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCatId() {
        return catId;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public Timestamp getCreated_At() {
        return created_At;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setCreated_At(Timestamp created_At) {
        this.created_At = created_At;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    @Override
    public String toString() {
        return "RecipeCategory{" + "catId=" + catId + ", name=" + name + ", imgUrl=" + imgUrl + ", isDeleted=" + isDeleted + ", deletedAt=" + deletedAt + ", created_At=" + created_At + ", modified_at=" + modified_at + '}';
    }
    
    
}
