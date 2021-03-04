/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.event;


import coheal.entities.event.EventCategory;
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


/**
 *
 * @author HP
 */
public class ServiceCategory implements IServiceCategory{
    
        Connection cnx ;

    public ServiceCategory() {
        cnx= MyConnection.getInstance().getConnection();
    }
    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<EventCategory> AfficherCategoryEvent()  {
         ObservableList<EventCategory>Events= FXCollections.observableArrayList();
            try {
                Statement stm=cnx.createStatement();
                String query="select * from `event_category` ";
                
                ResultSet  rst=stm.executeQuery(query);
                
               
                
                while(rst.next())
                {
                    EventCategory c = new EventCategory() ;
                    c.setCatId(rst.getInt("cat_id"));
                    c.setName(rst.getString("name"));
                    c.setImgUrl(rst.getString("img_Url"));
                    
                    
                    Events.add(c);
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ServiceCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return Events;
    }

 

    @Override
    public void deleteCategoryEvent(int id) {
        try{
            Statement stm=cnx.createStatement();
            String query;
            query ="delete from event_category where cat_id = "+id+"";
            stm.executeUpdate(query);
            
        }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  


    public void AddEventCategory(EventCategory c) {
               try {
            Statement stm=cnx.createStatement();
            String query;
            query ="insert into event_category(name,img_URL) values('"+c.getName()+"','"+c.getImgUrl()+"')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

        @Override
    public void updateCategoryEvent(int id,EventCategory c) {
                    try {
            Statement stm=cnx.createStatement();
            String query;
            query ="update event_category set name = '"+c.getName()+"', img_URL = '"+c.getImgUrl()+"' where cat_id="+id+"";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
}
