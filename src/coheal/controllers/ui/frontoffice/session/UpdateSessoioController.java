/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.session;

import coheal.entities.session.Session;
import coheal.services.session.ServiceSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class UpdateSessoioController implements Initializable {

    @FXML
    private TextField titreid;
    @FXML
    private TextArea desid;
    @FXML
    private TextField numdaysid;
 SessionHolder tah=SessionHolder.getINSTANCE();
        Session s;

    ServiceSession ss = new ServiceSession();
    public Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //TODO
        s=ss.searchSession(tah.getId());
         s.setTitle(titreid.getText());
        s.setDescription(numdaysid.getText());
      
    }    

    @FXML
    private void updateid(ActionEvent event) {
         Session s = new Session();

        s.setTitle(titreid.getText());
        s.setDescription(desid.getText());
        int n = Integer.parseInt(numdaysid.getText());
        s.setNumOfDays(n);
        s=ss.searchSession(tah.getId());

        
        ss.modifierSession(s, tah.getId());
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) numdaysid.getScene().getWindow();
        stage.close();
        
    }
    
    
}
