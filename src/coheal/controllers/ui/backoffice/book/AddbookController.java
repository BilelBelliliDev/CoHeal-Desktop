/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.book;

import coheal.controllers.ui.frontoffice.book.*;
import coheal.entities.book.Book;
import coheal.entities.task.Notification;
import static coheal.services.book.Constants.projectPath;
import coheal.services.book.ServiceBook;
import coheal.services.book.ServiceBookCategory;
import coheal.services.book.notifBook;
import coheal.services.task.ServiceNotification;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXComboBox;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
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
         ServiceBookCategory sbc = new ServiceBookCategory();
        int cat_id = 0;
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
        @FXML
        private JFXComboBox<String> bcat;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                 try {
                        for (int i = 0; i < sbc.AfficherBookCat().size(); i++) {
                                bcat.getItems().add(sbc.AfficherBookCat().get(i).getName());
                        }
                } catch (SQLException ex) {
                        Logger.getLogger(coheal.controllers.ui.frontoffice.book.AddbookController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

        @FXML
        private void closeAction(MouseEvent event) {
                Stage stage = new Stage();
                stage = (Stage) tfdescription.getScene().getWindow();
                stage.close();
        }

        @FXML
        private void addBokkAction(ActionEvent event) throws IOException {
                ServiceBook sb = new ServiceBook();
                Book b = new Book();
                 b.setCatId(cat_id);
                b.setUserId(UserSession.getUser_id());
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
                 QRcode();

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

          private void QRcode() throws FileNotFoundException, IOException {
                String contenue = "Titre: " + tftiltle.getText() + "\n" + "Auteur: " + tfauthor.getText() + "\n" + "Description: " + tfdescription.getText();
                ByteArrayOutputStream out = QRCode.from(contenue).to(ImageType.JPG).stream();
                File f = new File(projectPath + "\\src\\coheal\\resources\\images\\QRBook\\" + tftiltle.getText() + ".jpg");
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(out.toByteArray());
                fos.flush();

        }

        @FXML
        private void bCat(ActionEvent event) throws SQLException {
                                String cat = bcat.getValue();
                cat_id = sbc.RechercheCatID(cat).get(0).getCatId();
                System.out.println(cat_id);
        }
        public void notif() {

                // notif accepté 
                notifBook service=new notifBook();
        Notification n = new Notification();
        n.setId(UserSession.getUser_id());
        n.setMessage(UserSession.getFirst_name()+" "+UserSession.getLast_name()+" a ajouter un livre "+tftiltle.getText());
        service.addNotification(n);

        }

}
