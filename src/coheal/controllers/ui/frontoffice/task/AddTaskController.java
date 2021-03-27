/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceTaskCategory;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class AddTaskController implements Initializable {

    @FXML
    private JFXTextField Titre;
    @FXML
    private JFXTextField numOfDays;
    @FXML
    private JFXTextArea Desceiption;
    @FXML
    private JFXTextField minUsers;
    @FXML
    private JFXTextField maxUsers;
    @FXML
    private ToggleGroup type;
    @FXML
    private JFXComboBox<String> comboCatg;
    Task task = null;
    ServiceTask st = new ServiceTask();
    Task t = new Task();
    ServiceTaskCategory stc = new ServiceTaskCategory();
    String cat = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TaskCategory tc = new TaskCategory();
        for (int i = 0; i < stc.ListTaskCategory().size(); i++) {
            comboCatg.getItems().add(stc.ListTaskCategory().get(i).getName());
        }
    }

    @FXML
    private void addTaskAction(ActionEvent event) {
         Window owner = maxUsers.getScene().getWindow();
        if (Titre.getText().isEmpty()) {
            AlertBox(Alert.AlertType.ERROR, owner, "Erreur",
                    "Veuillez saisir le titre");
            return;
        } else if (Desceiption.getText().isEmpty()) {
            AlertBox(Alert.AlertType.ERROR, owner, "Erreur",
                    "Veuillez saisir le description");
            return;
        } else if (numOfDays.getText().isEmpty()) {
            AlertBox(Alert.AlertType.ERROR, owner, "Erreur",
                    "Veuillez remplir les champs");
            return;
        } else if (maxUsers.getText().isEmpty()) {
            AlertBox(Alert.AlertType.ERROR, owner, "Erreur",
                    "Veuillez remplir les champs");
            return;
        } else if (numOfDays.getText().isEmpty()) {
            AlertBox(Alert.AlertType.ERROR, owner, "Erreur",
                    "Veuillez remplir les champs");
            return;
        } else if (comboCatg.getSelectionModel().getSelectedItem().isEmpty()) {
            AlertBox(Alert.AlertType.ERROR, owner, "Erreur",
                    "Veuillez choisir un categorie");
            return;
        } else {
            t.setTitle(Titre.getText());
            t.setDescription(Desceiption.getText());
            int n = Integer.parseInt(numOfDays.getText());
            t.setNumOfDays(n);
            int min = Integer.parseInt(minUsers.getText());
            int max = Integer.parseInt(maxUsers.getText());
            t.setMaxUsers(max);
            t.setMinUsers(min);
            st.createTask(1, cat, t);
        }
    }
    
    private static void AlertBox(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void listCategoryCombo(ActionEvent event) {
        cat = comboCatg.getValue();
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) comboCatg.getScene().getWindow();
        stage.close();
    }

}
