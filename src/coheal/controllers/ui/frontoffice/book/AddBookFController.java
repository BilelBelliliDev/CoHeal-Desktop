/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import coheal.controllers.ui.frontoffice.LoginController;
import coheal.controllers.ui.frontoffice.recipe.*;
import coheal.controllers.ui.frontoffice.task.*;
import coheal.entities.book.Book;
import coheal.entities.recipe.Recipe;
import coheal.entities.task.Notification;
import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import static coheal.services.book.Constants.projectPath;
import coheal.services.book.ServiceBook;
import coheal.services.book.ServiceBookCategory;
import coheal.services.book.notifBook;
import coheal.services.recipe.RecipeCategoryService;
import coheal.services.recipe.RecipeService;
import coheal.services.task.ServicePaidTask;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceTaskCategory;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class AddBookFController implements Initializable {

    File file = null;
    File file2 = null;
    Desktop desktop = Desktop.getDesktop();
    FileChooser fileChooser = new FileChooser();
    ServiceBookCategory sbc = new ServiceBookCategory();
    int cat_id = 0;
    private boolean aut = false, tit = false, dd = false;

    @FXML
    private FontAwesomeIconView close1;
    @FXML
    private ImageView image;
    @FXML
    private JFXTextField tftiltle;
    @FXML
    private JFXTextArea tfdescription;
    @FXML
    private JFXTextField tfauthor;
    @FXML
    private ImageView image1;
    @FXML
    private JFXComboBox<String> bcat;
    @FXML
    private Label tffilepath;
    @FXML
    private Label tfimgurl;
    @FXML
    private Label labelBook;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AuthorValidator();
        TitleValidator();
        DescValidator();
        try {
            for (int i = 0; i < sbc.AfficherBookCat().size(); i++) {
                bcat.getItems().add(sbc.AfficherBookCat().get(i).getName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddbookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close1.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void Addimage(MouseEvent event) {
        Stage stage = null;

        file = fileChooser.showOpenDialog(stage);
        Image img = new Image("file:///"+file);
        image.setImage(img);
        
        tfimgurl.setText(file.getName());
    }

    @FXML
    private void Addlivre(MouseEvent event) {
       Stage stage2 = null;
        file2 = fileChooser.showOpenDialog(stage2);
        //Image img = new Image("file:///"+file2);
        //image1.setImage(img);
        labelBook.setVisible(false);
        tffilepath.setText(file2.getName());
    }

    public void notif() {

        // notif accepté 
        notifBook service = new notifBook();
        Notification n = new Notification();
        n.setId(UserSession.getUser_id());
        n.setMessage(UserSession.getFirst_name() + " " + UserSession.getLast_name() + " a ajouter un livre " + tftiltle.getText());
        service.addNotification(n);

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

    public void AuthorValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        tfauthor.setValidators(valid);
        valid.setMessage("Please enter a valid author name");
        tfauthor.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    tfauthor.validate();
                    if (tfauthor.validate()) {
                        aut = true;
                    } else {
                        aut = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TitleValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        tftiltle.setValidators(valid);
        valid.setMessage("Please enter a valid title");
        tftiltle.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    tftiltle.validate();
                    if (tftiltle.validate()) {
                        tit = true;
                    } else {
                        tit = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DescValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        tfdescription.setValidators(valid);
        valid.setMessage("Please enter a valid description");
        tfdescription.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    tfdescription.validate();
                    if (tfdescription.validate()) {
                        dd = true;
                    } else {
                        dd = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close1.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void addBokkAction(MouseEvent event) throws IOException {
        if (aut && tit && dd) {
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
                Logger.getLogger(AddbookController.class.getName()).log(Level.SEVERE, null, ex);
            }
            sb.AjouterBook(b);
            notif();
            QRcode();
        }
    }

}
