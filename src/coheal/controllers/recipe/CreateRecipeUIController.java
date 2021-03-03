/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.recipe;

import coheal.entities.Recipe.Recipe;
import coheal.services.RecipeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CreateRecipeUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label label;
    @FXML
    private TextField TitreTF;
    @FXML
    private TextArea DescTF;
    @FXML
    private Button imgImpBT;
    @FXML
    private Label Affichage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    

    @FXML
    private void Bouton_Ajouter(ActionEvent event) throws SQLException {
        RecipeService RS = new RecipeService();
        Recipe R = new Recipe();
        R.setTitle(TitreTF.getText());
        R.setDescription(DescTF.getText());
        R.setImgUrl(null);
        RS.Create_Recipe(1,"Lunch",R);
    }

    @FXML
    private void Bouton_Afficher(ActionEvent event) {
        RecipeService RS = new RecipeService();
        Affichage.setText(RS.Afficher_Recipe().toString());
    }
    
    @FXML
    private void Bouton_Modifier(ActionEvent event) {
       RecipeService RS = new RecipeService();
       Recipe R = new Recipe();
       R.setTitle(TitreTF.getText());
       R.setTitle(DescTF.getText());
       //R.setTitle(TitreTF.getText());
       RS.Update_Recipe(R, 1);
    }

    @FXML
    private void Bouton_Supprimer(ActionEvent event) {
       RecipeService RS = new RecipeService();
       System.out.println("");
    }

}
