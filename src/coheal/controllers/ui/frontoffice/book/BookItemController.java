/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import coheal.controllers.ui.frontoffice.report.RateAlertUIController;
import coheal.controllers.ui.frontoffice.report.RatePopupUIController;
import coheal.controllers.ui.frontoffice.report.ReportPopupUIController;
import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.book.Book;
import static coheal.services.book.Constants.projectPath;
import coheal.services.book.ServiceBook;
import coheal.services.report.RateService;
import coheal.services.user.UserSession;
import java.awt.Desktop;
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
import javafx.scene.layout.AnchorPane;
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
        private int bookId=0;

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
      
        private boolean menuIsDisplayed = false;
        int uid= 0;
    @FXML
    private AnchorPane menuId;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                
                

        }

        public void setData(Book b) {
                bookId=b.getBookId();
                bookTitle.setText(b.getTitle());
                bookAuthor.setText(b.getAuthor());
                bookViews.setText(String.valueOf(b.getViews()));
                id = b.getBookId();
                name = b.getFilePath();
                Image img = new Image("file:///" + projectPath + "\\src\\coheal\\resources\\images\\books\\" + b.getImgUrl());
                bookimg.setImage(img);
        }

     

        @FXML
        private void displayAction(MouseEvent event) throws IOException, SQLException {
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
                 System.out.println(sb.Rechercher(id).get(0).getUserId());


        }

    @FXML
    private void reportAction(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/report/ReportPopupUI.fxml"));
        Parent root=null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(BookItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        ReportPopupUIController c = loader.getController();
        c.setData(bookId, UserSession.getUser_id(), "Book", bookTitle.getText());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void rateAction(MouseEvent event) throws IOException {
        RateService rs = new RateService();
        if (rs.isRated(bookId, UserSession.getUser_id(), "Book")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/report/RateAlertUI.fxml"));
            Parent root = loader.load();
            RateAlertUIController c = loader.getController();
            c.setData(bookId, UserSession.getUser_id(), "Book");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/report/RatePopupUI.fxml"));
            Parent root = loader.load();
            RatePopupUIController c = loader.getController();
            c.setData(bookId, UserSession.getUser_id(), "Book");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void dotsAction(MouseEvent event) {
        if (menuIsDisplayed) {           
            menuIsDisplayed = false;
            new ZoomOut(menuId).play();
            menuId.setDisable(true);
        } else {
            menuId.setVisible(true);
            menuId.setDisable(false);
            menuIsDisplayed = true;
            new ZoomIn(menuId).play();
        }
    }

       
        

}
