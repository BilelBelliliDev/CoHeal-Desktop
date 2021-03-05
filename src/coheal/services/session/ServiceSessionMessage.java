/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.session;

import coheal.entities.session.SessionMessage;
import coheal.iservices.session.IServiceMsgInterface;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
               query = "INSERT into session_message(chat_id,msg)"+" VALUES('"+s.getChatId()+ "','"+s.getMsg()+"');";
        Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Message ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout "+ex);
        }
    }

    @Override
    public List<SessionMessage> listMessage() {
         ArrayList<SessionMessage> s=new ArrayList();
         try{
              Statement st = con.createStatement();
            String res = "select * from `session_message`";
            ResultSet rs = st.executeQuery(res);

            while (rs.next()) {
                s.add(new SessionMessage(rs.getString("msg")));
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return s;
         }
    
}
