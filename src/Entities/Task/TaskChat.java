/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Task;

import java.util.List;

/**
 *
 * @author Admin
 */
public class TaskChat {
    private int chatId;
    //private List<User> users;
    private List<TaskMessage> messages;

    public TaskChat(int chatId/*, List<User> users*/,List<TaskMessage> messages) {
        this.chatId = chatId;
       // this.users = users;
       this.messages=messages;
    }

    public TaskChat() {
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    
    public List<TaskMessage> getMessages() {
        return messages;
    }

    /*  public List<User> getUsers() {
    return users;
    }
    public void setUsers(List<User> users) {
    this.users = users;
    }
     */
    public void setMessages(List<TaskMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "TaskChat{" + "chatId=" + chatId/* + ", users=" + users */+" message="+messages+ '}';
    }
    
}
