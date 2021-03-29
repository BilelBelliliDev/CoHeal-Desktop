/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.session;

import coheal.entities.session.SessionMessage;
import coheal.services.session.ServiceSessionMessage;
import coheal.services.user.UserSession;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class SessionMessageController implements Initializable {

    @FXML
    private Pane textId;
    @FXML
    private TextField msgId;
    private Timer timer = new Timer();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerMessageAction(ActionEvent event) {
         ServiceSessionMessage sem=new ServiceSessionMessage();
        
        SessionMessage sm=new SessionMessage();
       
        sm.setMsg(msgId.getText());
         sm.setUserId(UserSession.getUser_id());

        sm.setChatId(1);
        sem.createSessionMesage(sm);
        
        
    }
    
    
}
