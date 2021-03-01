/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Task;

import Entities.User.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TaskChat {

    private int chatId;
    private List<UserTask> userTask = new ArrayList<UserTask>();
    private List<TaskMessage> messages = new ArrayList<>();
    private PaidTask paidTask;

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

    public List<UserTask> getUserTask() {
        return userTask;
    }

    public void setUserTask(List<UserTask> users) {
        this.userTask = users;
    }

    public void setMessages(List<TaskMessage> messages) {
        this.messages = messages;
    }

    public PaidTask getPaidTask() {
        return paidTask;
    }

    public void setPaidTask(PaidTask paidTask) {
        this.paidTask = paidTask;
    }
    
   

    @Override
    public String toString() {
        return "TaskChat{" + "chatId=" + chatId + ", userTask=" + userTask  + " message=" + messages+" paidTask="+paidTask + '}';
    }

}
