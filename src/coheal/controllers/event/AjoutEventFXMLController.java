/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.event;

import coheal.entities.event.Event;
import coheal.services.event.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutEventFXMLController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfdescription;
//    @FXML
//    private TextField tfstarttime;
//    @FXML
//    private TextField tfendtime;

    private TextField tftype;
    @FXML
    private Label Laffiche;
    @FXML

    private DatePicker stId;
    @FXML
    private DatePicker edId;
    @FXML
    private TextField tfloca;
    @FXML
    private TableView<Event> tableId;
    @FXML
    private TableColumn<Event, Integer> col1Id;
    @FXML
    private TableColumn<Event, String> col2Id;
    @FXML
    private TableColumn<Event, String> col3Id;
    @FXML
    private TableColumn<Event, java.sql.Date> col4Id;
    @FXML
    private TableColumn<Event, java.sql.Date> col5Id;
    @FXML
    private TableColumn<Event, String> col6Id;

    private ServiceEvent se = new ServiceEvent();
    private int selectedId;
    boolean canModify = false;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private TextField tfprice;
//    @FXML
//    private TableColumn<?, ?> col7Id1;
    @FXML
    private TableColumn<?, ?> col7Id1;
   
   

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            col1Id.setCellValueFactory(new PropertyValueFactory<>("eventId"));
            col2Id.setCellValueFactory(new PropertyValueFactory<>("title"));
            col3Id.setCellValueFactory(new PropertyValueFactory<>("description"));
            col4Id.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            col5Id.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            col6Id.setCellValueFactory(new PropertyValueFactory<>("location"));
            tableId.setItems(se.AfficherEvent());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableId.getSelectionModel().getSelectedItem() != null) {
                    Event selectedEvent = tableId.getSelectionModel().getSelectedItem();
                    tftitle.setText(selectedEvent.getTitle());
                    tfdescription.setText(selectedEvent.getDescription());
                    stId.setValue(selectedEvent.getStartDate().toLocalDate());
                    edId.setValue(selectedEvent.getEndDate().toLocalDate());
                    tfloca.setText(selectedEvent.getLocation());
                    if(selectedEvent.getType() == "free"){
                        radio1.setSelected(true);
                    }
                else{
                        radio2.setSelected(true);
                    }
                    selectedId = selectedEvent.getEventId();
                    canModify = true;
                }
            }
        });

    }

    @FXML
    private void add(ActionEvent event) {

        Event e = new Event();
        e.setUserId(1);
        e.setTitle(tftitle.getText());
        e.setDescription(tfdescription.getText());
        e.setStartDate(java.sql.Date.valueOf(stId.getValue()));
        e.setEndDate(java.sql.Date.valueOf(stId.getValue()));
        e.setLocation(tfloca.getText());
        e.setType(((RadioButton)type.getSelectedToggle()).getText());

        //e.setLocation(tflocation.getText());
        se.AddEvent(e);
        try {
            tableId.setItems(se.AfficherEvent());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

// 
//  FileChooser fileChooser = new FileChooser();
//  File file;
//
//    @FXML
//    private void Addimageevent(ActionEvent event) {
//                Stage stage = null;
//      
//        fileChooser.setTitle("View Pictures");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("All Images", "*.*"),
//                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
//                new FileChooser.ExtensionFilter("GIF", "*.gif"),
//                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
//                new FileChooser.ExtensionFilter("PNG", "*.png")
//        );
//         file = fileChooser.showSaveDialog(stage);
//       System.out.println(file.getName());
//  }
    @FXML
    private void modifierEvent(ActionEvent event) {
        if (canModify) {
            Event e = new Event();
            e.setTitle(tftitle.getText());
            e.setDescription(tfdescription.getText());
            e.setStartDate(java.sql.Date.valueOf(stId.getValue()));
            e.setEndDate(java.sql.Date.valueOf(edId.getValue()));
            e.setLocation(tfloca.getText());
            e.setType(((RadioButton)type.getSelectedToggle()).getText());
            se.updateEvent(selectedId, e);
            try {
                tableId.setItems(se.AfficherEvent());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else
            System.out.println("can't modify please select an item form the table");
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        if (canModify) {
        se.deleteEvent(selectedId);
        try {
                tableId.setItems(se.AfficherEvent());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else
            System.out.println("can't delete please select an item form the table");
    }

    @FXML
    private void moveToCategory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/event/AjoutEventCatFXML.fxml"));
                Parent root=loader.load();
                AjoutEventCatFXMLController s2 = loader.getController();
                Stage stage =new Stage();
                
                stage.setScene(new Scene(root));
                stage.show();
    }
}
