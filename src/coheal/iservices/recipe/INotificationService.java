/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.recipe;

import coheal.entities.recipe.Notification;
import coheal.utils.MyConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public interface INotificationService {
  
    public void addNotification(Notification n);

    public void deleteNotification(Notification n);
    
    public List<Notification> listNotification(int id);
}
