package coheal.controllers.user;

import coheal.entities.user.User;
import coheal.services.user.ServiceUser;
import coheal.services.user.UserSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author wajdi's pc
 */
public class ModifyDeleteController implements Initializable {

    @FXML
    private TextField TFEmail;
    @FXML
    private TextField TFPassword;
    @FXML
    private TextField TFFirstName;
    @FXML
    private TextField TFLastName;
    @FXML
    private TextField TFid;
    @FXML
    private DatePicker TFdateOfBirth;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierUser(ActionEvent event) {
        ServiceUser sp= new ServiceUser();
        User u =new User();        
            //sets
            u.setEmail(TFEmail.getText());        
            u.setPassword(TFPassword.getText());
            u.setFirstName(TFFirstName.getText());
            u.setLastName(TFLastName.getText());
            u.setDateOfBirth(java.sql.Date.valueOf(TFdateOfBirth.getValue()));
            
            sp.ModifierUser(u,UserSession.getUser_id());
    }

    @FXML
    private void DeleteAccount(ActionEvent event) {
        ServiceUser sp= new ServiceUser();
        System.err.println(UserSession.getUser_id());
        sp.DeleteUser(UserSession.getUser_id());
    }
    
}
