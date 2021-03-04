/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.book;

import coheal.entities.Book.BookCategory;
import coheal.services.book.ServiceBookCategory;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Marwen
 */
public class AfficherCategoryController implements Initializable {
        private int selectedId;
       private boolean canModify;
        

        @FXML
        private TableView<BookCategory> tabviewcat;
        @FXML
        private TableColumn<BookCategory, Integer> colid;
        @FXML
        private TableColumn<BookCategory, String> nomcol;
        @FXML
        private TableColumn<BookCategory, String> imagecol;
        @FXML
        private TextField selectednomcat;
        @FXML
        private TextField selectedimagecat;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                try{
                     ServiceBookCategory sbc=new ServiceBookCategory();
                      colid.setCellValueFactory(new PropertyValueFactory <BookCategory , Integer>("catId") );
                      nomcol.setCellValueFactory(new PropertyValueFactory <BookCategory , String>("name") );
                       imagecol.setCellValueFactory(new PropertyValueFactory <BookCategory, String>("imgUrl") );
                        
                        
                        tabviewcat.setItems(sbc.AfficherBookCat());}
                catch(SQLException ex){
                 Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tabviewcat.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tabviewcat.getSelectionModel().getSelectedItem() != null) {
                    BookCategory selectedBook = tabviewcat.getSelectionModel().getSelectedItem();
                    selectednomcat.setText(selectedBook.getName());
                    selectedimagecat.setText(selectedBook.getImgUrl());
                    
                   
                    
                  
                    selectedId = selectedBook.getCatId();
                    canModify = true;
                        System.out.println(selectedId);
                    
                }
            }

                        
        });
        }        

        @FXML
        private void suppcat(ActionEvent event) {
                 ServiceBookCategory sbc=new   ServiceBookCategory();
                System.out.println(selectedId);
                 if (canModify) {
        sbc.supprimerBookCat(selectedId);
        try {
              tabviewcat.setItems(sbc.AfficherBookCat());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            System.out.println("can't delete please select an item form the table");
        }

        @FXML
        private void retour5(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("accueil.fxml"));
                Parent root=loader.load();
                AccueilController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

        @FXML
        private void modifiercat(ActionEvent event) {
                 if (canModify) {
            BookCategory bc = new BookCategory();
            ServiceBookCategory sbc=new ServiceBookCategory();
            bc.setName(selectednomcat.getText());
            bc.setImgUrl(selectedimagecat.getText());
           
            sbc.modifierBookCat(selectedId, bc);
            try {
                tabviewcat.setItems(sbc.AfficherBookCat());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            System.out.println("can't modify please select an item form the table");
        }
}
