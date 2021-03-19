/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.book;


import coheal.entities.book.Book;
import coheal.services.book.ServiceBook;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * FXML Controller class
 *
 * @author Marwen
 */
public class RestrecherhceController implements Initializable {

        @FXML
        private TableView<Book> vrecherche;
        @FXML
        private TableColumn<Book, Integer> vrid;
        @FXML
        private TableColumn<Book, String> vrtitre;
        @FXML
        private TextField tfrecherchebook;
        @FXML
        private TableColumn<Book, String> vrimage;
        @FXML
        private TableColumn<Book, String> vrauteur;
        @FXML
        private TableColumn<Book, String> vrdesc;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {

                   try{
                     ServiceBook sb=new ServiceBook();
                      vrimage.setCellValueFactory(new PropertyValueFactory <Book , String>("imgUrl") );
                      vrtitre.setCellValueFactory(new PropertyValueFactory <Book , String>("title") );
                       vrauteur.setCellValueFactory(new PropertyValueFactory <Book , String>("author") );
                        vrdesc.setCellValueFactory(new PropertyValueFactory <Book , String>("description") );
                        vrid.setCellValueFactory(new PropertyValueFactory <Book , Integer>("bookId") );
                        
                        vrecherche.setItems(sb.Try());}
                catch(SQLException ex){
                 Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
                }

        }        

        @FXML
        private void recherhcebook(ActionEvent event) {

                 try{
                     ServiceBook sb=new ServiceBook();
                      vrimage.setCellValueFactory(new PropertyValueFactory <Book , String>("imgUrl") );
                      vrtitre.setCellValueFactory(new PropertyValueFactory <Book , String>("title") );
                       vrauteur.setCellValueFactory(new PropertyValueFactory <Book , String>("author") );
                        vrdesc.setCellValueFactory(new PropertyValueFactory <Book , String>("description") );
                        vrid.setCellValueFactory(new PropertyValueFactory <Book , Integer>("bookId") );
                       int t=Integer.valueOf(tfrecherchebook.getText());
                      
                        
                        vrecherche.setItems(sb.Rechercher(t));}
                catch(SQLException ex){
                 Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
                }

        }

        @FXML
        private void retour6(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/book/afficher.fxml"));
                Parent root=loader.load();
                AfficherController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }
        
}
