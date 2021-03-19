/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.task;

import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.services.task.ServiceTaskCategory;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ListTaskByCategoryController implements Initializable {

    @FXML
    private ComboBox<String> comboCat;
    @FXML
    private TableView<Task> tableview;
    @FXML
    private TableColumn<Task, String> titreCol;
    @FXML
    private TableColumn<Task, String> descpCol;
    @FXML
    private TableColumn<Task, String> numDaysCol;
    @FXML
    private TableColumn<Task, String> minUsersCol;
    @FXML
    private TableColumn<Task, String> maxCol;
    ServiceTaskCategory stc = new ServiceTaskCategory();
    String cat = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TaskCategory tc = new TaskCategory();
        for (int i = 0; i < stc.ListTaskCategory().size(); i++) {
            comboCat.getItems().add(stc.ListTaskCategory().get(i).getName());
        }
    }

    @FXML
    private void comboBoxAction(ActionEvent event) {
        cat=comboCat.getValue();
        System.out.println(cat);
        ObservableList<Task> l = stc.ListerTasksByIdCatg(cat);
        titreCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descpCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        numDaysCol.setCellValueFactory(new PropertyValueFactory<>("numOfDays"));
        maxCol.setCellValueFactory(new PropertyValueFactory<>("maxUsers"));
        minUsersCol.setCellValueFactory(new PropertyValueFactory<>("minUsers"));
        tableview.setItems(l);
    }

    @FXML
    private void retourButtonAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/task/PaidTaskOrFree.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            // Hide this current window 
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
