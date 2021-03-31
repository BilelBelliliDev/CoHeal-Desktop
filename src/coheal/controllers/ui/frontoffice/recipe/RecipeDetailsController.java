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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

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
    @FXML
    private FontAwesomeIconView printBtn;

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
            printBtn.setVisible(false);
        }

        if (r != null) {
            TitleLabel.setText(r.getTitle());
            ImageView.setImage(r.getImg().getImage());
            DescLabel.setText(r.getDescription());
            IngredientsLabel.setText(r.getIngredients());
            StepsLabel.setText(r.getSteps());
            CaloriesLabel.setText(String.valueOf(r.getCalories()));
            PersonsLabel.setText(String.valueOf(r.getPersons()));
            DurationLabel.setText(String.valueOf(r.getDuration()));
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
    private void deleteRecipe(MouseEvent event) {
        javafx.stage.Window owner = deleteIcon.getScene().getWindow();

        rs.Delete_Recipe(rh.getId());
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                "Recipe deleted successfully!");

    }

    @FXML
    private void backAction(MouseEvent event) throws IOException {
        AnchorPane pageHolder = (AnchorPane) RecipesPane.getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/recipe/RecipePage.fxml")));

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
    private void PdftoPrint(MouseEvent event) throws FileNotFoundException, DocumentException, IOException {
        javafx.stage.Window owner = printBtn.getScene().getWindow();

        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/MyRecipe.pdf"));
            document.open();

            Paragraph p1 = new Paragraph();
            p1.add("\n" + TitleLabel.getText() + "\n\n");
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            Paragraph p2 = new Paragraph();
            p2.add(DescLabel.getText() + "\n\n\n\n");
            p2.setAlignment(Element.ALIGN_CENTER);
            document.add(p2);

            Paragraph p3 = new Paragraph();
            p3.add("Ingredients: \n\n" + IngredientsLabel.getText() + "\n\n\n\n");
            p3.setAlignment(Element.ALIGN_LEFT);
            document.add(p3);

            Paragraph p4 = new Paragraph();
            p4.add("Steps: \n\n" + StepsLabel.getText() + "\n\n\n\n");
            p4.setAlignment(Element.ALIGN_LEFT);
            document.add(p4);

            document.close();
            writer.close();
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                "This recipe got transported to D:/ in a PDF file as MyRecipe.pdf ");

    }

}
