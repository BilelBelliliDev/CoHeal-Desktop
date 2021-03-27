/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.services.task.ServiceTask;
import coheal.services.ui.UIService;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TaskPageController implements Initializable {

    @FXML
    private HBox categoriesHBox;
    private UIService stc = new UIService();
    private ServiceTask st = new ServiceTask();
    @FXML
    private GridPane taskGrid;
    @FXML
    private ScrollPane taskPane;

    double xOffset, yOffset;
    @FXML
    private JFXButton addBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
if(UserSession.getRole().equals("therapist"))
            addBtn.setVisible(true);
        new ZoomIn(taskPane).play();
        List<TaskCategory> catTasks = stc.topThreeCatg();
        for (int i = 0; i < catTasks.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskCategoryItem.fxml"));
            try {
                AnchorPane pane = loader.load();
                TaskCategoryItemController c = loader.getController();

                c.setData(catTasks.get(i));
                categoriesHBox.getChildren().add(pane);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        int y = 0;
        int x = 0;
        List<Task> tasks;
        tasks = st.ListTask();
        for (int i = 0; i < tasks.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskItem.fxml"));
            try {
                Pane pane = loader.load();
                TaskItemController c = loader.getController();
                c.setData(tasks.get(i));
                if (x > 2) {
                    y++;
                    x = 0;
                }
                taskGrid.add(pane, x, y);
                x++;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void addTaskAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/task/AddTask.fxml"));
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
}
