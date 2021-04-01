package coheal.controllers.ui.frontoffice.user;

import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.user.User;
import coheal.services.user.ServiceUser;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author wajdi's pc
 */
public class ProfileUserController implements Initializable {

    @FXML
    private ScrollPane AnchorPane;
    @FXML
    private JFXTextField TFFirstName;
    @FXML
    private JFXTextField TFLastName;
    @FXML
    private JFXTextField TFEmail;
    @FXML
    private JFXDatePicker DPDateOfBirth;
    @FXML
    private JFXPasswordField TFPassword;
    @FXML
    private JFXPasswordField TFConfirmPassword;
    @FXML
    private JFXPasswordField TFOldPassword;
    @FXML
    private ImageView imagedefaultuser;
    @FXML
    private JFXButton BTNUpdateProfile;
    @FXML
    private JFXButton BTNUpdatePassword;
    @FXML
    private JFXButton BTNDeactivate;
    private double xOffset, yOffset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(AnchorPane).play();

        TFFirstName.setText(UserSession.getFirst_name());
        TFLastName.setText(UserSession.getLast_name());
        DPDateOfBirth.setValue(UserSession.getDate_of_birth().toLocalDate());
        TFEmail.setText(UserSession.getEmail());
    }

    @FXML
    private void UpdateProfile(ActionEvent event) {
        ServiceUser su = new ServiceUser();
        User u = new User();
        //sets
        u.setEmail(TFEmail.getText());
        u.setFirstName(TFFirstName.getText());
        u.setLastName(TFLastName.getText());
        u.setDateOfBirth(java.sql.Date.valueOf(DPDateOfBirth.getValue()));

        su.ModifierUser(u, UserSession.getUser_id());
        //-------------notification--------------------------------------------
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle("Profile Detail Updated SUCCESSFULLY");
        tray.setMessage("User detail updated succesfully");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
        //---------------------------------------------------------------------
    }

    @FXML
    private void UpdatePassword(ActionEvent event) {
        ServiceUser su = new ServiceUser();
        String pw = UserSession.getPassword();
        Window owner = BTNUpdatePassword.getScene().getWindow();

        if (TFOldPassword.getText().equals(pw)) {
            if (TFPassword.getText().isEmpty() && TFConfirmPassword.getText().isEmpty()) {
                //-------------notification--------------------------------------------
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle("Password Update Warning");
                tray.setMessage("password and/or confirm password fields is/are empty");
                tray.setNotificationType(NotificationType.WARNING);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
            } else {
                if (TFPassword.getText().equals(TFConfirmPassword.getText())) {
                    su.ModifierUserPassword(TFPassword.getText(), UserSession.getUser_id());
                    //-------------notification--------------------------------------------
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle("Password Update SUCCESS");
                    tray.setMessage("User password updated succesfully");
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));
                    //---------------------------------------------------------------------

                } else {
                    //-------------notification--------------------------------------------
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle("Password Update Warning");
                    tray.setMessage("password diffrent than the confirmpassword");
                    tray.setNotificationType(NotificationType.WARNING);
                    tray.showAndDismiss(Duration.millis(3000));
                    //---------------------------------------------------------------------
                }
            }
        } else if (TFOldPassword.getText().isEmpty()) {
            //-------------notification--------------------------------------------
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle("Password Update Warning");
            tray.setMessage("Old password field is empty");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
            //---------------------------------------------------------------------
        } else {
            //-------------notification--------------------------------------------
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle("Password Update Warning");
            tray.setMessage("enter your old password correctly");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
            //---------------------------------------------------------------------
        }

    }

    @FXML
    private void DeactivateAccount(ActionEvent event) throws IOException {

        ServiceUser su = new ServiceUser();
        System.err.println(UserSession.getUser_id());
        su.DeleteUser(UserSession.getUser_id());

        UserSession.cleanUserSession();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        HomePageHolderController hpc = new HomePageHolderController();
        hpc.setStage(stage);
        stage.show();
        Stage stage1 = (Stage) TFEmail.getScene().getWindow();
        stage1.close();
        root.setOnMousePressed((MouseEvent mouseEvent) -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent mouseEvent) -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
            stage.setOpacity(0.85f);
        });
        root.setOnMouseReleased((MouseEvent mouseEvent) -> {
            stage.setOpacity(1.0f);
        });
    }
}
