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
public class AccueilController implements Initializable {
        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                // TODO
        }        

        @FXML
        private void appelajout(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                Parent root=loader.load();
                FXMLDocumentController s2 = loader.getController();
                Stage stage =new Stage();
                
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                
        }

        @FXML
        private void appelaffiche(ActionEvent event)throws IOException{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
                Parent root=loader.load();
                AfficherController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
           
        }

        @FXML
        private void appelcatadd(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterCategoryBook.fxml"));
                Parent root=loader.load();
                AjouterCategoryBookController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

        @FXML
        private void appelcataff(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherCategory.fxml"));
                Parent root=loader.load();
                AfficherCategoryController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

       
        
}
