/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.task;

import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.services.task.ServiceUserTask;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ListeUserTaskController implements Initializable {

    @FXML
    private TableView<Task> data;
    @FXML
    private TableColumn<Task, String> titreCol;
    @FXML
    private TableColumn<Task, String> descpCol;
    @FXML
    private TableColumn<Task, String> numDaysCol;
    @FXML
    private TableColumn<Task, String> minUsersCol;
    @FXML
    private TableColumn<Task, String> maxUsersCol;
    ServiceUserTask su=new ServiceUserTask();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<Task> l = FXCollections.observableList(su.ListerTasksByIdUser(1));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descpCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        numDaysCol.setCellValueFactory(new PropertyValueFactory<>("numOfDays"));
        maxUsersCol.setCellValueFactory(new PropertyValueFactory<>("maxUsers"));
        minUsersCol.setCellValueFactory(new PropertyValueFactory<>("minUsers"));
        data.setItems(l);
        
        data.setRowFactory(tv -> {
            TableRow<Task> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                      Task  rowData = row.getItem();
  
                        System.out.println(rowData);
                    }
                }
            });
            return row;
        });
        
        
    }    

    @FXML
    private void retourButton(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/task/PaidTaskOrFree.fxml"));
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
