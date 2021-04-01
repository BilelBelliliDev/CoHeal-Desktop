/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.event;

import coheal.entities.event.EventCategory;
import coheal.entities.recipe.RecipeCategory;
import coheal.iservices.event.IServiceCategory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import coheal.utils.MyConnection;
import javafx.scene.image.ImageView;

/**
 *
 * @author HP
 */
public class ServiceCategory implements IServiceCategory {

    Connection cnx;
    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");

    public ServiceCategory() {
        cnx = MyConnection.getInstance().getConnection();
    }

    /**
     *
     * @return @throws SQLException
     */
    @Override
    public List<EventCategory> AfficherCategoryEvent() {
        ImageView img = null;
        ObservableList<EventCategory> Events = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "select * from event_category where is_deleted=0";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                EventCategory c = new EventCategory();
                c.setCatId(rst.getInt("cat_id"));
                c.setName(rst.getString("name"));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/events/" + rst.getString("img_url");
                img = new ImageView(url);
                c.setImg(img);

                Events.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Events;
    }

    @Override
    public void deleteCategoryEvent(int id) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "delete from event_category where cat_id = " + id + "";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void AddEventCategory(EventCategory c) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "insert into event_category(name,img_URL) values('" + c.getName() + "','" + c.getImgUrl() + "')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateCategoryEvent(int id, EventCategory c) {
        try {
            Statement stm = cnx.createStatement();
            String query;
            query = "update event_category set name = '" + c.getName() + "', img_URL = '" + c.getImgUrl() + "' where cat_id=" + id + "";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public EventCategory getEventByName(String name) {
        EventCategory eventCatg = null;
        String query = "select * from event_category where name='" + name + "';";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                EventCategory c = new EventCategory();
                c.setCatId(rst.getInt("cat_id"));
                c.setName(rst.getString("name"));
                c.setImgUrl(rst.getString("img_Url"));
                eventCatg = c;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return eventCatg;
    }

    public EventCategory getEventById(int id) {
        EventCategory eventCatg = null;
        String query = "select * from event_category where cat_id=" + id + ";";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                EventCategory c = new EventCategory();
                c.setCatId(rst.getInt("cat_id"));
                c.setName(rst.getString("name"));
                c.setImgUrl(rst.getString("img_Url"));
                eventCatg = c;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return eventCatg;
    }

    @Override
    public List<EventCategory> searchCategory(String name) {
    
        ImageView img = null;
        ObservableList<EventCategory> Events = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "select * from event_category where is_deleted=0 and name like '"+name+"%' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                EventCategory c = new EventCategory();
                c.setCatId(rst.getInt("cat_id"));
                c.setName(rst.getString("name"));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/events/" + rst.getString("img_url");
                img = new ImageView(url);
                c.setImg(img);

                Events.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Events;
    }
    

    
    

    }

 



  

