/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.event;

import coheal.entities.event.EventCategory;
import coheal.services.event.ServiceCategory;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.input.MouseEvent;
import static org.omg.CORBA.ORB.init;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class EventCategoryController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private TableView<EventCategory> categoryTable;
    @FXML
    private TableColumn<EventCategory, String> nameCol;
    ServiceCategory st = new ServiceCategory();
    EventCategory tc = new EventCategory();
    File f = null;
    @FXML
    private Label imageTxt;
    @FXML
    private JFXTextField RechercheTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        init();
        categoryTable.setRowFactory(tv -> {
            TableRow<EventCategory> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    EventCategory rowData = row.getItem();
                    name.setText(rowData.getName());
                    imageTxt.setText(rowData.getImgUrl());
                }
            });
            return row;
        });
    }

    public void init() {
        categoryTable.getItems().clear();
        ObservableList<EventCategory> l = FXCollections.observableList(st.AfficherCategoryEvent());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTable.getItems().addAll(l);
    }

    @FXML
    private void addCategoryAction(ActionEvent event) {
        tc.setName(name.getText());
        st.AddEventCategory(tc);
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
        EventCategory eventc = null;
        eventc = categoryTable.getSelectionModel().getSelectedItem();
        tc.setCatId(eventc.getCatId());
        tc.setName(name.getText());
        st.updateCategoryEvent(tc.getCatId(), tc);
        categoryTable.getItems().clear();
        init();
    }

    @FXML
    private void deleteCategoryAction(ActionEvent event) {
        EventCategory eventc = null;
        eventc = categoryTable.getSelectionModel().getSelectedItem();
        st.deleteCategoryEvent(eventc.getCatId());
        categoryTable.getItems().clear();
        init();
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showOpenDialog(null);
        imageTxt.setText(f.getName());
        tc.setImgUrl(f.getName());
    }

    @FXML
    private void RechercherCategories(MouseEvent event) throws SQLException {
        List<EventCategory> list = null;
        if (!"".equals(RechercheTF.getText())) {
            list = st.searchCategory(RechercheTF.getText());
        }
        if (list != null) {
            categoryTable.getItems().clear();
            ObservableList<EventCategory> l = FXCollections.observableList(list);
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            categoryTable.getItems().addAll(l);
        }
        else init();


    }

}
