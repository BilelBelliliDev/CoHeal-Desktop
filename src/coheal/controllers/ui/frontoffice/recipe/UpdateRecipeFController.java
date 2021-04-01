/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.entities.recipe.Recipe;
import coheal.services.recipe.RecipeService;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UpdateRecipeFController implements Initializable {

    @FXML
    private FontAwesomeIconView close1;
    @FXML
    private JFXTextField TitleTF;
    @FXML
    private JFXTextArea DescTF;
    @FXML
    private JFXTextField PersonsTF;
    @FXML
    private ImageView image;
    @FXML
    private JFXTextArea IngredientsTF;
    @FXML
    private JFXTextArea StepsTF;
    @FXML
    private JFXTextField CaloriesTF;
    @FXML
    private JFXTextField DurationTF;
    @FXML
    private FontAwesomeIconView updateRecipeBT;

    Recipe recipe;
    RecipeService rs = new RecipeService();
    RecipeHolder rh = RecipeHolder.getINSTANCE();
    private static String projectPath = System.getProperty("user.dir").replace("/", "\\");
    File f = null;

    private boolean title = false, cal = false, dur = false, per = false, desc = false, ingred = false, steps = false;
    @FXML
    private Label imgUrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recipe = rs.getRecipe(rh.getId());
        //remplissage des textfields
        TitleTF.setText(recipe.getTitle());
        DescTF.setText(recipe.getDescription());
        IngredientsTF.setText(recipe.getIngredients());
        StepsTF.setText(recipe.getSteps());
        CaloriesTF.setText(String.valueOf(recipe.getCalories()));
        DurationTF.setText(String.valueOf(recipe.getDuration()));
        PersonsTF.setText(String.valueOf(recipe.getPersons()));
        image.setImage(recipe.getImg().getImage());
        imgUrl.setText(recipe.getImgUrl());

        //appel contrôle saisie
        titleValidatorUp();
        descValidatorUp();
        ingredValidatorUp();
        stepsValidatorUp();
        caloriesValidatorUp();
        durationValidatorUp();
        personsValidatorUp();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close1.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        f = chooser.showOpenDialog(null);
        Image img = new Image("file:///" + f);
        image.setImage(img);
        //imgTF.setText(f.getName());
        System.out.println(f.getName());
        recipe.setImgUrl(f.getName());
        System.out.println(recipe.getImgUrl());
    }

    @FXML
    private void updateRecipeAction(MouseEvent event) {
        javafx.stage.Window owner = updateRecipeBT.getScene().getWindow();
        if (recipe != null) {
            recipe = rs.getRecipe(rh.getId());
            
            Recipe r = new Recipe();
            r.setTitle(TitleTF.getText());
            r.setDescription(DescTF.getText());
            r.setIngredients(IngredientsTF.getText());
            r.setSteps(StepsTF.getText());
            r.setCalories(Integer.valueOf(CaloriesTF.getText()));
            r.setDuration(Integer.valueOf(DurationTF.getText()));
            r.setPersons(Integer.valueOf(PersonsTF.getText()));
            //image 
            File dest = null;
            if (f != null) {
                dest = new File(projectPath + "/src/coheal/resources/images/recipes/" + recipe.getImgUrl());
            } else {
                System.out.println("Empty!");
            }

            try {
                if (dest != null) {
                    if (FileUtils.contentEquals(f, dest)) {
                        System.out.println("exists!");
                    } else {
                        FileUtils.copyFile(f, dest);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            rs.Update_Recipe(r, rh.getId());

//            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
//                    "Recipe updated successfully!");

        //Notification
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Success");
        tray.setMessage("Recipe updated successfully!");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

        }
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    //contrôle saisie 
    public void titleValidatorUp() { //title empty string
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        TitleTF.setValidators(valid);
        valid.setMessage("Enter the title");
        TitleTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    TitleTF.validate();
                    if (TitleTF.validate()) {
                        title = true;
                    } else {
                        title = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddRecipeFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descValidatorUp() { //description empty string
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        DescTF.setValidators(valid);
        valid.setMessage("Enter the description");
        DescTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    DescTF.validate();
                    if (DescTF.validate()) {
                        desc = true;
                    } else {
                        desc = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddRecipeFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ingredValidatorUp() { //ingredients empty string
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        IngredientsTF.setValidators(valid);
        valid.setMessage("Enter the ingredients");
        IngredientsTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    IngredientsTF.validate();
                    if (IngredientsTF.validate()) {
                        ingred = true;
                    } else {
                        ingred = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddRecipeFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stepsValidatorUp() { //steps empty string
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        StepsTF.setValidators(valid);
        valid.setMessage("Enter the steps");
        StepsTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    StepsTF.validate();
                    if (StepsTF.validate()) {
                        steps = true;
                    } else {
                        steps = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddRecipeFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void caloriesValidatorUp() { //calories only numbers
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^(0|[1-9][0-9]*)$");
        CaloriesTF.setValidators(valid);
        valid.setMessage("not valid");
        CaloriesTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    CaloriesTF.validate();
                    if (CaloriesTF.validate()) {
                        cal = true;
                    } else {
                        cal = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddRecipeFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void durationValidatorUp() { //duration only numbers
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^(0|[1-9][0-9]*)$");
        DurationTF.setValidators(valid);
        valid.setMessage("not valid");
        DurationTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    DurationTF.validate();
                    if (DurationTF.validate()) {
                        dur = true;
                    } else {
                        dur = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddRecipeFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void personsValidatorUp() { //persons only numbers
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^(0|[1-9][0-9]*)$");
        PersonsTF.setValidators(valid);
        valid.setMessage("not valid");
        PersonsTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    PersonsTF.validate();
                    if (PersonsTF.validate()) {
                        per = true;
                    } else {
                        per = false;
                    }
                }
            }
        });
        try {
            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
            valid.setIcon(new ImageView(errorIcon));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddRecipeFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
