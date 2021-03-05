/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.task;

import coheal.entities.task.Task;
import coheal.entities.task.TaskActions;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceUserTask;
import coheal.services.task.ServiceTaskActions;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ListTaskActionsController implements Initializable {

    @FXML
    private TableView<TaskActions> data;
    @FXML
    private TableColumn<TaskActions, String> titreTAcol;
    @FXML
    private TableColumn<TaskActions, String> descTAcol;
    ObservableList<TaskActions> l;
    ServiceTaskActions st = new ServiceTaskActions();
    ServiceUserTask service=new ServiceUserTask();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        l = FXCollections.observableList(st.ListTaskActionsByTaskId(getTask()));
        titreTAcol.setCellValueFactory(new PropertyValueFactory<TaskActions, String>("title"));
        descTAcol.setCellValueFactory(new PropertyValueFactory<TaskActions, String>("description"));

        data.setItems(l);
       
    }

    public int getTask() {
        TaskActionsHolder holder = TaskActionsHolder.getINSTANCE();
        int t = holder.getTc();
        return t;
    }

    @FXML
    private void participerButton(ActionEvent event) {
        service.participer(1, getTask());
    }

    @FXML
    private void retourButton(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/task/ParticiperUserFreeTask.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            // Hide  current window 
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
