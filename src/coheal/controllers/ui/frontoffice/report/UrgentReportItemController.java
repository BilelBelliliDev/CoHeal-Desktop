/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.report;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.report.Report;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class UrgentReportItemController implements Initializable {

    @FXML
    private FontAwesomeIconView icon;
    @FXML
    private Label text;
    Report report = null;
    double xOffset, yOffset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Report r) {
        report = r;
        switch (r.getType()) {
            case "task":
                icon.setGlyphName("TASKS");
                text.setText("\"" + r.getTitle() + "\" reported " + r.getTotal() + " times");
                break;
            case "book":
                icon.setGlyphName("BOOK");
                text.setText("\"" + r.getTitle() + "\" reported " + r.getTotal() + " times");
                break;
            case "event":
                icon.setGlyphName("CALENDAR");
                text.setText("\"" + r.getTitle() + "\" reported " + r.getTotal() + " times");
                break;
            case "session":
                icon.setGlyphName("USER_MD");
                text.setText("\"" + r.getTitle() + "\" reported " + r.getTotal() + " times");
                break;
            case "recipe":
                icon.setGlyphName("STICKY_NOTE");
                text.setText("\"" + r.getTitle() + "\" reported " + r.getTotal() + " times");
                break;
        }
    }

    @FXML
    private void details(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/report/OpenReportDetailsPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(UrgentReportItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        OpenReportDetailsPageController c = loader.getController();
        c.setData(report);
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
