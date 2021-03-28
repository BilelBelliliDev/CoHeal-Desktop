/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.entities.recipe.Recipe;
import coheal.entities.recipe.RecipeCategory;
import coheal.services.recipe.RecipeCategoryService;
import coheal.services.recipe.RecipeService;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UpdateRecipeController implements Initializable {

    @FXML
    private TextArea DescTF;
    @FXML
    private Label imgTF;
    @FXML
    private TextField CaloriesTF;
    @FXML
    private TextField TitleTF;
    @FXML
    private TextArea IngredientsTF;
    @FXML
    private TextArea StepsTF;
    @FXML
    private TextField DurationTF;
    @FXML
    private TextField PersonsTF;
    @FXML
    private JFXComboBox<String> CatBox;

    Recipe recipe;
    RecipeService rs = new RecipeService();
    RecipeCategoryService rcs = new RecipeCategoryService();
    RecipeHolder rh = RecipeHolder.getINSTANCE();
    private static String projectPath = System.getProperty("user.dir").replace("/", "\\");
    File f = null;
    String cat = "";
    RecipeCategory rc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RecipeCategory rc = new RecipeCategory();
        for (int i = 0; i < rcs.Afficher_RecipeCategory().size(); i++) {
            CatBox.getItems().add(rcs.Afficher_RecipeCategory().get(i).getName());
        }

        recipe = rs.getRecipe(rh.getId());
        TitleTF.setText(recipe.getTitle());
        imgTF.setText(recipe.getImgUrl());
        DescTF.setText(recipe.getDescription());
        IngredientsTF.setText(recipe.getIngredients());
        StepsTF.setText(recipe.getSteps());
        CaloriesTF.setText(String.valueOf(recipe.getCalories()));
        DurationTF.setText(String.valueOf(recipe.getDuration()));
        PersonsTF.setText(String.valueOf(recipe.getPersons()));
        CatBox.getSelectionModel().select(recipe.getCat().getName());

    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) CatBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        f = fileChooser.showOpenDialog(null);
        imgTF.setText(f.getName());
        recipe.setImgUrl(f.getName());

    }

    @FXML
    private void ListCategoriesBox(ActionEvent event) {
        cat = CatBox.getValue();
    }

    @FXML
    private void UpdateRecipeAction(ActionEvent event) {
        if (recipe != null) {
            recipe = rs.getRecipe(rh.getId());
            TitleTF.setText(recipe.getTitle());
            imgTF.setText(recipe.getImgUrl());
            DescTF.setText(recipe.getDescription());
            IngredientsTF.setText(recipe.getIngredients());
            StepsTF.setText(recipe.getSteps());
            CaloriesTF.setText(String.valueOf(recipe.getCalories()));
            DurationTF.setText(String.valueOf(recipe.getDuration()));
            PersonsTF.setText(String.valueOf(recipe.getPersons()));
            //CatBox.getSelectionModel().select(recipe.getCat().getName());

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
            System.out.println("click"+cat);
            rc = rcs.RechercherRecipeCategory(cat);
            recipe.setCat(rc);
            rs.Update_Recipe(recipe, rh.getId());
        }

    }
}
