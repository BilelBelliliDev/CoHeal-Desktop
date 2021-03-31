/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

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
    int id = 0;
    @FXML
    private Label price;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Task t) {
        taskTitle.setText(t.getTitle());
        taskDesc.setText(t.getDescription());
        taskImg.setImage(t.getImg().getImage());
        id = t.getTaskId();
    }

    public void setPaidTaskData(PaidTask pt) {
        taskTitle.setText(pt.getTitle());
        taskDesc.setText(pt.getDescription());
        taskImg.setImage(pt.getImg().getImage());
        price.setText(pt.getPrice() + "DT");
        id = pt.getTaskId();
    }

    @FXML
    private void showTaskDetails(MouseEvent event) throws IOException {

        TaskHolder th = TaskHolder.getINSTANCE();
        th.setId(id);

        AnchorPane pageHolder = (AnchorPane) taskTitle.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskDetails.fxml")));
    }
}
