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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class RecipeCategoryItemController implements Initializable {

    @FXML
    private ImageView recipeCatgImg;
    @FXML
    private Label recipeCatgTitle;
    @FXML
    private Label recipeCatgTotalEvents;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(RecipeCategory tc){
        UIService stc=new UIService();
        recipeCatgTitle.setText(tc.getName());
        recipeCatgTotalEvents.setText(String.valueOf(stc.ListerRecipesByIdCatg(tc.getName()).size())+" Recipes");
    }

}
