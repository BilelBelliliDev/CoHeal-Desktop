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
