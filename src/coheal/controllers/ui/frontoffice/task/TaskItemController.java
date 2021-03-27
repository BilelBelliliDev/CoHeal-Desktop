/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import coheal.entities.task.Task;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class TaskItemController implements Initializable {

    @FXML
    private Label taskTitle;
    @FXML
    private ImageView taskImg;
    @FXML
    private Label taskDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void setData(Task t){
        taskTitle.setText(t.getTitle());
        taskDesc.setText(t.getDescription());
    }
}
