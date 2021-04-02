/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.report;

import animatefx.animation.ZoomIn;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class RateAlertUIController implements Initializable {

    private int id, userId;
    private String s;
    @FXML
    private Label labelId;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(anchor).play();
    }    

    private void closeAction(ActionEvent event) {
        
    }
    
    public void setData(int id, int userId, String s) {
        this.id = id;
        this.userId=userId;
        this.s=s;
        labelId.setText("You already rated this "+s+"!");
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = (Stage) labelId.getScene().getWindow();
        stage.close();
    }
    
}
