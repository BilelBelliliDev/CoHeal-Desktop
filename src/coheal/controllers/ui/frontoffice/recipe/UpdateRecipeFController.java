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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recipe = rs.getRecipe(rh.getId());
        TitleTF.setText(recipe.getTitle());
        DescTF.setText(recipe.getDescription());
        IngredientsTF.setText(recipe.getIngredients());
        StepsTF.setText(recipe.getSteps());
        CaloriesTF.setText(String.valueOf(recipe.getCalories()));
        DurationTF.setText(String.valueOf(recipe.getDuration()));
        PersonsTF.setText(String.valueOf(recipe.getPersons()));
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

            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                    "Recipe modified successfully!");

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

}
