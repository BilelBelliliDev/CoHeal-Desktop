/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.session;

import coheal.entities.session.Session;
import coheal.entities.session.SessionMessage;
import coheal.iservices.session.IServiceMsgInterface;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author acer
 */
public class ServiceSessionMessage implements IServiceMsgInterface {
            Connection con;

    
    public ServiceSessionMessage() {
       con=MyConnection.getInstance().getConnection();
    }


    @Override
    public void createSessionMesage( SessionMessage s) {
         try{
              String query;
               query = "INSERT into session_message(chat_id,msg,user_id)"+" VALUES("+s.getChatId()+ ",'"+s.getMsg()+"',"+s.getUserId()+ ");";
        Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Message ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout "+ex);
        }
    }

   // @Override
    public ObservableList<SessionMessage> listMessage(int id) {
         ObservableList<SessionMessage> s =FXCollections.observableArrayList();
         try{
              Statement st = con.createStatement();
            String res = "select msg from `session_message` where chat_id="+id;
            ResultSet rs = st.executeQuery(res);

            while (rs.next()) {
                SessionMessage sm=new SessionMessage();
                sm.setMsg(rs.getString("msg"));
                s.add(sm);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return s;
         }

    @Override
    public List<SessionMessage> listMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
