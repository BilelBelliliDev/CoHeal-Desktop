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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class ServiceSessionChat implements IServiceSessionChat {
     Connection con;

    public ServiceSessionChat() {
        con = MyConnection.getInstance().getConnection();
    }

   // @Override
    public void createSessionChat( int s) {
         try {
             Statement st = con.createStatement();
              String query;
             query="INSERT INTO `session_chat`( `session_id`)" +"VALUES ('"+s+ "');";
         } catch (SQLException ex) {
             Logger.getLogger(ServiceSessionChat.class.getName()).log(Level.SEVERE, null, ex);
         }

    }

    @Override
    public void createSessionChat(SessionChat s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
}
