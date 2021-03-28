/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.entities.recipe.Recipe;
import coheal.entities.recipe.RecipeCategory;
import static coheal.services.recipe.Constants.projectPath;
import coheal.services.recipe.RecipeCategoryService;
import coheal.services.recipe.RecipeService;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddRecipeCategoryController implements Initializable {

    @FXML
    private JFXTextField Title;
    @FXML
    private Label imageTxt;
    File f = null;
    RecipeCategory rc = new RecipeCategory();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        f = fileChooser.showOpenDialog(null);
        imageTxt.setText(f.getName());
        rc.setImgUrl(f.getName());
    }

    @FXML
    private void addRecipeCategoryAction(ActionEvent event) {
        RecipeCategoryService rcs = new RecipeCategoryService();
        RecipeCategory rc = new RecipeCategory();
        rc.setName(Title.getText());
        File dest = new File(projectPath + "/src/coheal/resources/images/recipes/" + f.getName());
        try {
            FileUtils.copyFile(f, dest);

        } catch (IOException e) {
            e.printStackTrace();
        }
        rcs.Create_RecipeCategory(rc);
    }

@FXML
        private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
       //stage = (Stage).getScene().getWindow();
        stage.close();
    }

}
