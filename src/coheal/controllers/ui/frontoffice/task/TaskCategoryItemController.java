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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    public void setData(TaskCategory tc) {
        ServiceTaskCategory stc = new ServiceTaskCategory();
        taskCatgTitle.setText(tc.getName());
        int n=stc.ListerTasksByIdCatg(tc.getName()).size()+stc.ListerPaidTasksByIdCatg(tc.getName()).size();
        taskCatgTotalTasks.setText(n + " Tasks");
        taskCatgImg.setImage(tc.getImg().getImage());
    }

    @FXML
    private void showTasksAction(MouseEvent event) throws IOException {
        TaskCategoryHolder holder = TaskCategoryHolder.getINSTANCE();
        holder.setName(taskCatgTitle.getText());
        AnchorPane pageHolder = (AnchorPane) taskCatgTitle.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/task/TasksByCategory.fxml")));

    }

}
