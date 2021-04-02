/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.entities.recipe.RecipeCategory;
import coheal.services.ui.UIService;
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
 * @author HP
 */
public class RecipeCategoryItemController implements Initializable {

    @FXML
    private ImageView recipeCatgImg;
    @FXML
    private Label recipeCatgTitle;
    @FXML
    private Label recipeCatgTotalRecipes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(RecipeCategory tc) {
        UIService stc = new UIService();
        recipeCatgImg.setImage(tc.getImg().getImage());
        recipeCatgTitle.setText(tc.getName());
        recipeCatgTotalRecipes.setText(String.valueOf(stc.ListerRecipesByIdCatg(tc.getName()).size()) + " Recipes");
    }

    @FXML
    private void showRecipes(MouseEvent event) throws IOException {
        RecipeCategoryHolder holder = RecipeCategoryHolder.getINSTANCE();
        holder.setName(recipeCatgTitle.getText());
        AnchorPane pageHolder = (AnchorPane) recipeCatgTitle.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        if(pageHolder==null){
             pageHolder = (AnchorPane) recipeCatgTitle.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        }
//        AnchorPane pageHolder = (AnchorPane) recipeCatgTitle.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/recipe/RecipesByCategory.fxml")));

    }

}
