/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.session;

import coheal.entities.session.Session;
import coheal.services.ui.UIService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class SessionItemController implements Initializable {

    @FXML
    private Label therpName;
    @FXML
    private Label sessionTitle;
    @FXML
    private Label sessionDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
    }    
    public void setData(Session s){
        UIService us = new UIService();
        sessionTitle.setText(s.getTitle());
        sessionDesc.setText(s.getDescription());
        try {
            System.out.println(us.therpSession(s.getTherpId()));
            therpName.setText(us.therpSession(s.getTherpId()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
