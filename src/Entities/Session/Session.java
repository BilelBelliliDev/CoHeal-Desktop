package Entities.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Timestamp;

/**
 *
 * @author acer
 */
public class Session {
    private int session_id;
    private int therp_id;
    private int user_id;
    private String title;
    private String description;
    private int num_of_days;
    private Timestamp created_at;
    private Timestamp modified_at;
    private Timestamp is_deleted;
    private Timestamp deleted_at;

  
    
    public Session() {
    }

    public int getTherp_id() {
        return therp_id;
    }

    public void setTherp_id(int therp_id) {
        this.therp_id = therp_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public int getNum_of_days() {
        return num_of_days;
    }

    public void setNum_of_days(int num_of_days) {
        this.num_of_days = num_of_days;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    public Timestamp getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Timestamp is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    @Override
    public String toString() {
        return "Session{" + "therp_id=" + therp_id + ", user_id=" + user_id + ", title=" + title + ", description=" + description + ", num_of_days=" + num_of_days + ", created_at=" + created_at + ", modified_at=" + modified_at + ", is_deleted=" + is_deleted + ", deleted_at=" + deleted_at + '}';
    }

  
    
    
    
    
}
