/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.task;

import coheal.entities.task.TaskCategory;
import coheal.services.task.ServiceTaskCategory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

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
    @FXML
    private TableColumn<TaskCategory, ?> imgCol;
    @FXML
    private JFXButton addButton;
    @FXML
    private FontAwesomeIconView deleteButton;
    @FXML
    private FontAwesomeIconView updateButton;
    private static String projectPath = System.getProperty("user.dir").replace("/", "\\");

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
        imgCol.setCellValueFactory(new PropertyValueFactory<>("img"));
        categoryTable.getItems().addAll(l);
    }

    @FXML
    private void addCategoryAction(ActionEvent event) {
         Window owner = addButton.getScene().getWindow();
        tc.setName(name.getText());
        File dest = null;
        if(f!=null){
         dest = new File(projectPath + "/src/coheal/resources/images/tasks/" + f.getName());
        }
            try {
                 if (dest != null) {
                        if (FileUtils.contentEquals(f, dest)) {
                            System.out.println("existe");
                        } else {
                            FileUtils.copyFile(f, dest);
                        }
                    }

            } catch (IOException e) {
                e.printStackTrace();
            }
        st.createTaskCategory(tc);
        AlertBox(Alert.AlertType.CONFIRMATION, owner, "Confirmation","Category added successfully!");
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
    private void addImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        f = fileChooser.showOpenDialog(null);
        imageTxt.setText(f.getName());
        tc.setImgUrl(f.getName());
    }

    @FXML
    private void deleteCategoryAction(MouseEvent event) {
        Window owner = addButton.getScene().getWindow();
        TaskCategory taskc = null;
        taskc = categoryTable.getSelectionModel().getSelectedItem();
        st.deleteTaskCategory(taskc.getCatgid());
        AlertBox(Alert.AlertType.CONFIRMATION, owner, "Confirmation","Category deleted successfully!");
        categoryTable.getItems().clear();
        init();
    }

    @FXML
    private void updateCategoryAction(MouseEvent event) {
        Window owner = addButton.getScene().getWindow();
        TaskCategory taskc = null;
        taskc = categoryTable.getSelectionModel().getSelectedItem();
        tc.setCatgid(taskc.getCatgid());
        tc.setName(name.getText());
        File dest = null;
        if(f!=null){
         dest = new File(projectPath + "/src/coheal/resources/images/tasks/" + f.getName());
        }
            try {
                 if (dest != null) {
                        if (FileUtils.contentEquals(f, dest)) {
                            System.out.println("existe");
                        } else {
                            FileUtils.copyFile(f, dest);
                        }
                    }

            } catch (IOException e) {
                e.printStackTrace();
            }
        st.updateTaskCategory(tc);
        AlertBox(Alert.AlertType.CONFIRMATION, owner, "Confirmation","Category updated successfully!");
        categoryTable.getItems().clear();
        init();
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
