/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Task;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Task {
    private int taskId;
    //  private List<User> users;
    private TaskCategory category;
    private String imgUrl;
    private String title;
    private String description;
    private int numOfDays;
    private int minUsers;
    private int maxUsers;
    private List<TaskActions> actions;
    private boolean isDeleted;
    private Timestamp deletedAt;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public Task(int taskId, /*List<User> users,*/ TaskCategory category, String imgUrl, String title, String description, int numOfDays, int minUsers, int maxUsers,List<TaskActions> actions, boolean isDeleted, Timestamp deletedAt, Timestamp createdAt, Timestamp modifiedAt) {
        this.taskId = taskId;
        // this.users = users;
        this.category = category;
        this.imgUrl = imgUrl;
        this.title = title;
        this.description = description;
        this.numOfDays = numOfDays;
        this.minUsers = minUsers;
        this.maxUsers = maxUsers;
        this.actions=actions;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    
    public Task( String imgUrl, String title, String description, int numOfDays, int minUsers, int maxUsers) {
       
        this.imgUrl = imgUrl;
        this.title = title;
        this.description = description;
        this.numOfDays = numOfDays;
        this.minUsers = minUsers;
        this.maxUsers = maxUsers;
    }

    public Task() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /* public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }*/
    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public int getMinUsers() {
        return minUsers;
    }

    public void setMinUsers(int minUsers) {
        this.minUsers = minUsers;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public List<TaskActions> getActions() {
        return actions;
    }

    public void setActions(List<TaskActions> actions) {
        this.actions = actions;
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
        return "Task{" + "taskId=" + taskId + ", category=" + category + ", imgUrl=" + imgUrl + ", title=" + title + ", description=" + description + ", numOfDays=" + numOfDays + ", minUsers=" + minUsers + ", maxUsers=" + maxUsers + ", actions=" + actions + ", isDeleted=" + isDeleted + ", deletedAt=" + deletedAt + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + '}';
    }
}
