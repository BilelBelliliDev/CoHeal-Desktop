/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;

import coheal.entities.event.Event;
import coheal.services.event.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class GridEventController implements Initializable {

    @FXML
    private GridPane eventGrid;
    public int pageCount,currentPage;
    private ServiceEvent st = new ServiceEvent();
    private final int NUM=8;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void setData(int index){
        currentPage=index;
        try {
            int y = 0;
            int x = 0;
            List<Event> events;
            events = st.AfficherEvent();
            //pagination
            pageCount=events.size()/NUM;
            if(events.size()%NUM>0){
                pageCount++;
            }
            int a,b;
            a=currentPage*NUM; 
            if(currentPage==(pageCount-1))
                b=events.size();
            else
                b=a+NUM;
            for (int i = a; i < b; i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/event/EventItem.fxml"));
                AnchorPane pane = loader.load();
                EventItemController c = loader.getController();
                c.setData(events.get(i));
                if (x > 1) {
                    y++;
                    x = 0;
                }
                eventGrid.add(pane, x, y);
                x++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GridEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GridEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
