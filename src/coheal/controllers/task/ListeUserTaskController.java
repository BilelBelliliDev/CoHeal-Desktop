/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.task;

import coheal.entities.task.Task;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ListeUserTaskController implements Initializable {

    @FXML
    private TableView<Task> data;
    @FXML
    private TableColumn<Task, String> titreCol;
    @FXML
    private TableColumn<Task, String> descpCol;
    @FXML
    private TableColumn<Task, String> numDaysCol;
    @FXML
    private TableColumn<Task, String> minUsersCol;
    @FXML
    private TableColumn<Task, String> maxUsersCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retourButton(ActionEvent event) {
    }
    
}
