/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.recipe;

import coheal.entities.recipe.Recipe;
import coheal.entities.recipe.RecipeCategory;
import coheal.services.recipe.RecipeCategoryService;
import coheal.services.recipe.RecipeService;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
public class RecipePageController implements Initializable {

    @FXML
    private AnchorPane moderationPane;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private TableView<Recipe> RecipeTable;
    @FXML
    private TableColumn<Recipe, ImageView> img_col;
    @FXML
    private TableColumn<Recipe, String> title_col;
    @FXML
    private TableColumn<Recipe, String> cat_col;
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
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<?, ?> lineChart;

    RecipeService rs = new RecipeService();
    RecipeCategory rc = new RecipeCategory();
    RecipeCategoryService rcs = new RecipeCategoryService();
    double xOffset, yOffset;
    @FXML
    private JFXTextField RechercheTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Recipe> recipes = rs.Afficher_Recipe();
        ObservableList<Recipe> list = FXCollections.observableList((List<Recipe>) recipes);

        img_col.setCellValueFactory(new PropertyValueFactory<>("img"));
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        Desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        cal_col.setCellValueFactory(new PropertyValueFactory<>("calories"));
        dur_col.setCellValueFactory(new PropertyValueFactory<>("duration"));
        persons_col.setCellValueFactory(new PropertyValueFactory<>("persons"));
        ingred_col.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
        Steps_col.setCellValueFactory(new PropertyValueFactory<>("steps"));
        RecipeTable.setItems(list);

        //----------------PieChart----------
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("Categories", rcs.Afficher_RecipeCategory().size()),
                new PieChart.Data("Categories with recipes", rcs.AfficherRecipesByIdCatg(rc.getName()).size()));
        pieChart.setTitle("Recipes");
        pieChart.setData(valueList);
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
//        //----------------LineChart----------
//        lineChart.setTitle("Number of recipes by date");
//        XYChart.Series dataSeries = new XYChart.Series();
//        dataSeries.setName("test");
//        for (int i = 0; i < rcs.getNbrParticipateByDate().size(); i++) {
//            dataSeries.getData().add(new XYChart.Data(String.valueOf(sut.getNbrParticipateByDate().get(i).getCreatedAt()), sut.getNbrParticipateByDate().get(i).getNbr()));
//        }
//        lineChart.getData().add(dataSeries);
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) RecipeTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) RecipeTable.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void ViewCategoryAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/backoffice/recipe/RecipeCategoryPage.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
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
    private void RechercheRecipes(KeyEvent event) throws SQLException {
        img_col.setCellValueFactory(new PropertyValueFactory<>("img"));
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        Desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        cal_col.setCellValueFactory(new PropertyValueFactory<>("calories"));
        dur_col.setCellValueFactory(new PropertyValueFactory<>("duration"));
        persons_col.setCellValueFactory(new PropertyValueFactory<>("persons"));
        ingred_col.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
        Steps_col.setCellValueFactory(new PropertyValueFactory<>("steps"));
        String n = RechercheTF.getText();
        RecipeTable.setItems(rs.RechercheRecipeAvance(n));
    }

}
