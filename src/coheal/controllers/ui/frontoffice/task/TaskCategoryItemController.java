/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;


import coheal.entities.task.TaskCategory;
import coheal.services.task.ServiceTaskCategory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class TaskCategoryItemController implements Initializable {

    @FXML
    private Label taskCatgTitle;
    @FXML
    private Label taskCatgTotalTasks;
    @FXML
    private ImageView taskCatgImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(TaskCategory tc){
        ServiceTaskCategory stc=new ServiceTaskCategory();
        taskCatgTitle.setText(tc.getName());
        taskCatgTotalTasks.setText(String.valueOf(stc.ListerTasksByIdCatg(tc.getName()).size())+" Tasks");
    }

}
