/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.recipe;

import coheal.entities.recipe.Notification;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class NotificationService {

    Connection con;

    public NotificationService() {
        con = MyConnection.getInstance().getConnection();
    }

    public void addNotification(Notification n) {
        try {
            String query = "insert into notification (user_id,message) values(" + n.getId() + ",'" + n.getMessage() + "');";
            System.out.println(query);
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Notification ajoutée ");
        } catch (SQLException ex) {
            System.out.println("Error! " + ex.getMessage());
        }
    }

    public void deleteNotification(Notification n) {
        try {
            String query = "DELETE FROM notification WHERE user_id=" + n.getId() + " and message='"+n.getMessage()+"';";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Deleted Successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Notification> listNotification(int id){
        List<Notification> l=new ArrayList<>();
        String query = "select * FROM notification WHERE user_id=" +id + ";";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Notification n=new Notification();
                n.setId(rs.getInt("user_id"));
                n.setMessage(rs.getString("message"));
                l.add(n);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
}
