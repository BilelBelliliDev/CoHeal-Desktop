/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.recipe;

import coheal.entities.recipe.Recipe;
import coheal.entities.recipe.RecipeCategory;
import coheal.services.recipe.RecipeCategoryService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CreateRecipeCategoryUIController implements Initializable {

    @FXML
    private TextField nomTF;
    @FXML
    private TextField imgTF;
    @FXML
    private TableView<RecipeCategory> table;
    @FXML
    private TableColumn<RecipeCategory, String> nom_col;
    @FXML
    private TableColumn<RecipeCategory, String> img_col;

    /**
     * Initializes the controller class.
     */
    RecipeCategoryService rc = new RecipeCategoryService();
    RecipeCategoryService rcs = new RecipeCategoryService();

    ObservableList<RecipeCategory> l = FXCollections.observableList(rc.Afficher_RecipeCategory());
    @FXML
    private Button GotoRecettesBT;

    boolean isSelected = false;
    @FXML
    private Button BoutonModifier;
    @FXML
    private Button BoutonSupprimer;
    @FXML
    private Button Retour2BT;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

    public void init() {
        System.out.println(l);
        nom_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        img_col.setCellValueFactory(new PropertyValueFactory<>("img_url"));
        table.setItems(l);

        table.setRowFactory(tv -> {
            TableRow<RecipeCategory> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    RecipeCategory rowData = row.getItem();
                    nomTF.setText(rowData.getName());
                    imgTF.setText(rowData.getImgUrl());
                    isSelected = true;
                }
            });
            return row;
        });
    }

    @FXML
    private void Bouton_Ajouter(ActionEvent event) throws SQLException {

        javafx.stage.Window owner = BoutonModifier.getScene().getWindow();
        if (nomTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!",
                    "Entrer un nom pour votre catégorie!");
            return;
        }
        if (imgTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!",
                    "Entrer l'URL de l'image!");
            return;
        }

        RecipeCategoryService rcs = new RecipeCategoryService();
        RecipeCategory RC = new RecipeCategory();
        RC.setName(nomTF.getText());
        RC.setImgUrl(imgTF.getText());
        rcs.Create_RecipeCategory(RC);
        table.setItems((ObservableList<RecipeCategory>) rcs.Afficher_RecipeCategory());

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                "Catégorie Ajoutée avec succés!");

    }

    @FXML
    private void Bouton_Modifier(ActionEvent event) {
        javafx.stage.Window owner = BoutonModifier.getScene().getWindow();
        if (isSelected) {
            RecipeCategory RC = new RecipeCategory();
            RecipeCategory rc = table.getSelectionModel().getSelectedItem();
            RC.setName(nomTF.getText());
            RC.setImgUrl(imgTF.getText());
            rcs.Update_RecipeCategory(RC, rc.getCatId());
            table.setItems((ObservableList<RecipeCategory>) rcs.Afficher_RecipeCategory());

            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                    "Catégorie Modifiée avec succés!");

        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!", "Selectionner une catégorie!");
            return;
        }
    }

    @FXML
    private void Bouton_Supprimer(ActionEvent event) {

        javafx.stage.Window owner = BoutonSupprimer.getScene().getWindow();
        if (isSelected) {
            RecipeCategory rc = new RecipeCategory();
            rc = table.getSelectionModel().getSelectedItem();
            System.out.println(table.getSelectionModel().getSelectedItem());
            rcs.Delete_RecipeCategory(rc.getCatId());
            table.setItems((ObservableList<RecipeCategory>) rcs.Afficher_RecipeCategory());

            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                    "Catégorie Supprimée avec succés!");
        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur!", "Selectionner une catégorie!");
            return;
        }
    }

    @FXML
    private void GotoRecettes_Button(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/coheal/views/recipeui/CreateRecipeUI.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
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
    private void Bouton_RetourMenu(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/coheal/views/MainMenuUI.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
