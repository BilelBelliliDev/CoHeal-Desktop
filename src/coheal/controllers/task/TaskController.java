/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.task;

import coheal.controllers.book.FXMLDocumentController;
import coheal.controllers.report.RateAlertUIController;
import coheal.controllers.report.RatePopupUIController;
import coheal.controllers.report.ReportPopupUIController;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.services.report.RateService;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceTaskCategory;
import coheal.services.user.ServiceUser;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TaskController implements Initializable {

    //Report/Rate Module (Bilel Bellili)
    private ServiceUser su = new ServiceUser();
    private RateService sr = new RateService();
    private int selectedId;
    @FXML
    private TextField Titre;
    @FXML
    private TextField numOfDays;
    @FXML
    private TextField minUsers;
    @FXML
    private TextField maxUsers;
    @FXML
    private TextArea Desceiption;
    @FXML
    private ComboBox<String> comboCatg;
    @FXML
    private TextField imageTEXT;
    private Label show;

    /**
     * Initializes the controller class.
     */
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
    @FXML
    private TableView<Task> tableview;

    Task task = null;
    ServiceTask st = new ServiceTask();
    Task t = new Task();
    ServiceTaskCategory stc = new ServiceTaskCategory();
    String cat = "";
    @FXML
    private Button addButton;
    @FXML
    private ComboBox<Integer> userIdBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //comboBox
        TaskCategory tc = new TaskCategory();
        for (int i = 0; i < stc.ListTaskCategory().size(); i++) {
            comboCatg.getItems().add(stc.ListTaskCategory().get(i).getName());
        }

        init();

        //Report/Rate Module (Bilel Bellili)  
        int num = su.AfficherPersonne().size();
        for (int i = 0; i < num; i++) {
            userIdBox.getItems().add(su.AfficherPersonne().get(i).getUserId());
        }

    }

    public void init() {
        //tableView
        ObservableList<Task> l = FXCollections.observableList(st.ListTask());
        titreCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descpCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        numDaysCol.setCellValueFactory(new PropertyValueFactory<>("numOfDays"));
        maxCol.setCellValueFactory(new PropertyValueFactory<>("maxUsers"));
        minUsersCol.setCellValueFactory(new PropertyValueFactory<>("minUsers"));
        tableview.setItems(l);
        tableview.setRowFactory(tv -> {
            TableRow<Task> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Task rowData = row.getItem();
                    Titre.setText(rowData.getTitle());
                    Desceiption.setText(rowData.getDescription());
                    String m = "" + rowData.getNumOfDays();
                    String min = "" + rowData.getMinUsers();
                    String max = "" + rowData.getMaxUsers();
                    selectedId = tableview.getSelectionModel().getSelectedItem().getTaskId();
                    numOfDays.setText(m);
                    minUsers.setText(min);
                    maxUsers.setText(max);
                }
            });
            return row;
        });
    }

    @FXML
    private void addImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showOpenDialog(null);
        String fileURL = f.getAbsolutePath();
        imageTEXT.setText(f.getName());
        t.setImgUrl(fileURL);
    }

    @FXML
    private void addTask(ActionEvent event) {

        Window owner = addButton.getScene().getWindow();
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
            tableview.getItems().clear();
            init();
        }
    }

    @FXML
    private void listCategoryCombo(ActionEvent event) {
        cat = comboCatg.getValue();

    }

    @FXML
    private void deleteAction(ActionEvent event) {
        task = tableview.getSelectionModel().getSelectedItem();

        st.deleteTask(task.getTaskId());
        tableview.getItems().clear();
        init();

    }

    @FXML
    private void updateAction(ActionEvent event) {
        task = tableview.getSelectionModel().getSelectedItem();
        t.setTitle(Titre.getText());
        t.setDescription(Desceiption.getText());
        int n = Integer.parseInt(numOfDays.getText());
        t.setNumOfDays(n);
        int min = Integer.parseInt(minUsers.getText());
        int max = Integer.parseInt(maxUsers.getText());
        t.setMaxUsers(max);
        t.setMinUsers(min);
        System.out.println(task);
        st.updateTask(t, task.getTaskId());
        tableview.getItems().clear();
        init();
        //tableview.setItems((ObservableList<Task>) st.ListTask());
    }

    @FXML
    private void afficherTaskActions(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/task/TaskActions.fxml"));
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

    private static void AlertBox(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void ratePopupAction(ActionEvent event) throws IOException {
        if (sr.isRated(selectedId, userIdBox.getValue(), "Task")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/RateAlertUI.fxml"));
            Parent root = loader.load();
            RateAlertUIController c = loader.getController();
            c.setData(selectedId, userIdBox.getValue(), "Task");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/RatePopupUI.fxml"));
            Parent root = loader.load();
            RatePopupUIController c = loader.getController();
            c.setData(selectedId, userIdBox.getValue(), "Task");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void reportPopupAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/ReportPopupUI.fxml"));
        Parent root = loader.load();
        ReportPopupUIController c = loader.getController();
        c.setData(selectedId, userIdBox.getValue(), "Task");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
