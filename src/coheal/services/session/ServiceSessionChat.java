/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.session;

import coheal.entities.session.Session;
import coheal.entities.session.SessionChat;
import coheal.iservices.session.IServiceSessionChat;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author acer
 */
public class ServiceSessionChat implements IServiceSessionChat {
     Connection con;

    public ServiceSessionChat() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void createSessionChat( SessionChat s) {
         try {
             Statement st = con.createStatement();
              String query;
             query="INSERT INTO `session_chat`( `session_id`)" +"VALUES ('"+s.getSessionId()+ "');";
         } catch (SQLException ex) {
             Logger.getLogger(ServiceSessionChat.class.getName()).log(Level.SEVERE, null, ex);
         }

    }

    @Override
    public void joindreSession(SessionChat s) {
        
    }

     @Override
    public List listechat() {
        ObservableList<SessionChat> s =FXCollections.observableArrayList();
        try {
            Statement st = con.createStatement();
            String res = "select * from `session_chat` ";
            ResultSet rs = st.executeQuery(res);

            while (rs.next()) {
                SessionChat e = new SessionChat() ;
                e.setChatId(rs.getInt("chat_id"));
                e.setChatId(rs.getInt("session_id"));
                
       
                s.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return s;
    }

   

    
    
    
}
