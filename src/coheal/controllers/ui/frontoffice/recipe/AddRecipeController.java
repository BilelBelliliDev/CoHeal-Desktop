/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.entities.recipe.Recipe;
import static coheal.services.recipe.Constants.projectPath;
import coheal.services.recipe.RecipeCategoryService;
import coheal.services.recipe.RecipeService;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXComboBox;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class AddRecipeController implements Initializable {

    @FXML
    private TextArea DescTF;
    @FXML
    private TextField CaloriesTF;
    @FXML
    private TextField TitleTF;
    @FXML
    private Label imgTF;
    @FXML
    private TextArea IngredientsTF;
    @FXML
    private TextArea StepsTF;
    @FXML
    private TextField DurationTF;
    @FXML
    private TextField PersonsTF;
    @FXML
    private Button addRecipeBT;
    File f = null;
    Recipe r=new Recipe();
    private static String projectPath = System.getProperty("user.dir").replace("/", "\\");
    RecipeService rs = new RecipeService();
    String cat = "";
    @FXML
    private JFXComboBox<String> CatBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //comboBox
        RecipeCategoryService rcs = new RecipeCategoryService();
        for (int i = 0; i < rcs.Afficher_RecipeCategory().size(); i++) {
            CatBox.getItems().add(rcs.Afficher_RecipeCategory().get(i).getName());
        }
    }

    @FXML
    private void addRecipeAction(ActionEvent event) throws IOException {
        javafx.stage.Window owner = addRecipeBT.getScene().getWindow();
        if (TitleTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter a title for your recipe!");
            return;
        }
        if (DescTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Describe your recipe!");
            return;
        }
        if (IngredientsTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter the required ingredients for your recipe !");
            return;
        }
        if (StepsTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter the steps of preparing of your recipe !");
            return;
        }
        if (CaloriesTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter the number of calories of your recipe !");
            return;
        }
        if (DurationTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter the  required ingredients for your recipe !");
            return;
        }
        if (PersonsTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter the number of persons of your recipe !");
            return;
        }

        r.setTitle(TitleTF.getText());
        r.setDescription(DescTF.getText());
        r.setIngredients(IngredientsTF.getText());
        r.setSteps(StepsTF.getText());
        float c = Float.parseFloat(CaloriesTF.getText());
        r.setCalories(c);
        int d = Integer.parseInt(DurationTF.getText());
        r.setDuration(d);
        int p = Integer.parseInt(PersonsTF.getText());
        r.setPersons(p);

       File dest = new File(projectPath + "/src/coheal/resources/images/recipes/" + f.getName());
        try {
            FileUtils.copyFile(f, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rs.Create_Recipe(UserSession.getUser_id(), cat, r);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                "Recipe added successfully!");

    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) CaloriesTF.getScene().getWindow();
        stage.close();
    }

//    Desktop desktop = Desktop.getDesktop();
//    FileChooser fileChooser = new FileChooser();

    @FXML
    private void ListCategoriesBox(ActionEvent event) {
        cat = CatBox.getValue();
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        f = chooser.showOpenDialog(null);
        imgTF.setText(f.getName());
        System.out.println(f.getName());
        r.setImgUrl(f.getName());
        System.out.println(r.getImgUrl());
    }

}
