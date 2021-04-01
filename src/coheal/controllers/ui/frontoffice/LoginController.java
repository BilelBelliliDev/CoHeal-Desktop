package coheal.controllers.ui.frontoffice;

import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import coheal.entities.user.User;
import coheal.services.user.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class LoginController implements Initializable {

    private boolean emailSI = false, emailSU = false, fName = false, lName = false, date = false, password = false;

    @FXML
    private AnchorPane layer;
    private JFXTextField inpEmailSI;
    @FXML
    private AnchorPane animatedLayer;
    @FXML
    private FontAwesomeIconView minIcon;
    @FXML
    private FontAwesomeIconView closeIcon;
    @FXML
    private JFXButton btnSignUpAnim;
    @FXML
    private JFXButton btnSignInAnim;
    @FXML
    private Label signInLabel;
    @FXML
    private Label signUpLabel;
    @FXML
    private Pane signInPane;
    @FXML
    private Pane signUpPane;
    double xOffset, yOffset;
    Stage stage;
    @FXML
    private Pane signUpPane2;
    @FXML
    private Label signUpLabel1;
    @FXML
    private JFXTextField TFEmailSUP;
    @FXML
    private JFXPasswordField TFPasswordSUP;
    @FXML
    private JFXPasswordField TFConfirmPasswordSUP;
    @FXML
    private JFXTextField TFFirstNameSUP;
    @FXML
    private JFXTextField TFLastNameSUP;
    @FXML
    private JFXDatePicker DPDateOfBirthSUP;
    @FXML
    private JFXButton btnNext;
    @FXML
    private JFXTextField TFEmailSIN;
    @FXML
    private JFXPasswordField TFPasswordSIN;
    @FXML
    private JFXButton BTNSignIn;
    @FXML
    private JFXButton BTNSignUp;

    /**
     * Initializes the controller class.
     */
    
    //ValidationSupport ValidationEmailSIN = new ValidationSupport();
    //ValidationSupport ValidationPasswordSIN = new ValidationSupport();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TFEmailSIN.requestFocus();
        new ZoomOut(signUpPane2).play();
        emailValidatorSI();
        emailValidatorSU();
        passwordValidator();
        nameValidator();
        lNameValidator();
        //ValidationEmailSIN.registerValidator(TFEmailSIN, Validator.createEmptyValidator("Email field is empty"));
        //ValidationPasswordSIN.registerValidator(TFPasswordSIN, Validator.createEmptyValidator("Password field is empty"));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void signInAction(ActionEvent event) throws IOException {
        //login code    
        Window owner = BTNSignIn.getScene().getWindow();
        //test empty fields
        if (emailSI) {
            String email = TFEmailSIN.getText();
            String password = TFPasswordSIN.getText();
            ServiceUser su = new ServiceUser();
            boolean flag = su.Validate_Login(email, password);
            if (!flag) {
                //-------------notification--------------------------------------------
                TrayNotification tray=new TrayNotification();
                AnimationType type =AnimationType.POPUP;
                
                tray.setAnimationType(type);
                tray.setTitle("Sign In Failed");
                tray.setMessage("Rong Email and/or Password or account is deleted");
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
            } else {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/HomePageHolder.fxml"));
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/backoffice/AdminPageHolder.fxml"));

                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                scene.getStylesheets().add("/coheal/resources/css/main.css");
                HomePageHolderController hpc = new HomePageHolderController();
                hpc.setStage(stage);
                stage.show();
                Stage stage1 = (Stage) BTNSignIn.getScene().getWindow();
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
    }

    @FXML
    private void signUpAction(ActionEvent event) {

        Window owner = BTNSignUp.getScene().getWindow();
        ServiceUser sp;
        //test empty fields
        if (fName && lName && DPDateOfBirthSUP.getValue() != null && emailSU && password && (TFPasswordSUP.getText().equals(TFConfirmPasswordSUP.getText()))) {
            sp = new ServiceUser();
            User u = new User();
                //sets
                u.setFirstName(TFFirstNameSUP.getText());
                u.setLastName(TFLastNameSUP.getText());
                u.setDateOfBirth(java.sql.Date.valueOf(DPDateOfBirthSUP.getValue()));
                u.setEmail(TFEmailSUP.getText());
                u.setPassword(TFPasswordSUP.getText());
                sp.AddUser(u);
                
                //set the sign up email to sign in field
                String EM =TFEmailSUP.getText();
                TFEmailSIN.setText(EM);
                
                //clear the sign up fields
                TFFirstNameSUP.clear();
                TFLastNameSUP.clear();
                TFEmailSUP.clear();
                TFPasswordSUP.clear();
                TFConfirmPasswordSUP.clear();
                DPDateOfBirthSUP.setValue(null);
                
                signInAnimAction();
                prevSignUp();
                
                //----------notification---------------------
                TrayNotification tray=new TrayNotification();
                AnimationType type =AnimationType.POPUP;
                
                tray.setAnimationType(type);
                tray.setTitle("Sign Up");
                tray.setMessage(EM+" Sign Up successfully");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                //-------------------------------------------
        }
        else {
            //notification
                TrayNotification tray=new TrayNotification();
                AnimationType type =AnimationType.POPUP;
                
                tray.setAnimationType(type);
                tray.setTitle("Sign Up");
                tray.setMessage(" Sign Up faild check your fields");
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(3000));
        }
        
    }

    @FXML
    private void signUpAnimAction() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(animatedLayer);
        slide.setToX(380);
        slide.play();
        new ZoomOut(signInPane).play();
        new ZoomIn(signUpPane).play();
        slide.setOnFinished((e -> {
            closeIcon.setVisible(true);
            minIcon.setVisible(true);
            btnSignInAnim.setVisible(true);
            btnSignUpAnim.setVisible(false);
            TFFirstNameSUP.requestFocus();
        }));
         prevSignUp();
    }

    @FXML
    private void signInAnimAction() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(animatedLayer);
        slide.setToX(0);
        slide.play();
        closeIcon.setVisible(false);
        minIcon.setVisible(false);
        new ZoomOut(signUpPane).play();
        new ZoomIn(signInPane).play();
        slide.setOnFinished((e -> {

            btnSignInAnim.setVisible(false);
            btnSignUpAnim.setVisible(true);
            TFEmailSIN.requestFocus();

        }));
        
       
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) BTNSignIn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) BTNSignIn.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void NextAction(ActionEvent event) {

        new ZoomOut(signUpPane).play();
        new ZoomIn(signUpPane2).play();
        TFEmailSUP.requestFocus();
    }

    @FXML
    private void prevSignUp() {
        new ZoomOut(signUpPane2).play();
        new ZoomIn(signUpPane).play();
    }

    public void emailValidatorSI() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        TFEmailSIN.setValidators(valid);
        valid.setMessage("Email is not valid");
        TFEmailSIN.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    TFEmailSIN.validate();
                    if (TFEmailSIN.validate()) {
                        emailSI = true;
                    } else {
                        emailSI = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void emailValidatorSU() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        TFEmailSUP.setValidators(valid);
        valid.setMessage("Email is not valid");
        TFEmailSUP.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    TFEmailSUP.validate();
                    if (TFEmailSUP.validate()) {
                        emailSU = true;
                    } else {
                        emailSU = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void passwordValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^(?=.*?[A-Z])(?=.*?[a-z]).{6,}$");
        TFPasswordSUP.setValidators(valid);
        valid.setMessage("Min 6 characters + uppercase letter");
        TFPasswordSUP.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    TFPasswordSUP.validate();
                    if (TFPasswordSUP.validate()) {
                        password = true;
                    } else {
                        password = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nameValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        TFFirstNameSUP.setValidators(valid);
        valid.setMessage("Please enter a valid name");
        TFFirstNameSUP.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    TFFirstNameSUP.validate();
                    if (TFFirstNameSUP.validate()) {
                        fName = true;
                    } else {
                        fName = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lNameValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        TFLastNameSUP.setValidators(valid);
        valid.setMessage("Please enter a valid last name");
        TFLastNameSUP.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    TFLastNameSUP.validate();
                    if (TFLastNameSUP.validate()) {
                        lName = true;
                    } else {
                        lName = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
