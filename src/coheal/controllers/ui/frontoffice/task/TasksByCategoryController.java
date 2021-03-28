/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceTaskCategory;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TasksByCategoryController implements Initializable {

    @FXML
    private ScrollPane tasksPane;
    @FXML
    private GridPane tasksGrid;
    @FXML
    private JFXButton addBtn;
    double xOffset, yOffset;
    private ServiceTask st = new ServiceTask();
    private ServiceTaskCategory stc = new ServiceTaskCategory();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserSession.getRole().equals("therapist")) {
            addBtn.setVisible(true);
        }
        new ZoomIn(tasksPane).play();
        int y = 0;
        int x = 0;
        TaskCategoryHolder holder = TaskCategoryHolder.getINSTANCE();
        List<?> tasks = Stream.concat(stc.ListerTasksByIdCatg(holder.getName()).stream(), stc.ListerPaidTasksByIdCatg(holder.getName()).stream())
                .collect(Collectors.toList());
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof PaidTask) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskItem.fxml"));
                try {
                    Pane pane = loader.load();
                    TaskItemController c = loader.getController();
                    c.setPaidTaskData((PaidTask) tasks.get(i));
                    if (x > 2) {
                        y++;
                        x = 0;
                    }
                    tasksGrid.add(pane, x, y);
                    x++;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskItem.fxml"));
                try {
                    Pane pane = loader.load();
                    TaskItemController c = loader.getController();
                    c.setData((Task) tasks.get(i));
                    if (x > 2) {
                        y++;
                        x = 0;
                    }
                    tasksGrid.add(pane, x, y);
                    x++;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @FXML
    private void addTaskAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/task/AddTask.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TasksByCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void backAction(MouseEvent event) throws IOException {
        AnchorPane pageHolder = (AnchorPane) tasksPane.getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/task/AllCategories.fxml")));

    }

}
