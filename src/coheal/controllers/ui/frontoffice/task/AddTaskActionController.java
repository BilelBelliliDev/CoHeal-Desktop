/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import coheal.entities.task.TaskActions;
import coheal.services.task.ServiceTaskActions;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AddTaskActionController implements Initializable {

    @FXML
    private JFXTextField Titre;
    @FXML
    private JFXTextArea Description;
    private ServiceTaskActions sta = new ServiceTaskActions();
    TaskActions ta = new TaskActions();
    TaskHolder th = TaskHolder.getINSTANCE();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void addTaskAction(ActionEvent event) {
        ta.setTitle(Titre.getText());
        ta.setDescription(Description.getText());
        sta.createTaskActions(th.getId(), ta);
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) Titre.getScene().getWindow();
        stage.close();
    }

}
