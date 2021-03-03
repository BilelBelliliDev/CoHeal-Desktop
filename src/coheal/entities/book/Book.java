/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.entities.book;

import java.sql.Timestamp;

/**
 *
 * @author Marwen
 */
public class Book {
        private int  bookId,catId,userId,views;
      private String imgUrl,filePath,author,title,description;
      private Timestamp deletedAt,createdAt,modifiedAt;
      private boolean isDeleted;
    
        public int getBookId() {
                return bookId;
        }

        public void setBookId(int bookId) {
                this.bookId = bookId;
        }

        public int getCatId() {
                return catId;
        }

        public void setCatId(int catId) {
                this.catId = catId;
        }

        public int getUserId() {
                return userId;
        }

        public void setUserId(int userId) {
                this.userId = userId;
        }

        public int getViews() {
                return views;
        }

        public void setViews(int views) {
                this.views = views;
        }

        public boolean getIsDeleted() {
                return isDeleted;
        }

        public void setIsDeleted(boolean isDeleted) {
                this.isDeleted = isDeleted;
        }

        public String getImgUrl() {
                return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
        }

        public String getFilePath() {
                return filePath;
        }

        public void setFilePath(String filePath) {
                this.filePath = filePath;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
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
                return "book{" + "bookId=" + bookId + ", catId=" + catId + ", userId=" + userId + ", views=" + views + ", isDeleted=" + isDeleted + ", imgUrl=" + imgUrl + ", filePath=" + filePath + ", author=" + author + ", title=" + title + ", description=" + description + ", deletedAt=" + deletedAt + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + '}'+'\n';
        }

}
