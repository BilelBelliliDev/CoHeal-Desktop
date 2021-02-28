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
public class Session_rate {
    
    private int rate_id;
    private int session_id;

    public Session_rate(int rate_id, int session_id) {
        this.rate_id = rate_id;
        this.session_id = session_id;
    }

    public int getRate_id() {
        return rate_id;
    }

    public void setRate_id(int rate_id) {
        this.rate_id = rate_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    @Override
    public String toString() {
        return "Session_rate{" + "rate_id=" + rate_id + ", session_id=" + session_id + '}';
    }
    
}
