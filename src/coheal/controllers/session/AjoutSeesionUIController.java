package coheal.controllers.session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AjoutSeesionUIController implements Initializable {

    @FXML
    private TextField titleId;
    @FXML
    private TextArea descriptionId;
    @FXML
    private TextField d;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void ajouterSession(ActionEvent event) {
//        ServiceSessionChat sc = new ServiceSessionChat();
//
//        Session s = new Session();
//
//        s.setTitle(titleId.getText());
//        s.setDescription(d.getText());
//        int n = Integer.parseInt(numberofdaysId.getText());
//        s.setNumOfDays(n);
//        s.setTherpId(1);
//        ss.createSession(s);
    }
    
}
