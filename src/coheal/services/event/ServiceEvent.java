/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.event;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import coheal.entities.event.Event;
import coheal.entities.event.EventCategory;
import coheal.entities.user.User;
import coheal.iservices.event.IServiceEvent;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

/**
 *
 * @author HP
 */
public class ServiceEvent implements IServiceEvent {

    Connection cnx;
    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    ServiceCategory sc = new ServiceCategory();

    public ServiceEvent() {
        cnx = MyConnection.getInstance().getConnection();
    }

    @Override
    public void AddEvent(Event e) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "insert into event(user_id,cat_id,title,description,start_date,end_date,location,type,price,img_url) values(" + e.getUserId() + "," + e.getCat().getCatId() + ",'" + e.getTitle() + "','" + e.getDescription() + "','" + e.getStartDate() + "','" + e.getEndDate() + "','" + e.getLocation() + "','" + e.getType() + "'," + e.getPrice() + ",'" + e.getImgUrl() + "')";
            stm.executeUpdate(query);
            System.out.println("event ajouter ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ObservableList<Event> AfficherEvent() {
        ImageView img = null;
        ObservableList<Event> Events = FXCollections.observableArrayList();
        String query = "select * from `event` where is_deleted=0 and start_date> sysdate() order by created_at desc";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                Event e = new Event();
                e.setEventId(rst.getInt("event_id"));
                e.setTitle(rst.getString("title"));
                e.setCatId(rst.getInt("cat_id"));
                e.setCat(catName(rst.getInt("cat_id")));
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
                Events.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Events;

    }

    public EventCategory catName(int catId) {
        String query = "select name from `event_category` where is_deleted=0 and cat_id=" + catId + "";
        EventCategory e = new EventCategory();
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {

                e.setName(rst.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }

    @Override
    public void updateEvent(int id, Event e) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "update event set title = '" + e.getTitle() + "', description = '" + e.getDescription() + "', start_date = '" + e.getStartDate() + "', end_date = '" + e.getEndDate() + "', location = '" + e.getLocation() + "', type = '" + e.getType() + "', price=" + e.getPrice() + ", img_url='" + e.getImgUrl() + "', cat_id=" + e.getCat().getCatId() + " where event_id = " + id + "";
            stm.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteEvent(int id) {
        Calendar calendar = Calendar.getInstance();
        Timestamp d = new Timestamp(calendar.getTime().getTime());
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "update event set is_deleted=" + 1 + ",deleted_at='" + d + "'where event_id = " + id + "";
            stm.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User getUserById(int idU) {
        User u = null;
        String query = "select * from user where user_id=" + idU;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                u = user;
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return u;
    }

    public Event getEvent(int id) {
        Event event = null;
        ImageView img = null;
        try {

            Statement stm = cnx.createStatement();
            String query = "select * from `event` where event_id=" + id;

            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Event e = new Event();
                User u = getUserById(rst.getInt("user_id"));
                e.setUser(u);
                System.out.println(u);
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
                event = e;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return event;
    }

    @Override
    public List<Event> ListEventByIdUser(int idU) {

        List<Event> t = new ArrayList();
        ImageView img = null;
        try {

            Statement st = cnx.createStatement();
            String query = "select * from event  where is_deleted=0  and user_id=" + idU + " ORDER by start_date desc";
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {
                Event e = new Event();
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
                t.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return t;
    }

}
