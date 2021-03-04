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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Marwen
 */
public class AjouterCategoryBookController implements Initializable {
        @FXML
        private TextField tfimagecat;
        @FXML
        private TextField tfnomcat;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                // TODO
        }        

        @FXML
        private void ajoutercat(ActionEvent event) {
                 ServiceBookCategory sbc=new ServiceBookCategory();
                BookCategory bc=new BookCategory();
//                int num;
//                num=Integer.parseInt(tfidcat.getText());
//                bc.setCatId(num);
                bc.setName(tfnomcat.getText());
                bc.setImgUrl(tfimagecat.getText());
                sbc.AjouterBookCategory(bc);
        }

        @FXML
        private void retour3(ActionEvent event) throws IOException {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("accueil.fxml"));
                Parent root=loader.load();
                AccueilController s2 = loader.getController();
                Stage stage =new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

        
}
