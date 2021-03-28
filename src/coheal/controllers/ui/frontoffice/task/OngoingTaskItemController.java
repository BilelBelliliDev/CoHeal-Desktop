/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class OngoingTaskItemController implements Initializable {

    @FXML
    private Label TaskTitle;
    @FXML
    private Label taskDescription;
    int id=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Task t) {
        TaskTitle.setText(t.getTitle());
        taskDescription.setText(t.getDescription());
        id = t.getTaskId();
    }

    public void setPaidTaskData(PaidTask pt) {
        TaskTitle.setText(pt.getTitle());
        taskDescription.setText(pt.getDescription());
        id = pt.getTaskId();
    }

    @FXML
    private void showTaskDetails(MouseEvent event) throws IOException {
        TaskHolder th = TaskHolder.getINSTANCE();
        th.setId(id);
//         AnchorPane pageHolder = (AnchorPane) TaskTitle.getParent();
//        pageHolder.getChildren().removeAll(pageHolder.getChildren());
//        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskDetails.fxml")));

    }
}
