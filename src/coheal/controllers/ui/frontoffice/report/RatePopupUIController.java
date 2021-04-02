/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.report;

import animatefx.animation.ZoomIn;
import coheal.entities.rate.BookRate;
import coheal.entities.rate.EventRate;
import coheal.entities.rate.Rate;
import coheal.entities.rate.RecipeRate;
import coheal.entities.rate.SessionRate;
import coheal.entities.rate.TaskRate;
import coheal.services.report.RateService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private Rating ratingId;
    @FXML
    private FontAwesomeIconView face;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(anchor).play();
    }
    public int getId() {
        return id;
    }

    public void setData(int id, int userId, String s) {
        this.id = id;
        this.userId = userId;
        this.s = s;
    }

    @FXML
    private void rateClicked(MouseEvent event) {
        if (ratingId.getRating() <= 2) {
            face.setGlyphName("FROWN_ALT");
        } else if (ratingId.getRating() > 2 && ratingId.getRating() <= 3) {
            face.setGlyphName("MEH_ALT");
        } else {
            face.setGlyphName("SMILE_ALT");
        }
    }

    @FXML
    private void disableHover(MouseEvent event) {
        ratingId.setUpdateOnHover(false);
    }

    @FXML
    private void enableHover(MouseEvent event) {
        ratingId.setUpdateOnHover(true);
    }

    @FXML
    private void rateAction(MouseEvent event) {
        RateService rs = new RateService();
        Rate r;
        Stage stage;
        switch (s) {
            case "Book":
                r = new BookRate();
                r.setUserId(userId);
                r.setScore(ratingId.getRating());
                rs.addRate(r, id);
                stage = (Stage) ratingId.getScene().getWindow();
                stage.close();
                break;
            case "Recipe":
                r = new RecipeRate();
                r.setUserId(userId);
                r.setScore(ratingId.getRating());
                rs.addRate(r, id);
                stage = (Stage) ratingId.getScene().getWindow();
                stage.close();
                break;
            case "Task":
                r = new TaskRate();
                r.setUserId(userId);
                r.setScore(ratingId.getRating());
                rs.addRate(r, id);
                stage = (Stage) ratingId.getScene().getWindow();
                stage.close();
                break;
            case "Event":
                r = new EventRate();
                r.setUserId(userId);
                r.setScore(ratingId.getRating());
                rs.addRate(r, id);
                stage = (Stage) ratingId.getScene().getWindow();
                stage.close();
                break;
            case "Session":
                r = new SessionRate();
                r.setUserId(userId);
                r.setScore(ratingId.getRating());
                rs.addRate(r, id);
                stage = (Stage) ratingId.getScene().getWindow();
                stage.close();
                break;
        }
    }

    @FXML
    private void cancelAction(MouseEvent event) {
        Stage stage = (Stage) ratingId.getScene().getWindow();
        stage.close();
    }

}
