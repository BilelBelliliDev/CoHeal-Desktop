/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.session;

import coheal.entities.session.Session;
import coheal.iservices.session.ISessionService;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author acer
 */
public class ServiceSession implements ISessionService {

    Connection con;

    public ServiceSession() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void createSession(Session s) {
        try {
            Statement st = con.createStatement();

            String query;
            query = "INSERT into session(therp_id,title,description,num_of_days)"
                    + " VALUES( '" + s.getTherpId() + "','" + s.getTitle() + "','" + s.getDescription() + "','" + s.getNumOfDays() + "');";
            st.executeUpdate(query);
            System.out.println("Session ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout " + ex.getMessage());;
        }
    }

    @Override
    public void modifierSession(Session s, int id) {
        try {

            String query = "UPDATE  session set title='" + s.getTitle() + "',description ='" + s.getDescription() + "' ,num_of_days= " + s.getNumOfDays() + " where session_id =" + id + ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("modification avec succes");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public void SupprimerSession(int id) {
        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            String query = "UPDATE  session set deleted_at= CURRENT_TIMESTAMP ,is_deleted=1 where session_id=" + id + ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Session> listSesion() {
        ObservableList<Session> s =FXCollections.observableArrayList();
        try {
            Statement st = con.createStatement();
            String res = "select * from `session` where is_deleted=0 order by created_at desc";
            ResultSet rs = st.executeQuery(res);

            while (rs.next()) {
                Session e = new Session() ;
                e.setSessionId(rs.getInt("session_id"));
                e.setTherpId(rs.getInt("therp_id"));
                e.setTitle(rs.getString("title"));
                e.setDescription(rs.getString("description"));         
                e.setNumOfDays(rs.getInt("num_of_days"));
       
                s.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return s;
    }
    public ObservableList<Session> searchSessionAvoncer(String idTA) {
        ObservableList<Session> ta = FXCollections.observableArrayList();;
        String query = "SELECT * FROM session WHERE is_deleted = 0 AND title like '" + idTA + "%'   ";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Session session = new Session();
                session.setSessionId(rs.getInt("session_id"));
                session.setTitle(rs.getString("title"));
                session.setDescription(rs.getString("description"));
                ta.add(session);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ta;
    }
public Session searchSession(int idTA) {
        Session ta = null;
        String query = "SELECT session_id, therp_id, title, description FROM session where session_id=" + idTA;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Session session = new Session();
                session.setSessionId(rs.getInt("session_id"));
                session.setTitle(rs.getString("title"));
                session.setDescription(rs.getString("description"));
                ta = session;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ta;
    }
  public void modifierV(int id) {
        try {
            Statement stm = con.createStatement();
            String query;
            query = "update session set views=views+1 where session_id = " + id + "";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
