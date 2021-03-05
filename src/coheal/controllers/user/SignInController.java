package coheal.controllers.user;

import coheal.services.user.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author wajdi's pc
 */
public class SignInController implements Initializable {

    @FXML
    private TextField TFEmail;
    @FXML
    private TextField TFPassword;
    @FXML
    private Button LoginButton;
    @FXML
    private Button LoginButton1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event) {
        Window owner = LoginButton.getScene().getWindow();
        //test empty fields
        if (TFEmail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
        }
        if (TFPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }
        
        
        String email = TFEmail.getText();
        String password = TFPassword.getText();
        
        ServiceUser su= new ServiceUser();
        
        boolean flag = su.Validate_Login(email, password);
        if (!flag) {
            infoBox("Please enter correct Email and Password or maybe your your account is deleted", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Success");
                //redirect to modifyDelete
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/coheal/views/user/ModifyDelete.fxml"));
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
    
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    private void GoToSignUp (ActionEvent event) throws IOException {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/user/SignUp.fxml"));
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
