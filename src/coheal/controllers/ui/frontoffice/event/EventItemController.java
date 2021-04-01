/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;

import coheal.entities.event.Event;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class EventItemController implements Initializable {

    @FXML
    private Label eventDay;
    @FXML
    private Label eventMonth;
    @FXML
    private ImageView eventImg;
    @FXML
    private Label eventTitle;
    @FXML
    private Label eventLocation;
    @FXML
    private Label eventType;
    int id=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Event e){
        eventTitle.setText(e.getTitle());
        eventLocation.setText(e.getLocation());
        eventDay.setText(e.getStartDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd")));
        eventMonth.setText(e.getStartDate().toLocalDate().format(DateTimeFormatter.ofPattern("MMM")));
        eventType.setText(e.getType());
        eventImg.setImage(e.getImg().getImage());
        id=e.getEventId();
    }

    @FXML
    private void showTaskDetailsAction(ActionEvent event) throws IOException {
        EventHolder th = EventHolder.getINSTANCE();
        th.setId(id);

        AnchorPane pageHolder = (AnchorPane) eventTitle.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
         if(pageHolder==null){
             pageHolder = (AnchorPane) eventTitle.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
             
        }
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/event/EventDetails.fxml")));
    
    }
}
