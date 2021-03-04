/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.session;

import coheal.entities.session.SessionMessage;
import coheal.services.session.ServiceSessionMessage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class SessionMessageFXMLController implements Initializable {
@FXML
    private Label msgEnvoyer;
    @FXML
    private TextArea messageId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EnvoyerAction(ActionEvent event) {
       
        ServiceSessionMessage sem=new ServiceSessionMessage();
        
        SessionMessage sm=new SessionMessage();
       
        sm.setMsg(messageId.getText());
        sm.setChatId(1);
        sem.createSessionMesage( sm);
        
    }

    @FXML
    private void afficherMessageAction(ActionEvent event) {
        ServiceSessionMessage sm = new ServiceSessionMessage();
        msgEnvoyer.setText(sm.listMessage().toString());
                
    }
    
}
