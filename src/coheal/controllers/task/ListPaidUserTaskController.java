/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.task;

import coheal.entities.task.PaidTask;
import coheal.services.task.ServiceUserTask;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ListPaidUserTaskController implements Initializable {

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
    ServiceUserTask st =new ServiceUserTask();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<PaidTask> l = FXCollections.observableList(st.ListerPaidTasksByIdUser(1));
       titreCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descpCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        numDaysCol.setCellValueFactory(new PropertyValueFactory<>("numOfDays"));
        maxUsersCol.setCellValueFactory(new PropertyValueFactory<>("maxUsers"));
        minUsersCol.setCellValueFactory(new PropertyValueFactory<>("minUsers"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        data.setItems(l);
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
