/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.task;

import coheal.entities.task.Task;
import coheal.entities.task.TaskActions;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceTaskActions;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TaskActionsController implements Initializable {

    @FXML
    private TextField txtTitre;
    @FXML
    private TextField txtDescrp;
    ServiceTaskActions sta=new ServiceTaskActions();
    TaskActions ta=new TaskActions();
    @FXML
    private ComboBox<String> comboTask;
    String task="";
     ServiceTask st = new ServiceTask();
    @FXML
    private TableView<TaskActions> data;
    @FXML
    private TableColumn<TaskActions,String> titrecol;
    @FXML
    private TableColumn<TaskActions, String> desccol;
    @FXML
    private Button addButton;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Task tc = new Task();
        for (int i = 0; i < st.ListTask().size(); i++) {
            comboTask.getItems().add(st.ListTask().get(i).getTitle());
            init();
        }

    }    

   public void init(){
       ObservableList<TaskActions> l = FXCollections.observableList(sta.ListTaskActions());
       titrecol.setCellValueFactory(new PropertyValueFactory<TaskActions, String>("title"));
       desccol.setCellValueFactory(new PropertyValueFactory<TaskActions, String>("description"));
       
        data.setItems(l);
    }
    @FXML
    private void addActions(ActionEvent event) {
        Window owner = addButton.getScene().getWindow();
        if (txtTitre.getText().isEmpty()) {
            AlertBox(Alert.AlertType.ERROR, owner, "Erreur",
                    "Veuillez saisir le titre");
            return;
        }else if(txtDescrp.getText().isEmpty()){
             AlertBox(Alert.AlertType.ERROR, owner, "Erreur",
                    "Veuillez saisir la description");
            return;
        } 
        else {
        ta.setTitle(txtTitre.getText());
        ta.setDescription(txtDescrp.getText());
        sta.createTaskActions(task,ta);
         data.getItems().clear();
       init();
        }
        
    }

    @FXML
    private void getTasks(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/task/Task.fxml"));
            Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        // Hide this current window 
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void taskAction(ActionEvent event) {
        task=comboTask.getValue();
    }
    
    private static void AlertBox(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
