/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.session;

import coheal.entities.session.Session;
import coheal.services.session.ServiceSession;
import coheal.services.session.ServiceSessionChat;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */

  public class SessionFXMLController implements Initializable {

    @FXML
    private TextField titleId;
    @FXML
    private Text descriptionId;
    @FXML
    private TextArea d;
    @FXML
    private Label SessionList;
    @FXML
    private TextField numberofdaysId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterSession(ActionEvent event) {
        
        ServiceSession se=new ServiceSession();
        ServiceSessionChat sc=new ServiceSessionChat();
        
        Session s=new Session();
        
        s.setTitle(titleId.getText());
        s.setDescription(d.getText());
        int n = Integer.parseInt(numberofdaysId.getText());
        s.setNumOfDays(n);
        s.setTherpId(1);
        se.createSession(s);
        
        
        
    }

    @FXML
    private void AfficherSession(ActionEvent event) {
         ServiceSession sp=new ServiceSession();
         SessionList.setText(sp.listSesion().toString());
        
    }

    

    @FXML
    private void MessageShow(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/session/SessionMessageFXML.fxml"));
            Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void ModifierAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/session/UpdateUIController.fxml"));
            Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void supprimerSessionAction(ActionEvent event) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/sessionDeleteFXML.fxml"));
            Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
