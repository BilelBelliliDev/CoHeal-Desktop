/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.entities.session;

import java.sql.Timestamp;

/**
 *
 * @author acer
 */
public class SessionMessage {
    private int msgId;
    private int chatId;
    private int userId;
    private String msg;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

     public SessionMessage() {
    }

    
    public SessionMessage(String msg) {
        this.msg = msg;
    }

    
    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return "SessionMessage{" + "msg=" + msg + '}'+"\n";
    }

   



 
    
}
