/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.entities.Book;

import java.sql.Timestamp;

/**
 *
 * @author Marwen
 */
public class BookCategory {
        private int catId;
          private String name,imgUrl;
          private boolean isDeleted;
          private Timestamp deletedAt,createdAt,modifiedAt;
          

        public int getCatId() {
                return catId;
        }

        public void setCatId(int catId) {
                this.catId = catId;
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

        @Override
        public String toString() {
                return "BookCategory{" + "catId=" + catId + ", name=" + name + ", imgUrl=" + imgUrl + ", isDeleted=" + isDeleted + ", deletedAt=" + deletedAt + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + '}'+'\n';
        }
          
}
