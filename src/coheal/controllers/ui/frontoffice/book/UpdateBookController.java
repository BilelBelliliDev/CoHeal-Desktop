/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import coheal.controllers.ui.frontoffice.LoginController;
import coheal.entities.book.Book;
import coheal.services.book.ServiceBook;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marwen
 */
public class UpdateBookController implements Initializable {
        
        private boolean at = false, tt = false,dd = false;
        String tit = null;
        String aut = null;
        String desc = null;
        int n=0;
        @FXML
        private JFXTextField tfauthor;
        @FXML
        private JFXTextField tftiltle;
        @FXML
        private JFXTextField tfdescription;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                AuthorValidator();
                TitleValidator();
                DescValidator();
        }        

        public void display(String t, String a, String d,int id) {
                tftiltle.setText(t);
                tfauthor.setText(a);
                tfdescription.setText(d);
                tit = t;
                aut = a;
                desc = d;
                n=id;
        }
        
        @FXML
        private void updateact(ActionEvent event) {
                if(at && tt && dd){
                  Book b = new Book();
                        ServiceBook sb = new ServiceBook();
                        b.setTitle(tftiltle.getText());
                        b.setAuthor( tfauthor.getText());
                        b.setDescription(tfdescription.getText());
                        sb.modifierBook2(n, b);}
                        
                       
        }
        
        @FXML
        private void closeAction(MouseEvent event) {
                 Stage stage = new Stage();
                stage = (Stage) tfdescription.getScene().getWindow();
                stage.close();
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
                                                at = true;
                                        } else {
                                                at = false;
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
                                                tt = true;
                                        } else {
                                                tt = false;
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
        
}
