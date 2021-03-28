/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.book;

import coheal.entities.book.BookCategory;
import static coheal.services.book.Constants.projectPath;
import coheal.services.book.ServiceBookCategory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Marwen
 */
public class AddCatController implements Initializable {

        @FXML
        private TextField tfimagecat;
        @FXML
        private TextField tfnomcat;
        File file = null;
        FileChooser fileChooser = new FileChooser();

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                // TODO
        }

        @FXML
        private void addBokkAction(ActionEvent event) {
                ServiceBookCategory sbc = new ServiceBookCategory();
                BookCategory bc = new BookCategory();
                bc.setName(tfnomcat.getText());
                bc.setImgUrl(tfimagecat.getText());
                String n = tfnomcat.getText();

                if ((n.length() == 0) || (n.length() > 10)) {
                        Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                        a1.setTitle("Alert");
                        a1.setHeaderText("Alert (saisie) ");
                        a1.setContentText("le nom doit etre compris entre 0 et 10 lettres");
                        a1.showAndWait();
                } else {
                        Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                        a1.setTitle("info");
                        a1.setHeaderText("catégorie ajouter avec succès");
                        a1.showAndWait();
                         File dest = new File(projectPath + "\\src\\coheal\\resources\\images\\bookCat\\" + file.getName());
                try {
                        FileUtils.copyFile(file, dest);
                } catch (IOException ex) {
                        Logger.getLogger(AddbookController.class.getName()).log(Level.SEVERE, null, ex);
                }
                        sbc.AjouterBookCategory(bc);
                }
        }

        @FXML
        private void closeAction(MouseEvent event) {
                Stage stage = new Stage();
                stage = (Stage) tfnomcat.getScene().getWindow();
                stage.close();
        }

        @FXML
        private void Addimage(MouseEvent event) {
                Stage stage = null;

                file = fileChooser.showOpenDialog(stage);

                tfimagecat.setText(file.getName());
        }

}
