/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.entities.recipe.Recipe;
import coheal.services.recipe.RecipeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addRecipeAction(ActionEvent event) {
        javafx.stage.Window owner = TitleTF.getScene().getWindow();
        if (TitleTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!",
                    "Entrer un titre pour votre recette!");
            return;
        }
        if (DescTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!",
                    "Decrire votre recette!");
            return;
        }

        RecipeService RS = new RecipeService();
        Recipe R = new Recipe();
        R.setCatId(1);
        R.setTitle(TitleTF.getText());
        R.setDescription(DescTF.getText());
        R.setCalories(Double.valueOf(CaloriesTF.getText()));
        RS.Create_Recipe(1, "Lunch", R);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                "Recette Ajoutée avec succés!");
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
    
}
