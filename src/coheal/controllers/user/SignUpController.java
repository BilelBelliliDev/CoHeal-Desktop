package coheal.controllers.user ;

import coheal.entities.user.User;
import coheal.services.user.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wajdi's pc
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField TFFirstName;
    @FXML
    private TextField TFLastName;
    @FXML
    private DatePicker TFDateOfBirth;
    @FXML
    private TextField TFEmail;
    @FXML
    private TextField TFPassword;
    @FXML
    private TextField TFconfirmPassword;
    private Label afficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SingUp(ActionEvent event) throws SQLException {
        ServiceUser sp= new ServiceUser();
        User u =new User();        
        if(TFPassword.getText().equals(TFconfirmPassword.getText())){
            //sets
            u.setFirstName(TFFirstName.getText());
            u.setLastName(TFLastName.getText());
            u.setDateOfBirth(java.sql.Date.valueOf(TFDateOfBirth.getValue()));
            u.setEmail(TFEmail.getText());        
            u.setPassword(TFPassword.getText());  
            sp.AddUser(u);
        }      
    }
    
    

    private void AfficherPersonne(ActionEvent event) {
        ServiceUser sp= new ServiceUser();
        afficher.setText(sp.AfficherPersonne().toString());
    }

    @FXML
    private void GoToSignIn(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/user/SignIn.fxml"));
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
