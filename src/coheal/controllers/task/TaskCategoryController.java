/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.task;

import coheal.entities.task.TaskCategory;
import coheal.services.task.ServiceTaskCategory;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TaskCategoryController implements Initializable {

    @FXML
    private Label imageLabel;
    @FXML
    private TextField nom;
    @FXML
    private TextField imageText;
    private Label afficherLabel;

    @FXML
    private TableColumn<TaskCategory, String> col_name;
    private TableColumn<TaskCategory, String> col_img;
    @FXML
    private TableView<TaskCategory> table;
    ServiceTaskCategory st = new ServiceTaskCategory();
    TaskCategory tc = new TaskCategory();
    TaskCategory taskc = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        init();
    }

    public void init() {
        ObservableList<TaskCategory> l = FXCollections.observableList(st.ListTaskCategory());
        //tableView
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getItems().addAll(l);
        table.setRowFactory(tv -> {
            TableRow<TaskCategory> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    TaskCategory rowData = row.getItem();
                    System.out.println(rowData.getCatgid());
                    nom.setText(rowData.getName());
                    imageText.setText(rowData.getImgUrl());
                }
            });
            return row;
        });
    }

    @FXML
    private void ajouterImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showOpenDialog(null);
        String fileURL = f.getAbsolutePath();
        imageText.setText(f.getName());
        tc.setImgUrl(fileURL);
        String t = imageText.getText();

    }

    @FXML
    private void ajouterTaskCategory(ActionEvent event) {

        tc.setName(nom.getText());
        st.createTaskCategory(tc);
        //table.setItems((ObservableList<TaskCategory>)st.ListTaskCategory());
        table.getItems().clear();
        init();
    }

    private void afficherTasksCategory(ActionEvent event) {
        System.out.println(st.ListTaskCategory().toString());
        afficherLabel.setText(st.ListTaskCategory().toString());

    }

    private void linkTask(ActionEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("coheal/views/task/TaskCategory.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void afficherGestionTask(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/task/Task.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        taskc = table.getSelectionModel().getSelectedItem();
        System.out.println(taskc.getCatgid());
        st.deleteTaskCategory(taskc.getCatgid());
        table.getItems().clear();
        init();
    }

    @FXML
    private void actionsAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/task/TaskActions.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void update(ActionEvent event) {
        taskc = table.getSelectionModel().getSelectedItem();
        tc.setCatgid(taskc.getCatgid());
        tc.setName(nom.getText());
        System.out.println(tc);
        st.updateTaskCategory(tc);
        table.getItems().clear();
        init();
    }
}
