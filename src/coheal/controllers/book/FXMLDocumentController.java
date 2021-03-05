/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.book;

import coheal.entities.book.Book;
import coheal.services.book.ServiceBook;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Marwen
 */
public class FXMLDocumentController implements Initializable {
         @FXML
        private Label label;
        
        @FXML
        private TextField tfimgurl;
        @FXML
        private TextField tffilepath;
        @FXML
        private TextField tfauthor;
        @FXML
        private TextField tftiltle;
        @FXML
        private TextField tfdescription;
        
        
        private void handleButtonAction(ActionEvent event) {
                System.out.println("You clicked me!");
                label.setText("Hello World!");
        }
        
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                // TODO
        }        

        @FXML
        private void ajouterbook(ActionEvent event) {
                ServiceBook sb=new ServiceBook();
                Book b=new Book();
                b.setCatId(1);
                b.setUserId(1);
                b.setImgUrl(tfimgurl.getText());
                b.setFilePath(tffilepath.getText());
                b.setAuthor(tfauthor.getText());
                b.setTitle(tftiltle.getText());
                b.setDescription(tfdescription.getText());
                String n1=tfauthor.getText();
                String n2=tftiltle.getText();
                //System.out.println(n2 instanceof String);
                if((n1.length()==0)||(n1.length()>10)){    
                 Alert a1= new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Alert");
                a1.setHeaderText("Alert (saisie) ");
                a1.setContentText("le nom de l'auteur doit etre compris entre 0 et 10 lettres");
                a1.showAndWait();}
                if((n2.length()==0)||(n2.length()>10)){    
                  Alert a1= new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Alert");
                a1.setHeaderText("Alert (saisie) ");
                a1.setContentText("le titre doit etre compris entre 0 et 10 lettres");
                a1.showAndWait();}
                else{
                          Alert a1= new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("info");
                a1.setHeaderText("livre ajouter avec succès");
                
                a1.showAndWait();
                sb.AjouterBook(b);}
                //sb.AjouterBook(b);
                
        }
    FileChooser fileChooser = new FileChooser();
    File file;
        @FXML
        private void AddimageBook(ActionEvent event) {
                  Stage stage = null;
                   
      
        fileChooser.setTitle("View Pictures");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
         file = fileChooser.showSaveDialog(stage);
        System.out.println(file.getAbsolutePath());
        tfimgurl.setText(file.getAbsolutePath());
            
        }

        @FXML
        private void addlivre(ActionEvent event) {
         
                
                       Stage stage = null;
                   
      
        fileChooser.setTitle("View files");
               fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
                
       
         file = fileChooser.showSaveDialog(stage);
        System.out.println(file.getAbsolutePath());
        tffilepath.setText(file.getAbsolutePath());
                
                
                
        }

        @FXML
        private void retour2(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/book/accueil.fxml"));
                Parent root=loader.load();
                AccueilController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

}
