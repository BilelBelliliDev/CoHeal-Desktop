/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import coheal.entities.book.Book;
import static coheal.services.book.Constants.projectPath;
import coheal.services.book.ServiceBook;
import coheal.services.user.UserSession;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Marwen
 */
public class AddbookController implements Initializable {

        File file = null;
        File file2 = null;
        Desktop desktop = Desktop.getDesktop();

        FileChooser fileChooser = new FileChooser();

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

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                // TODO
        }

        @FXML
        private void closeAction(MouseEvent event) {
                Stage stage = new Stage();
                stage = (Stage) tfdescription.getScene().getWindow();
                stage.close();
        }

        @FXML
        private void addBokkAction(ActionEvent event) {
                ServiceBook sb = new ServiceBook();
                Book b = new Book();
                b.setCatId(1);
                b.setUserId(1);
                b.setImgUrl(tfimgurl.getText());
                b.setFilePath(tffilepath.getText());
                b.setAuthor(tfauthor.getText());
                b.setTitle(tftiltle.getText());
                b.setDescription(tfdescription.getText());
                String n1 = tfauthor.getText();
                String n2 = tftiltle.getText();

                File dest = new File(projectPath + "\\src\\coheal\\resources\\images\\books\\" + file.getName());
                File dest2 = new File(projectPath + "\\src\\coheal\\resources\\images\\bookfiles\\" + file2.getName());

                try {
                        FileUtils.copyFile(file, dest);
                } catch (IOException ex) {
                        Logger.getLogger(AddbookController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                        FileUtils.copyFile(file2, dest2);
                } catch (IOException ex) {
                        Logger.getLogger(AddbookController.class.getName()).log(Level.SEVERE, null, ex);}
                sb.AjouterBook(b);
                 notif();

        }

        @FXML
        private void Addimage(MouseEvent event) {
                Stage stage = null;

                file = fileChooser.showOpenDialog(stage);

                tfimgurl.setText(file.getName());
        }

        @FXML
        private void Addlivre(MouseEvent event) {
                Stage stage2 = null;

                file2 = fileChooser.showOpenDialog(stage2);

                tffilepath.setText(file2.getName());
        }
        public void notif(){
                
            // notif accepté 
            Notifications notificationBuilder = Notifications.create()
                    .title("ajout")
                    .text("Felicitation "+UserSession.getFirst_name() +"! Votre livre a été ajouté avec succée")
                    .hideAfter(Duration.minutes(0.1))
                    .position(Pos.BOTTOM_RIGHT)
                    .darkStyle();
                  
            notificationBuilder.show();
        
        }

}
