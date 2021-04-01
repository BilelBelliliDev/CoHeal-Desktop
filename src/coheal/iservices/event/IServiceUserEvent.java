/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.event;

import coheal.entities.event.Event;
import coheal.entities.event.UserEvent;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IServiceUserEvent {
    public  void addUserEvent(int u,int e);
    public List<Event> getEventsById(int u); 
    public UserEvent getUserEvent(int idU, int idE); 
     public void participer(int idUser, int idEvent);
}
