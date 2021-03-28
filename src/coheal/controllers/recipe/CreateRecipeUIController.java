/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.recipe;

import coheal.controllers.report.RateAlertUIController;
import coheal.controllers.report.RatePopupUIController;
import coheal.controllers.report.ReportPopupUIController;
import coheal.entities.recipe.Recipe;
import coheal.entities.task.TaskCategory;
import static coheal.services.recipe.Constants.projectPath;
import coheal.services.recipe.RecipeCategoryService;
import coheal.services.recipe.RecipeService;
import coheal.services.report.RateService;
import coheal.services.user.ServiceUser;
import com.jfoenix.controls.JFXComboBox;
import java.awt.Desktop;
import java.io.File;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

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
    private Label imgTF;
    @FXML
    private TableColumn<Recipe, String> title_col;
    @FXML
    private TableColumn<Recipe, String> desc_col;
    @FXML
    private TableColumn<Recipe, ImageView> img_col;
    @FXML
    private TableColumn<Recipe, String> ingred_col;
    @FXML
    private TableColumn<Recipe, String> steps_col;
    @FXML
    private TableColumn<Recipe, String> duration_col;
    @FXML
    private TableColumn<Recipe, String> persons_col;
    @FXML
    private TableColumn<Recipe, String> calories_col;
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
    private ComboBox ComboBox;
    @FXML
    private ComboBox<Integer> userIdBox;

    File file = null;
    @FXML
    private Button AjouterImageBT;
    @FXML
    private TextField DurationTF;
    @FXML
    private TextField PersonsTF;
    @FXML
    private TextArea IngredientsTF;
    @FXML
    private TextArea StepsTF;
    @FXML
    private TextField CaloriesTF;
    String cat = "";
    @FXML
    private JFXComboBox<String> catBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();

        //Report/Rate Module (Bilel Bellili)      
        int num = su.AfficherPersonne().size();
        for (int i = 0; i < num; i++) {
            userIdBox.getItems().add(su.AfficherPersonne().get(i).getUserId());
        }
        //comboBox
        RecipeCategoryService rcs = new RecipeCategoryService();
        for (int i = 0; i < rcs.Afficher_RecipeCategory().size(); i++) {
            catBox.getItems().add(rcs.Afficher_RecipeCategory().get(i).getName());
    }}

    public void init() {
        System.out.println(l);
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        img_col.setCellValueFactory(new PropertyValueFactory<Recipe, ImageView>("img"));
        ingred_col.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
        steps_col.setCellValueFactory(new PropertyValueFactory<>("steps"));
        calories_col.setCellValueFactory(new PropertyValueFactory<>("calories"));
        duration_col.setCellValueFactory(new PropertyValueFactory<>("duration"));
        persons_col.setCellValueFactory(new PropertyValueFactory<>("persons"));
        table.setItems(l);
        table.setRowFactory(tv -> {
            TableRow<Recipe> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Recipe rowData = row.getItem();
                    TitreTF.setText(rowData.getTitle());
                    DescTF.setText(rowData.getDescription());
                    imgTF.setText(rowData.getImgUrl());
                    IngredientsTF.setText(rowData.getIngredients());
                    StepsTF.setText(rowData.getSteps());
                    String c = "" + rowData.getCalories();
                    String d = "" + rowData.getDuration();
                    String p = "" + rowData.getPersons();
                    CaloriesTF.setText(c);
                    DurationTF.setText(d);
                    PersonsTF.setText(p);
                    selectedId = table.getSelectionModel().getSelectedItem().getRecipeId();
                    isSelected = true;
                }
            });
            return row;
        });
    }

    @FXML
    private void Bouton_Ajouter(ActionEvent event) throws SQLException, IOException {

        javafx.stage.Window owner = BoutonAjouter.getScene().getWindow();
        if (TitreTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter a title for your recipe!");
            return;
        }
        if (DescTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Describe your recipe!");
            return;
        }
        if (IngredientsTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter the required ingredients for your recipe !");
            return;
        }
        if (StepsTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter the steps of preparing of your recipe !");
            return;
        }
        if (CaloriesTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter the number of calories of your recipe !");
            return;
        }
        if (DurationTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter the  required ingredients for your recipe !");
            return;
        }
        if (PersonsTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter the number of persons of your recipe !");
            return;
        }

        RecipeService RS = new RecipeService();
        Recipe R = new Recipe();
        R.setTitle(TitreTF.getText());
        R.setDescription(DescTF.getText());
        R.setIngredients(IngredientsTF.getText());
        R.setSteps(StepsTF.getText());
        float c = Float.parseFloat(CaloriesTF.getText());
        R.setCalories(c);
        int d = Integer.parseInt(DurationTF.getText());
        R.setDuration(d);
        int p = Integer.parseInt(PersonsTF.getText());
        R.setDuration(p);

        File dest = new File(projectPath + "src/coheal/images/recipe" + file.getName());
        FileUtils.copyFile(file, dest);

        RS.Create_Recipe(1, cat, R);
        table.setItems((ObservableList<Recipe>) rs.Afficher_Recipe());

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                "Recipe added successfully!");
    }

    Desktop desktop = Desktop.getDesktop();
    FileChooser fileChooser = new FileChooser();

    @FXML
    private void Addimage(ActionEvent event) {
        Stage stage = null;
        file = fileChooser.showOpenDialog(stage);
        imgTF.setText(file.getName());
    }

    @FXML
    private void Bouton_Modifier(ActionEvent event) {
        javafx.stage.Window owner = BoutonModifier.getScene().getWindow();
        if (isSelected) {
            Recipe r = table.getSelectionModel().getSelectedItem();
            RecipeService RS = new RecipeService();

            Recipe R = new Recipe();
            R.setTitle(TitreTF.getText());
            R.setDescription(DescTF.getText());
            R.setIngredients(IngredientsTF.getText());
            R.setSteps(StepsTF.getText());
            float c = Float.parseFloat(CaloriesTF.getText());
            R.setCalories(c);
            int d = Integer.parseInt(DurationTF.getText());
            R.setDuration(d);
            int p = Integer.parseInt(PersonsTF.getText());
            R.setDuration(p);

            RS.Update_Recipe(R, r.getRecipeId());
            table.setItems((ObservableList<Recipe>) rs.Afficher_Recipe());

            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                    "Recipe modified successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Select a recipe!");
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
                    "Recipe deleted successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Select a recipe!");
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

    private void SelectChoix(ActionEvent event) {
        String s = ComboBox.getSelectionModel().getSelectedItem().toString();
    }

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

    private void Bouton_RetourCat(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/coheal/views/recipe/CreateRecipeCategoryUI.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
