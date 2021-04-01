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
import javafx.scene.control.Pagination;
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
    private GridPane tasksGrid;
    private ServiceUserTask sut = new ServiceUserTask();
    @FXML
    private Pagination pagination;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(tasksPane).play();
        //pagination.setPageFactory((pageindex) -> grid(pageindex));
        pagination.setPageFactory((pageindex) -> {
            if (grid(pageindex) != null) {
                return grid((pageindex));
            }
            return null;
        });
    }

    public GridPane grid(int pageindex) {
        List<?> tasks;
        tasks = Stream.concat(sut.ListerTasksByIdUser(UserSession.getUser_id()).stream(), sut.ListerPaidTasksByIdUser(UserSession.getUser_id()).stream())
                .collect(Collectors.toList());
        if (tasks != null) {
            GridPane pane = null;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/task/GridTask.fxml"));
            try {
                pane = loader.load();
                GridTaskController c = loader.getController();
                c.setData(pageindex, tasks);
                pagination.setPageCount(c.pageCount);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            return pane;
        }
        return null;
    }

    @FXML
    private void backAction(MouseEvent event) throws IOException {
        AnchorPane pageHolder = (AnchorPane) tasksPane.getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskPage.fxml")));

    }

}
