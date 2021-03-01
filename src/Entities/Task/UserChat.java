/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Task;

import Entities.User.User;

/**
 *
 * @author Admin
 */
public class UserChat {
    private User user;
    private TaskChat chat;

    public UserChat() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskChat getChat() {
        return chat;
    }

    public void setChat(TaskChat chat) {
        this.chat = chat;
    }
    
    
}
