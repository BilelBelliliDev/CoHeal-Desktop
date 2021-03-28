/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import animatefx.animation.ZoomIn;
import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.services.task.ServiceUserTask;
import coheal.services.user.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class OngoingTasksController implements Initializable {

    @FXML
    private ScrollPane tasksPane;
    @FXML
    private GridPane tasksGrid;
    private ServiceUserTask sut = new ServiceUserTask();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       new ZoomIn(tasksPane).play();
       List<?> tasks = null;
        int y = 0;
        int x = 0;
       tasks = Stream.concat(sut.ListerTasksByIdUser(UserSession.getUser_id()).stream(), sut.ListerPaidTasksByIdUser(UserSession.getUser_id()).stream())
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
    private void backAction(MouseEvent event) throws IOException {
        AnchorPane pageHolder = (AnchorPane) tasksPane.getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskPage.fxml")));

    }
    
}
