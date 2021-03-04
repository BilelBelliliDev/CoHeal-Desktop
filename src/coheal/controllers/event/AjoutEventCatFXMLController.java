/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.event;

import coheal.entities.event.EventCategory;
import coheal.services.event.ServiceCategory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutEventCatFXMLController implements Initializable {
boolean canModify = true;

    @FXML
    private TextField tfN;
    @FXML
    private TextField tfIm;
    @FXML
    private TableColumn<EventCategory, String> cl1;
    @FXML
    private TableColumn<EventCategory, String> cl2;
    @FXML
    private TableView<EventCategory> tableV;

     private ServiceCategory sE = new ServiceCategory();
    @FXML
    private TableColumn<EventCategory, Integer> cl3;
     private int selectedId ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cl1.setCellValueFactory(new PropertyValueFactory<>("name"));
        cl2.setCellValueFactory(new PropertyValueFactory<>("imgUrl"));
        cl3.setCellValueFactory(new PropertyValueFactory<>("catId"));
        tableV.setItems((ObservableList<EventCategory>) sE.AfficherCategoryEvent());

        tableV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            @SuppressWarnings("empty-statement")
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableV.getSelectionModel().getSelectedItem() != null) {
                    EventCategory selectedEventCategory = tableV.getSelectionModel().getSelectedItem();
                    tfN.setText(selectedEventCategory.getName());
                  
                    // tfIm.setText(selectedEventCategory.getImgUrl());
                    selectedId = selectedEventCategory.getCatId();
                    canModify = true; 
                }
                
            }
            
        });

    }

    @FXML
    private void add(ActionEvent event) {
                EventCategory c = new EventCategory();
       // e.setUserId(1);
        c.setName(tfN.getText());
        c.setImgUrl(tfIm.getText());
 
//        String m="+selectedEvent.getPrice()";
//         e.setPrice(tfprice.getText());
        
        

        //e.setLocation(tflocation.getText());
        sE.AddEventCategory(c);
        tableV.setItems((ObservableList<EventCategory>) sE.AfficherCategoryEvent());
    }

//    @FXML
//    private void ModifierCategory(ActionEvent event) {
//        boolean canModify = false;
//                if (canModify) {
//            EventCategory c = new EventCategory();
//            c.setName(tfN.getText());
//            c.setImgUrl(tfIm.getText());
//
//            sE.updateEventCategory(selectedId, c);
//            tableV.setItems(sE.AfficherEventCategory());
//        }
//        else
//            System.out.println("can't modify please select an item form the table");
//    }
//
//    }

    @FXML
    private void DeleteCategory(ActionEvent event) {
          
                
        sE.deleteCategoryEvent(selectedId);
        tableV.setItems((ObservableList<EventCategory>) sE.AfficherCategoryEvent());
        
            //    tableV.setItems(sE.AfficherCategoryEvent());
          
        }

    @FXML
    private void ModifierCategory(ActionEvent event) {
              
                if (canModify) {
            EventCategory c = new EventCategory();
            c.setName(tfN.getText());
            c.setImgUrl(tfIm.getText());

            sE.updateCategoryEvent(selectedId, c);
           tableV.setItems((ObservableList<EventCategory>) sE.AfficherCategoryEvent());
        }
        else
            System.out.println("can't modify please select an item form the table");
    }
}
