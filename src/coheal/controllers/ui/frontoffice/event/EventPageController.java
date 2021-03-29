/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;

import coheal.controllers.ui.frontoffice.book.*;
import coheal.controllers.ui.frontoffice.task.*;
import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.HomePageHolderController;

import coheal.entities.event.Event;
import coheal.entities.event.EventCategory;
import coheal.services.event.ServiceEvent;
import coheal.services.ui.UIService;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EventPageController implements Initializable {

    private ScrollPane taskPane;
    @FXML
    private HBox categoriesHBox;
    private UIService stc = new UIService();
    
    private ServiceEvent st=new ServiceEvent();
    private GridPane taskGrid;
    private GridPane bookGrid;
    @FXML
    private ScrollPane eventPane;
    @FXML
    private GridPane eventGrid;
     double xOffset, yOffset;
    @FXML
    private JFXButton addBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if(UserSession.getRole().equals("therapist") || UserSession.getRole().equals("moderator"))
            addBtn.setVisible(true);
        new ZoomIn(eventPane).play();
        List<EventCategory> catBooks = stc.topThreeEventCatg();
        for (int i = 0; i < catBooks.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/event/EventCategoryItem.fxml"));
            try {
                AnchorPane pane = loader.load();
                EventCategoryItemController c = loader.getController();

                c.setData(catBooks.get(i));
                categoriesHBox.getChildren().add(pane);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        int y = 0;
        int x = 0;
        List<Event> events;
        try {
            events = st.AfficherEvent();
            for (int i = 0; i < events.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/event/EventItem.fxml"));
            try {
                AnchorPane pane = loader.load();
                EventItemController c = loader.getController();
                c.setData(events.get(i));
                if (x > 1) {
                    y++;
                    x = 0;
                }
                eventGrid.add(pane, x, y);
                x++;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }

    @FXML
    private void addEventAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/event/AddEvent.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        HomePageHolderController hpc = new HomePageHolderController();
        hpc.setStage(stage);
        stage.show();
        root.setOnMousePressed((MouseEvent mouseEvent) -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent mouseEvent) -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
            stage.setOpacity(0.85f);
        });
        root.setOnMouseReleased((MouseEvent mouseEvent) -> {
            stage.setOpacity(1.0f);
        });
    }
}