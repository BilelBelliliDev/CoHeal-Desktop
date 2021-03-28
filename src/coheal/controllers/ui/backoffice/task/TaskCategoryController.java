/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.task;

import coheal.entities.task.TaskCategory;
import coheal.services.task.ServiceTaskCategory;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TaskCategoryController implements Initializable {

    @FXML
    private TableView<TaskCategory> categoryTable;
    @FXML
    private Label imageTxt;
    @FXML
    private JFXTextField name;
    @FXML
    private TableColumn<TaskCategory, String> nameCol;
    ServiceTaskCategory st = new ServiceTaskCategory();
    TaskCategory tc = new TaskCategory();
    File f = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       init();
        categoryTable.setRowFactory(tv -> {
            TableRow<TaskCategory> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    TaskCategory rowData = row.getItem();
                    name.setText(rowData.getName());
                    imageTxt.setText(rowData.getImgUrl());
                }
            });
            return row;
        });
    }

    public void init() {
        ObservableList<TaskCategory> l = FXCollections.observableList(st.ListTaskCategory());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTable.getItems().addAll(l);
    }

    @FXML
    private void addCategoryAction(ActionEvent event) {
        tc.setName(name.getText());
        st.createTaskCategory(tc);
        categoryTable.getItems().clear();
        init();
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) categoryTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void updateCategoryAction(ActionEvent event) {
         TaskCategory taskc = null;
        taskc = categoryTable.getSelectionModel().getSelectedItem();
        tc.setCatgid(taskc.getCatgid());
        tc.setName(name.getText());
        st.updateTaskCategory(tc);
        categoryTable.getItems().clear();
        init();
    }

    @FXML
    private void deleteCategoryAction(ActionEvent event) {
        TaskCategory taskc = null;
        taskc = categoryTable.getSelectionModel().getSelectedItem();
        st.deleteTaskCategory(taskc.getCatgid());
        categoryTable.getItems().clear();
        init();
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        f = fileChooser.showOpenDialog(null);
        imageTxt.setText(f.getName());
        tc.setImgUrl(f.getName());
    }

}
