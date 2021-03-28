/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice;

import animatefx.animation.ZoomIn;
import coheal.services.task.ServicePaidTask;
import coheal.services.task.ServiceTask;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class DashboardPageController implements Initializable {

    @FXML
    private FontAwesomeIconView close;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private Label nbTasks;
    ServicePaidTask spt = new ServicePaidTask();
    ServiceTask st = new ServiceTask();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(dashboardPane).play();
        nbTasks.setText(String.valueOf(spt.getCountPaidTask()+st.getCountTask()));
    }    

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.setIconified(true);
    }
    
}
