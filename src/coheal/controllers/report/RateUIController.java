/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.report;

import coheal.entities.rate.BookRate;
import coheal.entities.rate.EventRate;
import coheal.entities.rate.Rate;
import coheal.entities.rate.RecipeRate;
import coheal.entities.rate.SessionRate;
import coheal.entities.rate.TaskRate;
import coheal.services.report.RateService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class RateUIController implements Initializable {

    RateService rs = new RateService();
    /**
     * Initializes the controller class.
     */
    private TextField reportId;
    private TextField userId;
    @FXML
    private Rating ratingId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void rateBookAction(ActionEvent event) {
        Rate r = new BookRate();
        r.setUserId(1);
        r.setScore(ratingId.getRating());
        rs.addRate(r, 2);
    }

    @FXML
    private void rateEventAction(ActionEvent event) {
        Rate r = new EventRate();
        r.setUserId(1);
        r.setScore(ratingId.getRating());
        rs.addRate(r, 1);
    }

    @FXML
    private void rateTaskAction(ActionEvent event) {
        Rate r = new TaskRate();
        r.setUserId(1);
        r.setScore(ratingId.getRating());
        rs.addRate(r, 1);
    }

    @FXML
    private void rateSessionAction(ActionEvent event) {
        Rate r = new SessionRate();
        r.setUserId(1);
        r.setScore(ratingId.getRating());
        rs.addRate(r, 1);
    }

    @FXML
    private void rateRecipeAction(ActionEvent event) {
        Rate r = new RecipeRate();
        r.setUserId(1);
        r.setScore(ratingId.getRating());
        rs.addRate(r, 1);
    }

    @FXML
    private void ratePopupAction(ActionEvent event) throws IOException {

//        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("/coheal/views/reportui/RatePopupUI.fxml"));
//        Parent root = loader.load();
//
//        RatePopupUIController c = loader.getController();
//        c.setId(2);
//        Stage stage = new Stage();
//        stage.show();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/reportui/RatePopupUI.fxml"));
        Parent root = loader.load();
        RatePopupUIController c = loader.getController();
        //c.setData(selectedId, 1, "Book");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

}
