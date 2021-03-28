/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.entities.recipe.Recipe;
import coheal.entities.recipe.RecipeCategory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class RecipeItemController implements Initializable {

    @FXML
    private Label recipeTitle;
    @FXML
    private Label recipeDesc;
    @FXML
    private ImageView recipeImg;
    @FXML
    private Label cals;
    @FXML
    private Label persons;
    @FXML
    private Label durat;
    @FXML
    private Label cat;
    
    int id = 0;
    RecipeCategory rc = new RecipeCategory();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Recipe r) {
        recipeTitle.setText(r.getTitle());
        recipeDesc.setText(r.getDescription());
        recipeImg.setImage(r.getImg().getImage());
        cals.setText(String.valueOf(r.getCalories()));
        durat.setText(String.valueOf(r.getDuration()));
        persons.setText(String.valueOf(r.getPersons()));
        cat.setText(rc.getName());
        id = r.getRecipeId();

    }

    @FXML
    private void showRecipeDetails(MouseEvent event) throws IOException {
        RecipeHolder rh = RecipeHolder.getINSTANCE();
        rh.setId(id);
        AnchorPane pageHolder = (AnchorPane) persons.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/recipe/RecipeDetails.fxml")));
    }
}
