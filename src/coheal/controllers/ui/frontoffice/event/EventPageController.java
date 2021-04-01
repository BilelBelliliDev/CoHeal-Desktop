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
import coheal.services.event.ServiceUserEvent;
import static coheal.services.recipe.Constants.projectPath;
import coheal.services.task.ServiceNotification;
import coheal.services.ui.UIService;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EventPageController implements Initializable {

    @FXML
    private HBox categoriesHBox;
    private UIService stc = new UIService();

    private ServiceEvent st = new ServiceEvent();

    @FXML
    private ScrollPane eventPane;
    private GridPane eventGrid;
    double xOffset, yOffset;
    @FXML
    private JFXButton addBtn;
    @FXML
    private JFXComboBox<String> comboBox;
    ServiceUserEvent sue = new ServiceUserEvent();
    @FXML
    private Pagination pagination;
    private String searchWord = "", comboValue = "All";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboBox.getItems().add("All");
        comboBox.getItems().add("Yours");
        comboBox.getSelectionModel().select("All");
        if (UserSession.getRole().equals("therapist") || UserSession.getRole().equals("moderator")) {
            addBtn.setVisible(true);
            ServiceNotification service = new ServiceNotification();
            for (int i = 0; i < service.listNotification(UserSession.getUser_id()).size(); i++) {
                Image notification = new Image("file:///" + projectPath + "/src/coheal/resources/images/events/alert.png");
                TrayNotification tray = new TrayNotification();
                tray.setTitle("participer");
                tray.setImage(notification);
                tray.setMessage(service.listNotification(UserSession.getUser_id()).get(i).getMessage());
                tray.setRectangleFill(Paint.valueOf("#2A9A84"));
//                tray.showAndWait();
                tray.showAndDismiss(Duration.seconds(5));
                service.deleteNotification(service.listNotification(UserSession.getUser_id()).get(i));
            }
        }
        new ZoomIn(eventPane).play();
        List<EventCategory> catEvents = stc.topThreeEventCatg();
        for (int i = 0; i < catEvents.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/event/EventCategoryItem.fxml"));
            try {
                AnchorPane pane = loader.load();
                EventCategoryItemController c = loader.getController();
                c.setData(catEvents.get(i));
                categoriesHBox.getChildren().add(pane);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
//        int y = 0;
//        int x = 0;
//        List<Event> events;
//        events = st.AfficherEvent();
//        for (int i = 0; i < events.size(); i++) {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/event/EventItem.fxml"));
//            try {
//                AnchorPane pane = loader.load();
//                EventItemController c = loader.getController();
//                c.setData(events.get(i));
//                if (x > 1) {
//                    y++;
//                    x = 0;
//                }
//                eventGrid.add(pane, x, y);
//                x++;
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }

        //pagination.setPageFactory((pageindex) -> grid(pageindex));
        pagination.setPageFactory((pageindex) -> grid(pageindex, searchWord, comboValue));
    }

//    public GridPane grid(int pageindex) {
//        GridPane pane = null;
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/event/GridEvent.fxml"));
//        try {
//            pane = loader.load();
//            GridEventController c = loader.getController();
//            c.setData(pageindex);
//            pagination.setPageCount(c.pageCount);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return pane;
//    }

    
     public GridPane grid(int pageindex, String searchWord, String comboValue) {
        GridPane pane = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/event/GridEvent.fxml"));
        try {
            pane = loader.load();
            GridEventController c = loader.getController();
            c.setData(pageindex, searchWord, comboValue);
            pagination.setPageCount(c.pageCount);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return pane;
    }
    @FXML
    private void addEventAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/event/AddEventF.fxml"));
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
    private void showAllCategoriesAction(MouseEvent event) throws IOException {
        AnchorPane pageHolder = (AnchorPane) eventPane.getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/event/AllEventCategories.fxml")));

    }

    @FXML
    private void comboBoxAction(ActionEvent event) {
       if ("Yours".equals(comboBox.getValue())) {
            comboValue = "Yours";
            pagination.setPageFactory((pageindex) -> grid(pageindex, searchWord, comboValue));

        } else if ("All".equals(comboBox.getValue())) {
            comboValue = "All";
            pagination.setPageFactory((pageindex) -> grid(pageindex, searchWord, comboValue));

        }

    }
}
