/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.event;


import coheal.entities.event.EventCategory;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IServiceCategory {
        public void AddEventCategory(EventCategory c);
    public List<EventCategory> AfficherCategoryEvent();
    public void updateCategoryEvent(int id, EventCategory c);
    public void deleteCategoryEvent(int id);
    public EventCategory getEventByName(String name);
    public List<EventCategory> searchCategory(String name);
}
