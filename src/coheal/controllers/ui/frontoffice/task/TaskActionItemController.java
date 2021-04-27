/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.task.TaskActions;
import coheal.services.task.ServiceTaskActions;
import coheal.services.user.UserSession;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TaskActionItemController implements Initializable {

    @FXML
    private Label taskActionName;
    @FXML
    private Label taskActionDescription;
    @FXML
    private FontAwesomeIconView updateIcon;
    @FXML
    private FontAwesomeIconView deleteIcon;
    private ServiceTaskActions sta=new ServiceTaskActions();
    int id=0;
    double xOffset, yOffset;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserSession.getRole().equals("ROLE_Therapist")) {
            updateIcon.setVisible(true);
            deleteIcon.setVisible(true);
        }
    }   
    public void setData(TaskActions ta){
        taskActionName.setText(ta.getTitle());
        taskActionDescription.setText(ta.getDescription());
        id=ta.getActionId();
    }

    @FXML
    private void updateTaskAction(MouseEvent event) throws IOException {
        TaskActionHolder tah=TaskActionHolder.getINSTANCE();
        tah.setId(id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/task/UpdateTaskActionF.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        HomePageHolderController hpc = new HomePageHolderController();
        hpc.setStage(stage);
        stage.show();
        root.setOnMousePressed((MouseEvent mouseEvent) -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent mouseEvent) -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
            stage.setOpacity(0.85f);
        });
        root.setOnMouseReleased((MouseEvent mouseEvent) -> {
            stage.setOpacity(1.0f);
        });
    }

    @FXML
    private void deleteTaskAction(MouseEvent event) {
        System.out.println(id);
        sta.deleteTaskActions(id);
    }
    
}
