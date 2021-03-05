/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import entites.book;
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
import service.Servicebook;

/**
 * FXML Controller class
 *
 * @author Marwen
 */
public class RestrecherhceController implements Initializable {

        @FXML
        private TableView<book> vrecherche;
        @FXML
        private TableColumn<book, Integer> vrid;
        @FXML
        private TableColumn<book, String> vrtitre;
        @FXML
        private TextField tfrecherchebook;
        @FXML
        private TableColumn<book, String> vrimage;
        @FXML
        private TableColumn<book, String> vrauteur;
        @FXML
        private TableColumn<book, String> vrdesc;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                   try{
                     Servicebook sb=new Servicebook();
                      vrimage.setCellValueFactory(new PropertyValueFactory <book , String>("imgUrl") );
                      vrtitre.setCellValueFactory(new PropertyValueFactory <book , String>("title") );
                       vrauteur.setCellValueFactory(new PropertyValueFactory <book , String>("author") );
                        vrdesc.setCellValueFactory(new PropertyValueFactory <book , String>("description") );
                        vrid.setCellValueFactory(new PropertyValueFactory <book , Integer>("bookId") );
                        
                        vrecherche.setItems(sb.Try());}
                catch(SQLException ex){
                 Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }        

        @FXML
        private void recherhcebook(ActionEvent event) {
                 try{
                     Servicebook sb=new Servicebook();
                      vrimage.setCellValueFactory(new PropertyValueFactory <book , String>("imgUrl") );
                      vrtitre.setCellValueFactory(new PropertyValueFactory <book , String>("title") );
                       vrauteur.setCellValueFactory(new PropertyValueFactory <book , String>("author") );
                        vrdesc.setCellValueFactory(new PropertyValueFactory <book , String>("description") );
                        vrid.setCellValueFactory(new PropertyValueFactory <book , Integer>("bookId") );
                       int t=Integer.valueOf(tfrecherchebook.getText());
                      
                        
                        vrecherche.setItems(sb.Rechercher(t));}
                catch(SQLException ex){
                 Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

        @FXML
        private void retour6(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
                Parent root=loader.load();
                AfficherController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }
        
}
