/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.recipe;

import coheal.entities.recipe.Recipe;
import coheal.services.recipe.RecipeService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class RecipePageController implements Initializable {

    @FXML
    private FontAwesomeIconView close;
    @FXML
    private AnchorPane recipePane;
    @FXML
    private TableView<Recipe> TableView;
    @FXML
    private TableColumn<Recipe, Integer> rID_col;
    @FXML
    private TableColumn<Recipe, Integer> UserID_col;
    @FXML
    private TableColumn<Recipe, String> titre_col;
    @FXML
    private TableColumn<Recipe, String> Desc_col;
    @FXML
    private TableColumn<Recipe, Float> cal_col;
    @FXML
    private TableColumn<Recipe, Integer> dur_col;
    @FXML
    private TableColumn<Recipe, Integer> persons_col;
    @FXML
    private TableColumn<Recipe, String> ingred_col;
    @FXML
    private TableColumn<Recipe, String> Steps_col;

    RecipeService rs = new RecipeService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //    imaglivre.setCellValueFactory(new PropertyValueFactory<Recipe, String>("imgUrl"));
        //   imageView.setCellValueFactory(new PropertyValueFactory<book, ImageView>("img"));
        
        rID_col.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("recipeId"));
        titre_col.setCellValueFactory(new PropertyValueFactory<Recipe, String>("title"));
        Desc_col.setCellValueFactory(new PropertyValueFactory<Recipe, String>("description"));
        cal_col.setCellValueFactory(new PropertyValueFactory<Recipe, Float>("calories"));
        dur_col.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("duration"));
        persons_col.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("persons"));
        ingred_col.setCellValueFactory(new PropertyValueFactory<Recipe, String>("ingredients"));
        Steps_col.setCellValueFactory(new PropertyValueFactory<Recipe, String>("steps"));
        TableView.setItems(rs.Afficher_Recipe());
    
       // TableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            /*@Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (TableView.getSelectionModel().getSelectedItem() != null) {
                    Recipe selectedRecipe = TableView.getSelectionModel().getSelectedItem();
                    selecttitre.setText(selectedBook.getTitle());
                    selectdescription.setText(selectedBook.getDescription());
                    selectauteur.setText(selectedBook.getAuthor());
                    selectimage.setText(selectedBook.getImgUrl());
                    selectedId = selectedBook.getBookId();
                    canModify = true;
                }
            }
        });*/
    }
                
    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.setIconified(true);
    }

}