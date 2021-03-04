/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.book;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/book/FXMLDocument.fxml"));
                Parent root=loader.load();
                FXMLDocumentController s2 = loader.getController();
                Stage stage =new Stage();
                
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                
        }

        @FXML
        private void appelaffiche(ActionEvent event)throws IOException{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/book/afficher.fxml"));
                Parent root=loader.load();
                AfficherController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
           
        }

        @FXML
        private void appelcatadd(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/book/ajouterCategoryBook.fxml"));
                Parent root=loader.load();
                AjouterCategoryBookController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

        @FXML
        private void appelcataff(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/book/afficherCategory.fxml"));
                Parent root=loader.load();
                AfficherCategoryController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

       
        
}
