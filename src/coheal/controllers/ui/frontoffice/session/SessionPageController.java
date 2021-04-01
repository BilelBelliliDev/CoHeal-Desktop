/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.session;

import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.session.Session;
import coheal.services.session.ServiceSession;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SessionPageController implements Initializable {

    private ServiceSession st = new ServiceSession();
    @FXML
    private ScrollPane sessionPane;
    @FXML
    private GridPane sessionGrid;
    double xOffset, yOffset;
    @FXML
    private JFXButton addBtn;
    @FXML
    private JFXTextField Search;
    @FXML
    private JFXComboBox<String> comboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox.getItems().add("All");
        comboBox.getItems().add("Yours");
        comboBox.getItems().add("On Going Sessions");
        comboBox.getSelectionModel().select("All");
        if (UserSession.getRole().equals("therapist") || UserSession.getRole().equals("nutritionist")) {
            addBtn.setVisible(true);
        }
        new ZoomIn(sessionPane).play();
        int y = 0;
        int x = 0;
        List<Session> sessions;
        st = new ServiceSession();
        sessions = st.listSesion();
        for (int i = 0; i < sessions.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/session/SessionItem.fxml"));
            try {
                AnchorPane pane = loader.load();
                SessionItemController c = loader.getController();
                c.setData(sessions.get(i),false);

                if (x > 2) {
                    y++;
                    x = 0;
                }
                sessionGrid.add(pane, x, y);
                x++;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void addSessionAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/session/AddSessionF.fxml"));
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
    private void SearchButtn(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/session/SearchPage.fxml"));

        Parent root = loader.load();
        SearchPageController src = loader.getController();
        src.function(Search.getText());
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
    private void comboBoxAction(ActionEvent event) {
        int y = 0;
        int x = 0;
        System.out.println("jj");
        List<Session> sessions = null;
        if (comboBox.getValue() == "Yours") {
            sessionGrid.getChildren().clear();
            sessions = st.ListSessionByTherp(UserSession.getUser_id());
            for (int i = 0; i < sessions.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/session/SessionItem.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    SessionItemController c = loader.getController();
                    c.setData(sessions.get(i),false);
                    System.out.println(sessions.get(i));
                    if (x > 2) {
                        y++;
                        x = 0;
                    }
                    sessionGrid.add(pane, x, y);
                    x++;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else if (comboBox.getValue() == "All") {
            sessionGrid.getChildren().clear();
            st = new ServiceSession();
            sessions = null;
            sessions = st.listSesion();
            System.out.println(st.listSesion().size());
            for (int i = 0; i < sessions.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/session/SessionItem.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    SessionItemController c = loader.getController();
                    c.setData(sessions.get(i),false);
                    
                    if (x > 2) {
                        y++;
                        x = 0;
                    }
                    sessionGrid.add(pane, x, y);
                    x++;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else if (comboBox.getValue() == "On Going Sessions") {
            sessionGrid.getChildren().clear();

            sessions = st.ListSessionByUser(UserSession.getUser_id());
            System.out.println(st.listSesion().size());
            for (int i = 0; i < sessions.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/session/SessionItem.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    SessionItemController c = loader.getController();
                    c.setData(sessions.get(i),true);
                    if (x > 2) {
                        y++;
                        x = 0;
                    }
                    sessionGrid.add(pane, x, y);
                    x++;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            
        }

    }
}
