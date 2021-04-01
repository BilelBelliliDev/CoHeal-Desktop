/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.event;


import coheal.entities.event.Event;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IServiceEvent  {
    
    public void AddEvent(Event e);
    public List<Event>AfficherEvent()throws SQLException;
    public void updateEvent(int id, Event e);
    public void deleteEvent(int id);
    public Event getEvent(int id);
    public List<Event> ListEventByIdUser(int idU);
   
}
