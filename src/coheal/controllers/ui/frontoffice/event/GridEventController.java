/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;

import coheal.entities.event.Event;
import coheal.services.event.ServiceEvent;
import coheal.services.event.ServiceUserEvent;
import coheal.services.user.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class GridEventController implements Initializable {

    @FXML
    private GridPane eventGrid;
    public int pageCount, currentPage;
    private ServiceEvent se = new ServiceEvent();
    private int gridSize, columnCount;
    ServiceUserEvent sue = new ServiceUserEvent();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setData(int index, String searchWord, String comboValue) {
//        gridSize=eventGrid.getRowConstraints().size()*eventGrid.getColumnConstraints().size();
//        columnCount=eventGrid.getColumnConstraints().size()-1;
//        currentPage=index;
//        try {
//            int y = 0;
//            int x = 0;
//            List<Event> events;
//            events = st.AfficherEvent();
//            //pagination
//            pageCount=events.size()/gridSize;
//            if(events.size()%gridSize>0){
//                pageCount++;
//            }
//            int a,b;
//            a=currentPage*gridSize; 
//            if(currentPage==(pageCount-1))
//                b=events.size();
//            else
//                b=a+gridSize;
//            for (int i = a; i < b; i++) {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/event/EventItem.fxml"));
//                AnchorPane pane = loader.load();
//                EventItemController c = loader.getController();
//                c.setData(events.get(i));
//                if (x > columnCount) {
//                    y++;
//                    x = 0;
//                }
//                eventGrid.add(pane, x, y);
//                x++;
//            }
//        } catch (IOException ex) {
//            System.out.println( ex.getMessage());
//        }

        gridSize = eventGrid.getRowConstraints().size() * eventGrid.getColumnConstraints().size();
        columnCount = eventGrid.getColumnConstraints().size() - 1;
        currentPage = index;
        try {
            int y = 0;
            int x = 0;
            List<Event> events = null;

            if ("Yours".equals(comboValue)) {
               if (UserSession.getRole().equals("therapist") || UserSession.getRole().equals("moderator")) {
                    eventGrid.getChildren().clear();
                    events = se.ListEventByIdUser(UserSession.getUser_id());
                }else {
                events = sue.getEventsById(UserSession.getUser_id());
            }

            } else if ("All".equals(comboValue)) {
                eventGrid.getChildren().clear();
                events = se.AfficherEvent();

            }
            //pagination
            if (events != null) {
                pageCount = events.size() / gridSize;
                if (events.size() % gridSize > 0) {
                    pageCount++;
                }
                int a, b;
                a = currentPage * gridSize;
                if (currentPage == (pageCount - 1)) {
                    b = events.size();
                } else {
                    b = a + gridSize;
                }
                for (int i = a; i < b; i++) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/event/EventItem.fxml"));
                    AnchorPane pane = loader.load();
                    EventItemController c = loader.getController();
                    c.setData(events.get(i));
                    if (x > columnCount) {
                        y++;
                        x = 0;
                    }
                    eventGrid.add(pane, x, y);
                    x++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
