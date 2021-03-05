/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.recipe;

import coheal.entities.recipe.Recipe;
import coheal.services.recipe.RecipeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
<<<<<<< Updated upstream
=======
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
>>>>>>> Stashed changes
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

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
    private TextField imgTF;
    @FXML
    private TableColumn<Recipe, String> title_col;
    @FXML
    private TableColumn<Recipe, String> desc_col;
    @FXML
    private TableColumn<Recipe, String> img_col;
    @FXML
    private TableView<Recipe> table;

    boolean isSelected = false;

    RecipeService rs = new RecipeService();

    ObservableList<Recipe> l = FXCollections.observableList(rs.Afficher_Recipe());
    @FXML
    private Button BoutonAjouter;
    @FXML
    private Button BoutonModifier;
    @FXML
    private Button BoutonSupprimer;
    @FXML
    private ComboBox ComboBox;
<<<<<<< Updated upstream
=======
    @FXML
    private ComboBox<Integer> userIdBox;
    @FXML
    private Button RetourBT;
>>>>>>> Stashed changes

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

    public void init() {
        System.out.println(l);
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        img_col.setCellValueFactory(new PropertyValueFactory<>("img_url"));
        table.setItems(l);
        table.setRowFactory(tv -> {
            TableRow<Recipe> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Recipe rowData = row.getItem();
                    TitreTF.setText(rowData.getTitle());
                    DescTF.setText(rowData.getDescription());
                    imgTF.setText(rowData.getImgUrl());
                    isSelected = true;
                }
            });
            return row;
        });
        
        //ComboBox
        //RecipeCategory rc = new RecipeCategory();
        //ObservableList<String> list = FXCollections.observableArrayList(rc.getName());
        ObservableList<String> list = FXCollections.observableArrayList("Soupes","Thé","Boissons","Salades","Plats poulets");
        ComboBox.setItems(list);
    }

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void Bouton_Ajouter(ActionEvent event) throws SQLException {

        javafx.stage.Window owner = BoutonModifier.getScene().getWindow();
        if (TitreTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!",
                    "Entrer un titre pour votre recette!");
            return;
        }
        if (DescTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!",
                    "Decrire votre recette!");
            return;
        }
        if (imgTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!",
                    "Entrer l'URL de l'image!");
            return;
        }

        RecipeService RS = new RecipeService();
        Recipe R = new Recipe();
        R.setCatId(1);
        R.setTitle(TitreTF.getText());
        R.setDescription(DescTF.getText());
        R.setImgUrl(imgTF.getText());
        RS.Create_Recipe(1, "Lunch", R);
        table.setItems((ObservableList<Recipe>) rs.Afficher_Recipe());

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                "Recette Ajoutée avec succés!");
    }

    @FXML
    private void Bouton_Modifier(ActionEvent event) {
        javafx.stage.Window owner = BoutonModifier.getScene().getWindow();
        if (isSelected) {
            Recipe r = table.getSelectionModel().getSelectedItem();
            RecipeService RS = new RecipeService();
            Recipe R = new Recipe();
            R.setCatId(1);
            R.setTitle(TitreTF.getText());
            R.setDescription(DescTF.getText());
            R.setImgUrl(imgTF.getText());
            RS.Update_Recipe(R, r.getRecipeId());
            table.setItems((ObservableList<Recipe>) rs.Afficher_Recipe());

            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                    "Recette Modifiée avec succés!");
        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!", "Selectionner une recette!");
            return;
        }

    }

    @FXML
    private void Bouton_Supprimer(ActionEvent event) {
        javafx.stage.Window owner = BoutonSupprimer.getScene().getWindow();
        if (isSelected) {
            RecipeService RS = new RecipeService();
            Recipe recipe = new Recipe();
            recipe = table.getSelectionModel().getSelectedItem();
            System.out.println(table.getSelectionModel().getSelectedItem());
            RS.Delete_Recipe(recipe.getRecipeId());
            table.setItems((ObservableList<Recipe>) rs.Afficher_Recipe());
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                    "Recette Supprimée avec succés!");
        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!", "Selectionner une recette!");
            return;
        }
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
    private void SelectChoix(ActionEvent event) {
        String s = ComboBox.getSelectionModel().getSelectedItem().toString();

    }
<<<<<<< Updated upstream
=======

    @FXML
    private void rateAction(ActionEvent event) throws IOException {
        if (sr.isRated(selectedId, userIdBox.getValue(), "Recipe")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/RateAlertUI.fxml"));
            Parent root = loader.load();
            RateAlertUIController c = loader.getController();
            c.setData(selectedId, userIdBox.getValue(), "Recipe");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/RatePopupUI.fxml"));
            Parent root = loader.load();
            RatePopupUIController c = loader.getController();
            c.setData(selectedId, userIdBox.getValue(), "Recipe");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void reportAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/ReportPopupUI.fxml"));
        Parent root = loader.load();
        ReportPopupUIController c = loader.getController();
        c.setData(selectedId, userIdBox.getValue(), "Recipe");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void Bouton_RetourCat(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/coheal/views/recipe/CreateRecipeCategoryUI.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
>>>>>>> Stashed changes
}
    

