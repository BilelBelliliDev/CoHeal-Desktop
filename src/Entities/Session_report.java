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
public class Session_report {
    private int report_id;
    private int session_id;

    public Session_report(int report_id, int session_id) {
        this.report_id = report_id;
        this.session_id = session_id;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    @Override
    public String toString() {
        return "Session_report{" + "report_id=" + report_id + ", session_id=" + session_id + '}';
    }
    
    
}
