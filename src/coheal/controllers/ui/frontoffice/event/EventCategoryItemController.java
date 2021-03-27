/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;


import coheal.controllers.ui.frontoffice.book.*;
import coheal.controllers.ui.frontoffice.task.*;
import coheal.entities.book.BookCategory;
import coheal.entities.event.EventCategory;
import coheal.entities.task.TaskCategory;
import coheal.services.task.ServiceTaskCategory;
import coheal.services.ui.UIService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class EventCategoryItemController implements Initializable {

    @FXML
    private ImageView eventCatgImg;
    @FXML
    private Label eventCatgTitle;
    @FXML
    private Label eventCatgTotalEvents;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(EventCategory tc){
        UIService stc=new UIService();
        eventCatgTitle.setText(tc.getName());
        eventCatgTotalEvents.setText(String.valueOf(stc.ListerEventsByIdCatg(tc.getName()).size())+" Events");
    }

}
