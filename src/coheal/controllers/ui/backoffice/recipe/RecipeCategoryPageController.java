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
import com.jfoenix.validation.RegexValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private ImageView selectedImg;
    @FXML
    private TableView<RecipeCategory> RecipeCatTable;
    @FXML
    private JFXButton UpdateBtn;
    @FXML
    private JFXButton DeleteBtn;
    @FXML
    private JFXTextField RechercheTF;
    @FXML
    private JFXTextField NameTF;
    @FXML
    private JFXTextField ImgUrlTF;

    RecipeCategoryService rcs = new RecipeCategoryService();
    boolean isSelected = false;
    RecipeCategory rc = new RecipeCategory();
    File f = null;
    double xOffset, yOffset;

//    //test textfield
//    boolean name = false;
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
                    NameTF.setText(rowData.getName());
                    ImgUrlTF.setText(rowData.getImgUrl());
                    selectedImg.setImage(rowData.getImg().getImage());
                    isSelected = true;
                }
            });
            return row;
        });

//        //appel contrôle saisie
//        nameValidatorUp();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/backoffice/recipe/AddCategoryF.fxml"));
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
            rc.setName(NameTF.getText());
            //image
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

//            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
//                    "Category modified successfully!");
            //Notification
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Success");
            tray.setMessage("Category updated successfully!");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        } else {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Select a category!");
            return;
        }
    }

//    public void nameValidatorUp() { //name Empty string
//        RegexValidator valid = new RegexValidator();
//        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
//        NameTF.setValidators(valid);
//        valid.setMessage("Enter the name");
//        NameTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
//                if (!newPropertyValue) {
//                    NameTF.validate();
//                    if (NameTF.validate()) {
//                        name = true;
//                    } else {
//                        name = false;
//                    }
//                }
//            }
//        });
//        try {
//            Image errorIcon = new Image(new FileInputStream("src/coheal/resources/images/cancel.png"));
//            valid.setIcon(new ImageView(errorIcon));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    @FXML
    private void DeleteCategory(ActionEvent event) {
        javafx.stage.Window owner = DeleteBtn.getScene().getWindow();

        if (isSelected) {
            RecipeCategory rc = new RecipeCategory();
            rc = RecipeCatTable.getSelectionModel().getSelectedItem();
            System.out.println(RecipeCatTable.getSelectionModel().getSelectedItem());
            rcs.Delete_RecipeCategory(rc.getCatId());
            RecipeCatTable.setItems((ObservableList<RecipeCategory>) rcs.Afficher_RecipeCategory());

//            showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
//                    "Category deleted successfully!");
            //Notification
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Success");
            tray.setMessage("Category deleted successfully!");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

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
    private void RechercherCategories(KeyEvent event) throws SQLException {
        RecipeCategoryService rcs = new RecipeCategoryService();
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        img_col.setCellValueFactory(new PropertyValueFactory<>("img"));
        String n = RechercheTF.getText();
        RecipeCatTable.setItems(rcs.RechercheCatAvance(n));
    }

    @FXML
    private void RefreshPage(MouseEvent event) {
        RecipeCategoryService rcs = new RecipeCategoryService();
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        img_col.setCellValueFactory(new PropertyValueFactory<>("img"));
        RecipeCatTable.setItems((ObservableList<RecipeCategory>) rcs.Afficher_RecipeCategory());
        RecipeCatTable.setRowFactory(tv -> {
            TableRow<RecipeCategory> row = new TableRow<>();
            row.setOnMouseClicked(Event -> {
                if (Event.getClickCount() == 1 && (!row.isEmpty())) {
                    RecipeCategory rowData = row.getItem();
                    NameTF.setText(rowData.getName());
                    ImgUrlTF.setText(rowData.getImgUrl());
                    selectedImg.setImage(rowData.getImg().getImage());
                    isSelected = true;
                }
            });
            return row;
        });
    }

}
