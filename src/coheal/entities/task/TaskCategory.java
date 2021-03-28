/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.entities.task;

import java.sql.Timestamp;
import java.util.List;
import javafx.scene.image.ImageView;

/**
 *
 * @author Admin
 */
public class TaskCategory {

    private int catgid;
    private String name;
    private String imgUrl;
    private boolean isDeleted;
    private Timestamp deletedAt;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private ImageView img;
    
    public TaskCategory(int catgid,String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.catgid=catgid;
    }

    public TaskCategory() {
    }

    public TaskCategory(String name) {
       
        this.name = name;
    }
    
    

    public int getCatgid() {
        return catgid;
    }

    public void setCatgid(int catgid) {
        this.catgid = catgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    
    @Override
    public String toString() {
        return  " name=" + name + ", imgUrl=" + imgUrl +"\n";
    }
}
