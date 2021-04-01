/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.recipe.Recipe;
import coheal.entities.recipe.RecipeCategory;
import coheal.services.recipe.RecipeService;
import coheal.services.ui.UIService;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class RecipePageController implements Initializable {

    private String searchWord = "", comboValue = "All";
    @FXML
    private HBox categoriesHBox;
    private RecipeService st = new RecipeService();
    private UIService stc = new UIService();
    @FXML
    private ScrollPane recipePane;
    private GridPane recipeGrid;
    double xOffset, yOffset;
    @FXML
    private JFXButton addBtn;
    @FXML
    private Pagination pagination;
    @FXML
    private JFXComboBox<String> ComboBox;
    @FXML
    private JFXTextField searchRecipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserSession.getRole().equals("nutritionist")) {
            addBtn.setVisible(true);
        } 

        if (UserSession.getRole().equals("nutritionist")) {
            ComboBox.setVisible(true);
        }

        addBtn.setVisible(true);

        new ZoomIn(recipePane).play();

        ComboBox.getItems().add("All");
        ComboBox.getItems().add("Yours");
        ComboBox.getSelectionModel().select("All");

        List<RecipeCategory> catRecipes = stc.topThreeRecCatg();
        System.out.println(catRecipes.size());

        for (int i = 0; i < catRecipes.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/recipe/RecipeCategoryItem.fxml"));
            System.out.println(catRecipes.get(i));
            try {
                AnchorPane pane = loader.load();
                RecipeCategoryItemController c = loader.getController();
                c.setData(catRecipes.get(i));
                categoriesHBox.getChildren().add(pane);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        pagination.setPageFactory((pageindex) -> grid(pageindex, searchWord, comboValue));
    }

    public GridPane grid(int pageindex, String searchWord, String comboValue) {
        GridPane pane = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/recipe/GridRecipe.fxml"));
        try {
            pane = loader.load();
            GridRecipeController c = loader.getController();
            c.setData(pageindex, searchWord, comboValue);
            pagination.setPageCount(c.pageCount);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return pane;
    }

    @FXML
    private void addRecipeAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/recipe/AddRecipeF.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        HomePageHolderController hpc = new HomePageHolderController();
        hpc.setStage(stage);
        stage.show();
        root.setOnMousePressed((MouseEvent mouseEvent) -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent mouseEvent) -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
            stage.setOpacity(0.85f);
        });
        root.setOnMouseReleased((MouseEvent mouseEvent) -> {
            stage.setOpacity(1.0f);
        });
    }

    @FXML
    private void allCategories(MouseEvent event) throws IOException {
        AnchorPane pageHolder = (AnchorPane) addBtn.getParent().getParent().getParent().getParent().getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/recipe/AllCategories.fxml")));

    }

    @FXML
    private void ComboBoxRole(ActionEvent event) throws SQLException {

        if ("Yours".equals(ComboBox.getValue())) {
            comboValue = "Yours";
            pagination.setPageFactory((pageindex) -> grid(pageindex, searchWord, comboValue));

        } else if ("All".equals(ComboBox.getValue())) {
            comboValue = "All";
            pagination.setPageFactory((pageindex) -> grid(pageindex, searchWord, comboValue));

        }

    }

    @FXML
    private void rechercheRecipe(KeyEvent event) throws SQLException {
        List<Recipe> recipes = new ArrayList();
        if (!"".equals(searchRecipe.getText())) {
            searchWord = searchRecipe.getText();
            pagination.setPageFactory((pageindex) -> grid(pageindex, searchWord, comboValue));
        }

    }
}
