/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class AdminPageHolderController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private AnchorPane pageHolder;
    @FXML
    private AnchorPane slider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/backoffice/DashboardPage.fxml")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void dashboardPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(0);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/backoffice/DashboardPage.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void usersPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(67);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
       try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/backoffice/user/AdminAddRole.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void tasksPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(137);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/backoffice/task/Task.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void booksPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(206);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
//        try {
//            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/book/BookPage.fxml")));
//        } catch (IOException ex) {
//            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void eventsPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(276);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
//        try {
//            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/book/BookPage.fxml")));
//        } catch (IOException ex) {
//            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void sessionsPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(344);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
//        try {
//            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/book/BookPage.fxml")));
//        } catch (IOException ex) {
//            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void recipesPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(413);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
//        try {
//            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/book/BookPage.fxml")));
//        } catch (IOException ex) {
//            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void moderationPageAction(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(481);
        slide.play();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        try {
            pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/backoffice/report/ModerationPage.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageHolderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
