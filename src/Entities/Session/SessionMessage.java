/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author acer
 */
public class SessionMessage {
    private int msg_id;
    private int chat_id;
    private int user_id;
    private String msg;
    private Timestamp created_at;
   

    public SessionMessage(int msg_id, int chat_id, int user_id, String msg, Timestamp created_at) {
        this.msg_id = msg_id;
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.msg = msg;
        this.created_at = created_at;
        
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

   
    

   
    public int getMsg_id() {
        return msg_id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SessionMessage{" + "msg_id=" + msg_id + ", chat_id=" + chat_id + ", user_id=" + user_id + ", msg=" + msg + ", created_at=" + created_at + '}';
    }

    
    
    
}
