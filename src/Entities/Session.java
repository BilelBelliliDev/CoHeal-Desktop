/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author acer
 */
public class Session {
    private int therp_id;
    private int user_id;
    private String title;
    private String description;

    public Session(int therp_id, int user_id, String title, String description) {
        this.therp_id = therp_id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
    }

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

    @Override
    public String toString() {
        return "Session{" + "therp_id=" + therp_id + ", user_id=" + user_id + ", title=" + title + ", description=" + description + '}';
    }
    
    
    
    
}
