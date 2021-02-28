package Entities.recipe;
import java.sql.Timestamp;

public class Recipe {
    private int recipeId;
    private int userID;
    private String title;
    private String description;
    private float calories;
    private String imgUrl;
    private boolean isDeleted;
    private Timestamp deletedAt;
    private Timestamp created_At;
    private Timestamp modified_at;

    public Recipe(){}

    public Recipe(int recipeId, int userID, String title, String description, float calories, String imgUrl, boolean isDeleted, Timestamp deletedAt, Timestamp created_At, Timestamp modified_at) {
        this.recipeId = recipeId;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.calories = calories;
        this.imgUrl = imgUrl;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
        this.created_At = created_At;
        this.modified_at = modified_at;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Timestamp getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Timestamp created_At) {
        this.created_At = created_At;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    @Override
    public String toString() {
        return "Recipe{" + "recipeId=" + recipeId + ", userID=" + userID + ", title=" + title + ", description=" + description + ", calories=" + calories + ", imgUrl=" + imgUrl + ", isDeleted=" + isDeleted + ", deletedAt=" + deletedAt + ", created_At=" + created_At + ", modified_at=" + modified_at + '}';
    }
    
    
    
    

}