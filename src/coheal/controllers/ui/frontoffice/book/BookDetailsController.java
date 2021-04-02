/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import static coheal.services.book.Constants.projectPath;
import coheal.services.book.ServiceBook;
import coheal.services.report.RateService;
import coheal.services.user.UserSession;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Marwen
 */
public class BookDetailsController implements Initializable {

    double xOffset, yOffset;

    String nomf = null;
    File file = null;
    String t = null;
    String a = null;
    String d = null;
    Desktop desktop = Desktop.getDesktop();
    int n = 0;
    ServiceBook sb = new ServiceBook();

    @FXML
    private ImageView imgbookv;
    @FXML
    private ImageView qrv;
    @FXML
    private Label ttt;
    @FXML
    private Label aaa;
    @FXML
    private Label dess;
    @FXML
    private FontAwesomeIconView trash;
    @FXML
    private FontAwesomeIconView modif;
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
        
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) aaa.getScene().getWindow();
        stage.close();
    }

    public void display(String title, String auth, String desc, String img, String f, int id) {
        if (rateService.ratesListById("book", id) == 0) {
            rateLabel.setVisible(true);
            ratingId.setVisible(false);
        } else {
            System.out.println(rateService.ratesListById("book", id));
            ratingId.setRating(rateService.ratesListById("book", id));
            rateLabel.setVisible(false);
            ratingId.setVisible(true);
        }
        try {
            if (UserSession.getRole().equals("therapist") && sb.Rechercher(id).get(0).getUserId() == UserSession.getUser_id()) {
                trash.setVisible(true);
                modif.setVisible(true);
            }
            if(UserSession.getRole().equals("moderator")){
                trash.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image imageB = new Image("file:///" + projectPath + "\\src\\coheal\\resources\\images\\books\\" + img);
        Image img2 = new Image("file:///" + projectPath + "\\src\\coheal\\resources\\images\\QRBook\\" + title + ".jpg");
        imgbookv.setImage(imageB);
        qrv.setImage(img2);
        ttt.setText(title);
        aaa.setText(auth);
        dess.setText(desc);
        nomf = f;
        n = id;
        t = title;
        a = auth;
        d = desc;
    }

    @FXML
    private void open(ActionEvent event) {
        String url = projectPath + "\\src\\coheal\\resources\\images\\bookfiles\\" + nomf;
        System.out.println("------------------" + url);
        file = new File(url);
        openFile(file);

        sb.modifierV(n);
    }

    private void openFile(File file) {
        try {
            desktop.open(file);

        } catch (IOException ex) {

        }
    }

    @FXML
    private void supp(MouseEvent event) {
        sb.supprimerBook(n);
    }

    @FXML
    private void modifier(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/book/updateBook.fxml"));
        Parent root = loader.load();
        UpdateBookController ubc = loader.getController();
        ubc.display(t, a, d, n);
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

}
