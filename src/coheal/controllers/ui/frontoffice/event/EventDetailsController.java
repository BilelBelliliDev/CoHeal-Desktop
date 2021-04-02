/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;

import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.event.Event;
import coheal.entities.event.UserEvent;
import coheal.entities.task.Notification;
import coheal.services.event.ServiceEvent;
import coheal.services.event.ServiceUserEvent;
import coheal.services.report.RateService;
import coheal.services.task.ServiceNotification;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EventDetailsController implements Initializable {

    
    @FXML
    private Label eventDescription;
    @FXML
    private Label startDate;
    @FXML
    private Label location;
    @FXML
    private ImageView eventImg;
    @FXML
    private Label eventTitle;
    @FXML
    private FontAwesomeIconView updateIcon;
    @FXML
    private FontAwesomeIconView deleteIcon;
    @FXML
    private Label endDate;
    @FXML
    private JFXButton participateButton;
    @FXML
    private Label price;
    double xOffset, yOffset;
    @FXML
    private ScrollPane eventPane;
    private Event event1;
    ServiceEvent se=new ServiceEvent();
    EventHolder eh = EventHolder.getINSTANCE();
    ServiceUserEvent sut = new ServiceUserEvent();
    RateService rs = new RateService();
    @FXML
    private Rating ratingId;
    @FXML
    private Label rateLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(eventPane).play();
        if(rs.ratesListById("event", eh.getId())==0){
            rateLabel.setVisible(true);
            ratingId.setVisible(false);
        }else {        
            System.out.println(rs.ratesListById("event", eh.getId()));
            ratingId.setRating(rs.ratesListById("event", eh.getId()));
            rateLabel.setVisible(false);
            ratingId.setVisible(true);
        }
        event1=se.getEvent(eh.getId());
        System.out.println(event1.getUser().getUserId());
        UserEvent u = sut.getUserEvent(UserSession.getUser_id(), event1.getEventId());
        if (u.getEvent() == null && u.getUser() == null) {
            participateButton.setVisible(true);
        }
        
        if (UserSession.getRole().equals("therapist") ) {
            updateIcon.setVisible(true);
            deleteIcon.setVisible(true);
        }
        eventDescription.setText(event1.getDescription());
        if(event1.getPrice()!=0){
            price.setText(String.valueOf(event1.getPrice()));
        }
        eventImg.setImage(event1.getImg().getImage());
        eventTitle.setText(event1.getTitle());
        location.setText(event1.getLocation());
        String start=event1.getStartDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd")) + " "
                    + event1.getStartDate().toLocalDate().format(DateTimeFormatter.ofPattern("MMM")) + " 20"
                    + event1.getStartDate().toLocalDate().format(DateTimeFormatter.ofPattern("YY"));
        String end=event1.getEndDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd")) + " "
                    + event1.getEndDate().toLocalDate().format(DateTimeFormatter.ofPattern("MMM")) + " 20"
                    + event1.getEndDate().toLocalDate().format(DateTimeFormatter.ofPattern("YY"));
        startDate.setText(start);
        endDate.setText(end);
        if(event1.getPrice()!=0){
            price.setText(String.valueOf(event1.getPrice()));
        }
    }    

    @FXML
    private void updateEvent(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/event/UpdateEventF.fxml"));
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

    @FXML
    private void deleteEvent(MouseEvent event) {
        se.deleteEvent(eh.getId());
    }

    @FXML
    private void participateAction(ActionEvent event) {
        sut.addUserEvent(UserSession.getUser_id(), eh.getId());
        ServiceNotification service=new ServiceNotification();
        Notification n = new Notification();
        n.setId(event1.getUser().getUserId());
        n.setMessage(UserSession.getFirst_name()+" "+UserSession.getLast_name()+" a participer a votre tache "+event1.getTitle());
        service.addNotification(n);
    }

    @FXML
    private void backAction(MouseEvent event) throws IOException {
        AnchorPane pageHolder = (AnchorPane) eventPane.getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/event/EventPage.fxml")));

    }

    
}
