/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.recipe.Recipe;
import coheal.services.recipe.RecipeService;
import coheal.services.user.UserSession;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RecipeDetailsController implements Initializable {

    @FXML
    private Label TitleLabel;
    @FXML
    private Label DescLabel;
    @FXML
    private ImageView ImageView;
    @FXML
    private Label IngredientsLabel;
    @FXML
    private Label StepsLabel;
    @FXML
    private Label CaloriesLabel;
    @FXML
    private Label DurationLabel;
    @FXML
    private FontAwesomeIconView deleteIcon;
    @FXML
    private FontAwesomeIconView updateIcon;
    @FXML
    private Label PersonsLabel;
    @FXML
    private ScrollPane RecipesPane;

    Recipe r;
    RecipeService rs = new RecipeService();
    RecipeHolder rh = RecipeHolder.getINSTANCE();
    double xOffset, yOffset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RecipeHolder rh = RecipeHolder.getINSTANCE();
        Recipe r = rs.getRecipe(rh.getId());
        if (UserSession.getRole().equals("nutritionist") && r.getUser().getUserId() == UserSession.getUser_id()) {
            updateIcon.setVisible(true);
            deleteIcon.setVisible(true);
        }

        if (r != null) {
            TitleLabel.setText(r.getTitle());
            ImageView.setImage(r.getImg().getImage());
            DescLabel.setText(r.getDescription());
            IngredientsLabel.setText(r.getIngredients());
            StepsLabel.setText(r.getSteps());
            CaloriesLabel.setText(String.valueOf(r.getCalories()));
            DurationLabel.setText(String.valueOf(r.getDuration()));
            PersonsLabel.setText(String.valueOf(r.getPersons()));;
        }
    }

    @FXML
    private void updateRecipe(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/recipe/UpdateRecipe.fxml"));
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
    private void deleteRecipe(MouseEvent event
    ) {
        rs.Delete_Recipe(rh.getId());
    }

    @FXML
    private void backAction(MouseEvent event) throws IOException {
        AnchorPane pageHolder = (AnchorPane) RecipesPane.getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/recipe/RecipePage.fxml")));

    }
}
