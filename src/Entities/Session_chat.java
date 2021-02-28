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
public class Session_chat {
    private int chat_id;
    private int session_id;

    public Session_chat(int chat_id, int session_id) {
        this.chat_id = chat_id;
        this.session_id = session_id;
    }

    public Session_chat() {
    }

    public int getChat_id() {
        return chat_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }
            
    
}
