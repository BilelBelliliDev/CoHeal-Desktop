/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.reportcontroller;

import coheal.entities.rate.BookRate;
import coheal.entities.rate.Rate;
import coheal.entities.rate.RecipeRate;
import coheal.services.report.RateService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class RatePopupUIController implements Initializable {

    private int id, userId;
    private String s;
    @FXML
    private Label dataId;
    @FXML
    private Rating ratingId;
    @FXML
    private Button rateBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void rateAction(ActionEvent event) {
        RateService rs = new RateService();
        Rate r;
        Stage stage;
        switch (s) {
            case "Book":
                r = new BookRate();
                r.setUserId(userId);
                r.setScore(ratingId.getRating());
                rs.addRate(r, id);
                stage = (Stage) rateBtn.getScene().getWindow();
                stage.close();
                break;
            case "Recipe":
                r = new RecipeRate();
                r.setUserId(userId);
                r.setScore(ratingId.getRating());
                rs.addRate(r, id);
                stage = (Stage) rateBtn.getScene().getWindow();
                stage.close();
                break;
        }

    }

    @FXML
    private void cancelAction(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public int getId() {
        return id;
    }

    public void setData(int id, int userId, String s) {
        this.id = id;
        this.userId = userId;
        this.s = s;
        dataId.setText(s + " id: " + id + ", User id: " + userId);
    }

}
