/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.entities.Task;

import coheal.entities.User.User;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TaskMessage {
    
    private int msgId;
    private TaskChat taskChat;
    private List<User> users= new ArrayList<>();
    private String msg;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

   
    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public TaskChat getTaskChat() {
        return taskChat;
    }

    public void setTaskChat(TaskChat taskChat) {
        this.taskChat = taskChat;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
        return "TaskMessage{" + "msgId=" + msgId + ", taskChat=" + taskChat /*+ ", users=" + users */+ ", msg=" + msg + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + '}';
    }
    
}
