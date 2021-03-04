/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.book;

import coheal.entities.Book.Book;
import coheal.services.book.ServiceBook;
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
public class AfficherController implements Initializable {
           boolean canModify = false;
        private int selectedId;
        
        @FXML
        private TableView<Book> tabviewsbook;
        @FXML
        private TableColumn<Book, String> imaglivre;
        @FXML
        private TableColumn<Book, String> titrelivre;
        @FXML
        private TableColumn<Book, String> auteurlivre;
        @FXML
        private TableColumn<Book, String> desclivre;
        @FXML
        private TextField selectimage;
        @FXML
        private TextField selecttitre;
        @FXML
        private TextField selectauteur;
        @FXML
        private TextField selectdescription;
        @FXML
        private TableColumn<Book, Integer> livId;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                try{
                     ServiceBook sb=new ServiceBook();
                      imaglivre.setCellValueFactory(new PropertyValueFactory <Book , String>("imgUrl") );
                      titrelivre.setCellValueFactory(new PropertyValueFactory <Book , String>("title") );
                       auteurlivre.setCellValueFactory(new PropertyValueFactory <Book, String>("author") );
                        desclivre.setCellValueFactory(new PropertyValueFactory <Book , String>("description") );
                        livId.setCellValueFactory(new PropertyValueFactory <Book , Integer>("bookId") );
                        
                        tabviewsbook.setItems(sb.AfficherBook2());}
                catch(SQLException ex){
                 Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tabviewsbook.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tabviewsbook.getSelectionModel().getSelectedItem() != null) {
                    Book selectedBook = tabviewsbook.getSelectionModel().getSelectedItem();
                    selecttitre.setText(selectedBook.getTitle());
                    selectdescription.setText(selectedBook.getDescription());
                    selectauteur.setText(selectedBook.getAuthor());
                    selectimage.setText(selectedBook.getImgUrl());
                    
                  
                    selectedId = selectedBook.getBookId();
                    canModify = true;
                    
                }
            }
        });
        }        

        @FXML
        private void supprimeract(ActionEvent event) {
                ServiceBook sb=new ServiceBook();
                System.out.println(selectedId);
                 if (canModify) {
        sb.supprimerBook(selectedId);
        try {
              tabviewsbook.setItems(sb.AfficherBook2());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            System.out.println("can't delete please select an item form the table");
        }

        @FXML
        private void modifieract(ActionEvent event) {
                  if (canModify) {
            Book b = new Book();
            ServiceBook sb=new ServiceBook();
            b.setImgUrl(selectimage.getText());
            b.setTitle(selecttitre.getText());
            b.setAuthor(selectauteur.getText());
            b.setDescription(selectdescription.getText());
            sb.modifierBook(selectedId, b);
            try {
                tabviewsbook.setItems(sb.AfficherBook2());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            System.out.println("can't modify please select an item form the table");
        }

        @FXML
        private void retour1(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("accueil.fxml"));
                Parent root=loader.load();
                AccueilController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }
}
