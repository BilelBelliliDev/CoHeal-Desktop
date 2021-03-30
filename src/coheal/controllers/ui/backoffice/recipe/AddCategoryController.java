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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddCategoryController implements Initializable {

    @FXML
    private JFXTextField Title;
    @FXML
    private Label imageTxt;
    File f = null;
    RecipeCategory rc = new RecipeCategory();
    @FXML
    private JFXButton AddBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        f = chooser.showOpenDialog(null);
        imageTxt.setText(f.getName());
        System.out.println(f.getName());
        rc.setImgUrl(f.getName());
        System.out.println(rc.getImgUrl());
    }

    @FXML
    private void addRecipeCategoryAction(ActionEvent event) {
        javafx.stage.Window owner = AddBtn.getScene().getWindow();
        if (Title.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Enter a title for your category!");
            return;
        }
        
        RecipeCategoryService rcs = new RecipeCategoryService();
        rc.setName(Title.getText());
        File dest = new File(projectPath + "/src/coheal/resources/images/recipes/" + f.getName());
        try {
            FileUtils.copyFile(f, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rcs.Create_RecipeCategory(rc);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                "Category added successfully!");

    }


@FXML
        private void closeAction(MouseEvent event) {
         Stage stage = new Stage();
        stage = (Stage) AddBtn.getScene().getWindow();
        stage.close();
    }

   private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
