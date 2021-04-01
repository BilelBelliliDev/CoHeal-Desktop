/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.event;


import coheal.entities.event.Event;
import coheal.entities.event.UserEvent;
import coheal.entities.task.UserTask;
import coheal.entities.user.User;
import coheal.iservices.event.IServiceUserEvent;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;

/**
 *
 * @author Admin
 */
public class ServiceUserEvent implements IServiceUserEvent{
    Connection cnx;
    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    ServiceCategory sc = new ServiceCategory();
    
     public ServiceUserEvent() {
        cnx = MyConnection.getInstance().getConnection();
    }

    @Override
    public void addUserEvent(int u, int e) {
    
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "insert into user_event(user_id,event_id) values(" +u+", "+e+")";
            stm.executeUpdate(query);
            System.out.println("user event ajouter ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Event> getEventsById(int u) {
        ImageView img = null;
        List<Event> l=new ArrayList();
         String query="select * from event e join user_event ue on e.event_id=ue.event_id where start_date>=sysdate() and is_deleted=0 and  ue.user_id="+u;
        try {
            Statement st = cnx.createStatement();
           ResultSet rst = st.executeQuery(query);
            while (rst.next()) {
              Event e=new Event();
              e.setEventId(rst.getInt("event_id"));
                e.setTitle(rst.getString("title"));
                e.setMaxUsers(rst.getInt("max_users"));
                e.setMinUsers(rst.getInt("min_users"));
                e.setDescription(rst.getString("description"));
                e.setStartDate(rst.getDate("start_date"));
                e.setEndDate(rst.getDate("end_date"));
                e.setLocation(rst.getString("location"));
                e.setType(rst.getString("type"));
                e.setPrice(rst.getDouble("price"));
                e.setCat(sc.getEventById(rst.getInt("cat_id")));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/events/" + rst.getString("img_url");
                img = new ImageView(url);
                e.setImg(img);
                e.setImgUrl(rst.getString("img_url"));
                l.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return l;
    
        
    }

    @Override
    public UserEvent getUserEvent(int idU, int idE) {
    
    ServiceEvent serviceE = new ServiceEvent();
        UserEvent ut = new UserEvent();
        try {

            String query = "select * from user_event where user_id=" + idU + " and event_id=" + idE;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                UserEvent u = new UserEvent();
                u.setEvent(serviceE.getEvent(rs.getInt("event_id")));
                u.setUser(getUserById(rs.getInt("user_id")));
                ut = u;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ut;
    }

    public User getUserById(int idU){
        User u = null;
        String query="select * from user where user_id="+idU;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User user=new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                u=user;
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return u;
    }

        public List<UserEvent> getNbrParticipateByDate(){
        List<UserEvent> listUserEvent=new ArrayList();
        String query="SELECT count(user_id) as nb , Date(created_at) as date FROM `user_event` GROUP BY Date(created_at)";
        try {                            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                UserEvent ut=new UserEvent();
                ut.setNbr(rs.getInt("nb"));
                ut.setCreatedAt(rs.getDate("date"));
                listUserEvent.add(ut);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUserEvent;
    }

    @Override
    public void participer(int idUser, int idEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
