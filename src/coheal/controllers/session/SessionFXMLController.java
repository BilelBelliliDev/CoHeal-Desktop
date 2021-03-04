/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.session;

import coheal.entities.event.EventCategory;
import coheal.entities.session.Session;
import coheal.services.session.ServiceSession;
import coheal.services.session.ServiceSessionChat;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private TableColumn<Session, Integer> col1Id;
    @FXML
    private TableColumn<Session, Integer> col2Id;
    @FXML
    private TableColumn<Session, String> col3Id;
    @FXML
    private TableColumn<Session, String> col4Id;
    @FXML
    private TableColumn<Session, Integer> col5Id;
    @FXML
    private TableView<Session> tableId;
    private ServiceSession ss=new ServiceSession();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            col1Id.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
            col2Id.setCellValueFactory(new PropertyValueFactory<>("therpId"));
            col3Id.setCellValueFactory(new PropertyValueFactory<>("title"));
            col4Id.setCellValueFactory(new PropertyValueFactory<>("description"));
            col5Id.setCellValueFactory(new PropertyValueFactory<>("numOfDays"));
            tableId.setItems((ObservableList<Session>) ss.listSesion());
            
    }    

    @FXML
    private void ajouterSession(ActionEvent event) {
        
        
        ServiceSessionChat sc=new ServiceSessionChat();
        
        Session s=new Session();
        
        s.setTitle(titleId.getText());
        s.setDescription(d.getText());
        int n = Integer.parseInt(numberofdaysId.getText());
        s.setNumOfDays(n);
        s.setTherpId(1);
        ss.createSession(s);
        tableId.setItems((ObservableList<Session>) ss.listSesion());
        
        
    }

    @FXML
    private void AfficherSession(ActionEvent event) {
         tableId.setItems((ObservableList<Session>) ss.listSesion());
        
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
            root = FXMLLoader.load(getClass().getResource("/coheal/views/session/DeleteFXML.fxml"));
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