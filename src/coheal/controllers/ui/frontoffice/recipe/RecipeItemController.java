/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.entities.recipe.Recipe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class RecipeItemController implements Initializable {

    @FXML
    private Label recipeTitle;
    @FXML
    private Label recipeTitle1;
    @FXML
    private Label recipeDesc;
    @FXML
    private ImageView recipeImg;
    @FXML
    private Label cals;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Recipe r){
        recipeTitle.setText(r.getTitle());
        recipeDesc.setText(r.getDescription());
        cals.setText(String.valueOf(r.getCalories()));
    }

}
