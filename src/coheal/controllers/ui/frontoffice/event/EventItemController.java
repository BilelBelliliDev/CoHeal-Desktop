/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;

import coheal.entities.event.Event;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
    }
}
