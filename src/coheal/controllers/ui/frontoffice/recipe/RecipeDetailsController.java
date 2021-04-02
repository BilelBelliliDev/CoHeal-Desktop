/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.recipe.Recipe;
import static coheal.services.recipe.Constants.projectPath;
import coheal.services.recipe.RecipeService;
import coheal.services.report.RateService;
import coheal.services.user.UserSession;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
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
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    @FXML
    private Label Imgurl;
    @FXML
    private Rating ratingId;
    @FXML
    private Label rateLabel;
    RateService rateService = new RateService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(rateService.ratesListById("recipe", rh.getId())==0){
            rateLabel.setVisible(true);
            ratingId.setVisible(false);
        }else {        
            System.out.println(rateService.ratesListById("recipe", rh.getId()));
            ratingId.setRating(rateService.ratesListById("recipe", rh.getId()));
            rateLabel.setVisible(false);
            ratingId.setVisible(true);
        }
        RecipeHolder rh = RecipeHolder.getINSTANCE();
        Recipe r = rs.getRecipe(rh.getId());
        
        if (UserSession.getRole().equals("nutritionist") && r.getUser().getUserId() == UserSession.getUser_id()) {
            updateIcon.setVisible(true);
            deleteIcon.setVisible(true);
            printBtn.setVisible(false);
        }

        if (r != null) {
            //sets
            TitleLabel.setText(r.getTitle());
            ImageView.setImage(r.getImg().getImage());
            DescLabel.setText(r.getDescription());
            IngredientsLabel.setText(r.getIngredients());
            StepsLabel.setText(r.getSteps());
            CaloriesLabel.setText(String.valueOf(r.getCalories()));
            PersonsLabel.setText(String.valueOf(r.getPersons()));
            DurationLabel.setText(String.valueOf(r.getDuration()));
            Imgurl.setText(r.getImgUrl());

        }
    }

    @FXML
    private void updateRecipe(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/recipe/UpdateRecipeF.fxml"));
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

//        showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
//                "Recipe deleted successfully!");

        //Notification
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Success");
        tray.setMessage("Recipe deleted successfully!");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

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

    //My API with itextpdf
    @FXML
    private void PdftoPrint(MouseEvent event) throws FileNotFoundException, DocumentException, IOException {
        javafx.stage.Window owner = printBtn.getScene().getWindow();

        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/" + TitleLabel.getText() + ".pdf"));
            document.open();

            //title
            Font orange = new Font(FontFamily.HELVETICA, 30, Font.BOLDITALIC, BaseColor.ORANGE);
                      //use chunk to set the font
            Chunk ch1 = new Chunk("\n" + TitleLabel.getText() + "\n\n", orange);
            Paragraph p1 = new Paragraph(ch1);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            //Description  
            Font desc = new Font(FontFamily.HELVETICA, 20, Font.NORMAL, BaseColor.BLACK);
            Chunk ch2 = new Chunk(DescLabel.getText() + "\n\n", desc);
            Paragraph p2 = new Paragraph(ch2);
            p2.setAlignment(Element.ALIGN_CENTER);
            document.add(p2);

            //Details
            String pathc = projectPath + "/src/coheal/resources/images/recipes/details.png";
            Image calImg = Image.getInstance(pathc);
            calImg.scaleAbsolute(471, 78);
            calImg.setAlignment(Element.ALIGN_CENTER);
            document.add(calImg);

            Paragraph c = new Paragraph();
            c.add("                                         " + CaloriesLabel.getText() + "                                 " + DurationLabel.getText() + "                                      " + PersonsLabel.getText() + "\n\n\n\n");
            c.setAlignment(Element.ALIGN_LEFT);
            document.add(c);

            //image
            String path = projectPath + "/src/coheal/resources/images/recipes/" + Imgurl.getText();
            Image image = Image.getInstance(path);
            image.scaleAbsolute(300, 188);
            image.setAlignment(Element.ALIGN_CENTER);
            image.setBorderWidth(3);
            image.setBorderWidthBottom(3);
            image.setBorderWidthRight(3);
            image.setBorderWidthTop(3);
            image.setBorderWidthLeft(3);
            image.setBorderColor(BaseColor.BLACK);
            document.add(image);

            //labels Font
            Font titre = new Font(FontFamily.HELVETICA, 25, Font.BOLDITALIC, BaseColor.RED);

            Chunk ch3 = new Chunk("\n\n Ingredients: \n\n", titre);
            Paragraph p3 = new Paragraph(ch3);
            p3.setAlignment(Element.ALIGN_LEFT);
            document.add(p3);

            //text Font
            Font text = new Font(FontFamily.HELVETICA, 18, Font.NORMAL, BaseColor.BLACK);

            Chunk ch4 = new Chunk(IngredientsLabel.getText() + "\n\n\n", text);
            Paragraph p4 = new Paragraph(ch4);
            p4.setAlignment(Element.ALIGN_LEFT);
            document.add(p4);

            Chunk ch5 = new Chunk("Steps: \n\n", titre);
            Paragraph p5 = new Paragraph(ch5);
            p3.setAlignment(Element.ALIGN_LEFT);
            document.add(p5);

            Chunk ch6 = new Chunk(StepsLabel.getText() + "\n\n\n\n", text);
            Paragraph p6 = new Paragraph(ch6);
            p6.setAlignment(Element.ALIGN_LEFT);
            document.add(p6);

            document.close();
            writer.close();
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Confirmation!",
                "This recipe got transported to D:/ in a PDF file as       « " + TitleLabel.getText() + " »");

    }

}
