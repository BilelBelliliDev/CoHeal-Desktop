/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;

import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.task.TaskCategoryItemController;
import coheal.entities.event.EventCategory;
import coheal.entities.task.TaskCategory;
import coheal.services.event.ServiceCategory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AllEventCategoriesController implements Initializable {

    @FXML
    private ScrollPane CategoryPane;
    @FXML
    private GridPane CategoryGrid;
    ServiceCategory sc=new ServiceCategory();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         new ZoomIn(CategoryPane).play();
        int y = 0;
        int x = 0;
        List<EventCategory> l=sc.AfficherCategoryEvent();
        for(int i=0;i<l.size();i++){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/event/EventCategoryItem.fxml"));
             try {
                AnchorPane pane = loader.load();
                EventCategoryItemController c = loader.getController();

                c.setData(l.get(i));
                if (x > 2) {
                    y++;
                    x = 0;
                }
                CategoryGrid.add(pane, x, y);
                x++;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }    

    @FXML
    private void backAction(MouseEvent event) throws IOException {
        AnchorPane pageHolder = (AnchorPane) CategoryPane.getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/event/EventPage.fxml")));
    
    }
    
}
