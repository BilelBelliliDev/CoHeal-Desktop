/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.recipe;

import coheal.controllers.report.RateAlertUIController;
import coheal.controllers.report.RatePopupUIController;
import coheal.entities.recipe.Recipe;
import coheal.services.recipe.RecipeService;
import coheal.services.report.RateService;
import coheal.services.user.ServiceUser;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CreateRecipeUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Report/Rate Module (Bilel Bellili)
    private ServiceUser su = new ServiceUser();
    private RateService sr = new RateService();
    private int selectedId;

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
    private ComboBox<Integer> userIdBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        //Report/Rate Module (Bilel Bellili)
        
        int num = su.AfficherPersonne().size();
        for (int i = 0; i < num; i++) {
            userIdBox.getItems().add(su.AfficherPersonne().get(i).getUserId());
        }
    }

    public void init() {
        System.out.println(l);
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        img_col.setCellValueFactory(new PropertyValueFactory<>("img_url"));

        table.setItems(l);

        table.setRowFactory(tv
                -> {
            TableRow<Recipe> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Recipe rowData = row.getItem();
                    TitreTF.setText(rowData.getTitle());
                    DescTF.setText(rowData.getDescription());
                    imgTF.setText(rowData.getImgUrl());
                    selectedId = table.getSelectionModel().getSelectedItem().getRecipeId();
                    isSelected = true;
                }
            });
            return row;
        }
        );
    }

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void Bouton_Ajouter(ActionEvent event) throws SQLException {
        RecipeService RS = new RecipeService();
        Recipe R = new Recipe();
        R.setCatId(1);
        R.setTitle(TitreTF.getText());
        R.setDescription(DescTF.getText());
        R.setImgUrl(imgTF.getText());

        RS.Create_Recipe(1, "Lunch", R);

        table.setItems((ObservableList<Recipe>) rs.Afficher_Recipe());
    }

    @FXML
    private void Bouton_Modifier(ActionEvent event) {

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
        } else {
            System.out.println("Sélectionner un objet!");
        }

    }

    @FXML
    private void Bouton_Supprimer(ActionEvent event) {
        if (isSelected) {
            RecipeService RS = new RecipeService();
            Recipe recipe = new Recipe();
            recipe = table.getSelectionModel().getSelectedItem();
            System.out.println(table.getSelectionModel().getSelectedItem());
            RS.Delete_Recipe(recipe.getRecipeId());
            table.setItems((ObservableList<Recipe>) rs.Afficher_Recipe());
        }
    }

    @FXML
    private void rateAction(ActionEvent event) throws IOException {
        if (sr.isRated(selectedId, userIdBox.getValue(), "Recipe")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/reportui/RateAlertUI.fxml"));
            Parent root = loader.load();
            RateAlertUIController c = loader.getController();
            c.setData(selectedId, userIdBox.getValue(), "Recipe");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/reportui/RatePopupUI.fxml"));
            Parent root = loader.load();
            RatePopupUIController c = loader.getController();
            c.setData(selectedId, userIdBox.getValue(), "Recipe");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void reportAction(ActionEvent event) {
    }
}
