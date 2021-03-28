/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.book.Book;
import static coheal.services.book.Constants.projectPath;
import coheal.services.book.ServiceBook;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class BookItemController implements Initializable {

        double xOffset, yOffset;

        Desktop desktop = Desktop.getDesktop();
        
        public int id;
        ServiceBook sb = new ServiceBook();
        String name = null;

        @FXML
        private ImageView bookimg;
        @FXML
        private Label bookTitle;
        @FXML
        private Label bookAuthor;
        @FXML
        private Label bookViews;
        @FXML
        private Pane item;
        @FXML
        private FontAwesomeIconView trash;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                 if(UserSession.getRole().equals("therapist"))
            trash.setVisible(true);

        }

        public void setData(Book b) {
                bookTitle.setText(b.getTitle());
                bookAuthor.setText(b.getAuthor());
                bookViews.setText(String.valueOf(b.getViews()));
                id = b.getBookId();
                name = b.getFilePath();
                Image img = new Image("file:///" + projectPath + "\\src\\coheal\\resources\\images\\books\\" + b.getImgUrl());
                bookimg.setImage(img);

        }

     

        @FXML
        private void displayAction(MouseEvent event) throws IOException {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/book/bookDetails.fxml"));
                Parent root = loader.load();
                BookDetailsController bdc=loader.getController();
                try {
                        bdc.display(sb.Rechercher(id).get(0).getTitle(),sb.Rechercher(id).get(0).getAuthor(),sb.Rechercher(id).get(0).getDescription(),sb.Rechercher(id).get(0).getImgUrl(),sb.Rechercher(id).get(0).getFilePath(),id);
                } catch (SQLException ex) {
                        Logger.getLogger(BookItemController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        private void delBo(MouseEvent event) {
                sb.supprimerBook(id);
        }

}
