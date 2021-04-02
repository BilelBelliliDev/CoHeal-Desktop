/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.report;

import animatefx.animation.ZoomIn;
import coheal.services.report.ReportService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class AlertUIController implements Initializable {

    @FXML
    private FontAwesomeIconView ex;
    
    String title;
    Stage s;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(anchor).play();
    }    

    @FXML
    private void cancelAction(ActionEvent event) {
        Stage stage = (Stage) ex.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeReport(ActionEvent event) {
        ReportService rs = new ReportService();
        rs.closeReport(title);
        Stage stage = (Stage) ex.getScene().getWindow();
        stage.close();
        s.close();
    }
    
    public void setData(String t, Stage s){
        title=t;
        this.s=s;
    }
}
