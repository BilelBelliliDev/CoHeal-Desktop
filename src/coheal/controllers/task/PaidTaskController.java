/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.task;

import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.services.task.ServicePaidTask;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class PaidTaskController implements Initializable {

    @FXML
    private TableView<PaidTask> data;
    @FXML
    private TableColumn<PaidTask, String> titreCol;
    @FXML
    private TableColumn<PaidTask, String> descpCol;
    @FXML
    private TableColumn<PaidTask, String> numDaysCol;
    @FXML
    private TableColumn<PaidTask, String> minUsersCol;
    @FXML
    private TableColumn<PaidTask, String> maxUsersCol;
    @FXML
    private TableColumn<PaidTask, String> prixCol;
    ServicePaidTask st=new ServicePaidTask();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<PaidTask> l = FXCollections.observableList(st.listPaidTask());
       titreCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descpCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        numDaysCol.setCellValueFactory(new PropertyValueFactory<>("numOfDays"));
        maxUsersCol.setCellValueFactory(new PropertyValueFactory<>("maxUsers"));
        minUsersCol.setCellValueFactory(new PropertyValueFactory<>("minUsers"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        data.setItems(l);
        
         data.setRowFactory(tv -> {
            TableRow<PaidTask> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                      PaidTask  rowData = row.getItem();
                        System.out.println(rowData);
                        Parent root = null;

                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        try {
                            TaskActionsHolder holder = TaskActionsHolder.getINSTANCE();
                            holder.setTc(rowData.getTaskId());
                            root = FXMLLoader.load(PaidTaskController.this.getClass().getResource("/coheal/views/task/ListTaskActions.fxml"));
                            
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            return row;
        });
    }    
    
}
