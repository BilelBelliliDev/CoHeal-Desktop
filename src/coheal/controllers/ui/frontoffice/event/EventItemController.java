/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;

import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.controllers.ui.frontoffice.book.BookItemController;
import coheal.controllers.ui.frontoffice.report.RateAlertUIController;
import coheal.controllers.ui.frontoffice.report.RatePopupUIController;
import coheal.controllers.ui.frontoffice.report.ReportPopupUIController;
import coheal.entities.event.Event;
import coheal.services.report.RateService;
import coheal.services.user.UserSession;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class EventItemController implements Initializable {

    double xOffset, yOffset;
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
    int id = 0;
    @FXML
    private AnchorPane menuId;

    private boolean menuIsDisplayed = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Event e) {
        eventTitle.setText(e.getTitle());
        eventLocation.setText(e.getLocation());
        eventDay.setText(e.getStartDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd")));
        eventMonth.setText(e.getStartDate().toLocalDate().format(DateTimeFormatter.ofPattern("MMM")));
        eventType.setText(e.getType());
        eventImg.setImage(e.getImg().getImage());
        id = e.getEventId();
    }

    @FXML
    private void showTaskDetailsAction(ActionEvent event) throws IOException {
        EventHolder th = EventHolder.getINSTANCE();
        th.setId(id);

        AnchorPane pageHolder = (AnchorPane) eventTitle.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        if (pageHolder == null) {
            pageHolder = (AnchorPane) eventTitle.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();

        }
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/event/EventDetails.fxml")));

    }

    @FXML
    private void dotsAction(MouseEvent event) {
        if (menuIsDisplayed) {
            menuIsDisplayed = false;
            new ZoomOut(menuId).play();
            menuId.setDisable(true);
        } else {
            menuId.setVisible(true);
            menuId.setDisable(false);
            menuIsDisplayed = true;
            new ZoomIn(menuId).play();
        }
    }

    @FXML
    private void reportAction(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/report/ReportPopupUI.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(EventItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ReportPopupUIController c = loader.getController();
        c.setData(id, UserSession.getUser_id(), "Event", eventTitle.getText());
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
    private void rateAction(MouseEvent event) throws IOException {
        RateService rs = new RateService();
        if (rs.isRated(id, UserSession.getUser_id(), "Event")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/report/RateAlertUI.fxml"));
            Parent root = loader.load();
            RateAlertUIController c = loader.getController();
            c.setData(id, UserSession.getUser_id(), "Event");
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
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/report/RatePopupUI.fxml"));
            Parent root = loader.load();
            RatePopupUIController c = loader.getController();
            c.setData(id, UserSession.getUser_id(), "Event");
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
}
