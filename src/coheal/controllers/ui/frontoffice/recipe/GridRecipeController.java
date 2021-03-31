/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.entities.recipe.Recipe;
import coheal.services.recipe.RecipeService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class GridRecipeController implements Initializable {

    @FXML
    private GridPane recipeGrid;
    public int pageCount, currentPage;
    private RecipeService rs = new RecipeService();
    private int gridSize, columnCount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setData(int index) {
        gridSize = recipeGrid.getRowConstraints().size() * recipeGrid.getColumnConstraints().size();
        columnCount = recipeGrid.getColumnConstraints().size() - 1;
        currentPage = index;
        try {
            int y = 0;
            int x = 0;
            List<Recipe> recipes;
            recipes = rs.Afficher_Recipe();
            //pagination
            pageCount = recipes.size() / gridSize;
            if (recipes.size() % gridSize > 0) {
                pageCount++;
            }
            int a, b;
            a = currentPage * gridSize;
            if (currentPage == (pageCount - 1)) {
                b = recipes.size();
            } else {
                b = a + gridSize;
            }
            for (int i = a; i < b; i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/recipe/RecipeItem.fxml"));
                AnchorPane pane = loader.load();
                RecipeItemController c = loader.getController();
                c.setData(recipes.get(i));
                if (x > columnCount) {
                    y++;
                    x = 0;
                }
                recipeGrid.add(pane, x, y);
                x++;
            }
        } catch (IOException ex) {
            Logger.getLogger(GridRecipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
       
}
