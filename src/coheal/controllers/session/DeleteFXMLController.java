/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.session;

import coheal.entities.session.Session;
import coheal.services.session.ServiceSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class DeleteFXMLController implements Initializable {

    private TextField deleteText;
    @FXML
    private TextField sessionId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void supprimerAction(ActionEvent event) {
        ServiceSession se=new ServiceSession();
        Session s=new Session();
        int n = Integer.parseInt(sessionId.getText());
        
        se.SupprimerSession(n );
    }
    
}
