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
import coheal.iservices.event.IServiceEvent;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class ServiceEvent implements IServiceEvent{
      
    Connection cnx ;

    public ServiceEvent() {
        cnx= MyConnection.getInstance().getConnection();
    }
    
    @Override
    public void AddEvent(Event e) {
        try {
            Statement stm=cnx.createStatement();
            String query;
            query ="insert into event(user_id,title,description,start_date,end_date,location) values("+e.getUserId()+",'"+e.getTitle()+"','"+e.getDescription()+"','"+e.getStartDate()+"','"+e.getEndDate()+"','"+e.getLocation()+"')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ObservableList<Event> AfficherEvent() throws SQLException{
      
            Statement stm=cnx.createStatement();
            String query="select * from `event` order by created_at desc";
            
            ResultSet  rst=stm.executeQuery(query);
            
            ObservableList<Event>Events= FXCollections.observableArrayList();
            
            while(rst.next())
            {
                Event e = new Event() ;
                e.setEventId(rst.getInt("event_id"));
                e.setTitle(rst.getString("title"));
                e.setDescription(rst.getString("description"));
                
                //e.setStartTime(rst.getTime("start_time"));
                //e.setEndTime(rst.getTime("end_time"));
                e.setStartDate(rst.getDate("start_date"));
                e.setEndDate(rst.getDate("end_date"));
                e.setLocation(rst.getString("location"));
                e.setType(rst.getString("type"));
                Events.add(e);
            }
      
        return Events;
        
    }

    @Override
    public void updateEvent(int id, Event e) {
       try {
            Statement stm=cnx.createStatement();
            String query;
            query ="update event set title = '"+e.getTitle()+"', description = '"+e.getDescription()+"', start_date = '"+e.getStartDate()+"', end_date = '"+e.getEndDate()+"', location = '"+e.getLocation()+"', type = '"+e.getType()+"' where event_id = "+id+"";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteEvent(int id) {
        try {
            Statement stm=cnx.createStatement();
            String query;
            query ="delete from event where event_id = "+id+"";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
