/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.session;

import coheal.entities.session.Session;
import coheal.services.session.ServiceSession;
import coheal.services.session.ServiceSessionChat;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class AddSessionController implements Initializable {

    
    @FXML
    private TextArea descriptionId;
    @FXML
    private TextField numDays;
    private ServiceSession ss = new ServiceSession();
    @FXML
    private TextField price;
    @FXML
    private TextField titleId;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                //titleValidatorSI();

    }    

    @FXML
    private void addSessionAction(ActionEvent event) {
        ServiceSessionChat sc = new ServiceSessionChat();

        Session s = new Session();

        s.setTitle(titleId.getText());
        s.setDescription(descriptionId.getText());
        int n = Integer.parseInt(numDays.getText());
        s.setNumOfDays(n);
        int n1 = Integer.parseInt(price.getText());
        s.setPrice(n1);
        s.setTherpId(UserSession.getUser_id());
        ss.createSession(s);
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) numDays.getScene().getWindow();
        stage.close();
    }
     private static void AlertBox(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    
   //    
}
