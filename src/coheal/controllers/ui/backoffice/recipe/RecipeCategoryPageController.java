/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.recipe;

import coheal.entities.recipe.RecipeCategory;
import static coheal.services.recipe.Constants.projectPath;
import coheal.services.recipe.RecipeCategoryService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RecipeCategoryPageController implements Initializable {

    @FXML
    private AnchorPane moderationPane;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private TableColumn<RecipeCategory, ImageView> img_col;
    @FXML
    private TableColumn<RecipeCategory, String> name_col;
    @FXML
    private TextField selectedName;
    @FXML
    private ImageView selectedImg;
    @FXML
    private TableView<RecipeCategory> RecipeCatTable;

    RecipeCategoryService rcs = new RecipeCategoryService();
    boolean isSelected = false;
    @FXML
    private TextField ImgUrl;
    double xOffset, yOffset;
    @FXML
    private JFXButton UpdateBtn;
    @FXML
    private JFXButton DeleteBtn;

    RecipeCategory rc = new RecipeCategory();
    File f = null;
    @FXML
    private JFXTextField RechercheTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<RecipeCategory> categories = rcs.Afficher_RecipeCategory();
        ObservableList<RecipeCategory> list = FXCollections.observableList((List<RecipeCategory>) categories);

        img_col.setCellValueFactory(new PropertyValueFactory<>("img"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        RecipeCatTable.setItems(list);

        RecipeCatTable.setRowFactory(tv -> {
            TableRow<RecipeCategory> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    RecipeCategory rowData = row.getItem();
                    selectedName.setText(rowData.getName());
                    ImgUrl.setText(rowData.getImgUrl());
                    selectedImg.setImage(rowData.getImg().getImage());
                    isSelected = true;
                }
            });
            return row;
        });

    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) RecipeCatTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) RecipeCatTable.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void AddCategoryAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/backoffice/recipe/AddCategory.fxml"));
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
    private void UpdateCategory(ActionEvent event) {
        javafx.stage.Window owner = UpdateBtn.getScene().getWindow();
        if (isSelected) {
            RecipeCategory rc = RecipeCatTable.getSelectionModel().getSelectedItem();
            rc.setName(selectedName.getText());
            File dest = null;
            if (f != null) {
                dest = new File(projectPath + "/src/coheal/resources/images/recipes/" + rc.getImgUrl());
            } else {
                System.out.println("Empty!");
            }

            try {
                if (dest != null) {
                    if (FileUtils.contentEquals(f, dest)) {
                        System.out.println("exists!");
                    } else {
                        FileUtils.copyFile(f, dest);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            rcs.Update_RecipeCategory(rc, rc.getCatId());
            RecipeCatTable.setItems((ObservableList<RecipeCategory>) rcs.Afficher_RecipeCategory());

            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                    "Category modified successfully!");

        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Select a category!");
            return;
        }
    }

    @FXML
    private void DeleteCategory(ActionEvent event) {
        javafx.stage.Window owner = DeleteBtn.getScene().getWindow();
        if (isSelected) {
            RecipeCategory rc = new RecipeCategory();
            rc = RecipeCatTable.getSelectionModel().getSelectedItem();
            System.out.println(RecipeCatTable.getSelectionModel().getSelectedItem());
            rcs.Delete_RecipeCategory(rc.getCatId());
            RecipeCatTable.setItems((ObservableList<RecipeCategory>) rcs.Afficher_RecipeCategory());

            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                    "Category deleted successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Select a category!");
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
    private void UpdateImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        f = fileChooser.showOpenDialog(null);
        img_col.setText(f.getName());
        rc.setImgUrl(f.getName());

    }

    @FXML
    private void RechercherCategories(MouseEvent event) throws SQLException {
        RecipeCategoryService rcs = new RecipeCategoryService();
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        img_col.setCellValueFactory(new PropertyValueFactory<>("img"));
        String n = RechercheTF.getText();
        RecipeCatTable.setItems(rcs.Recherche(n));
    }

}
