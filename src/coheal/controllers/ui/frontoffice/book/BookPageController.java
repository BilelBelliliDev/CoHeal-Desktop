/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import coheal.controllers.ui.frontoffice.task.*;
import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.HomePageHolderController;

import coheal.entities.book.Book;
import coheal.entities.book.BookCategory;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.services.book.ServiceBook;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceTaskCategory;
import coheal.services.ui.UIService;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class BookPageController implements Initializable {

        double xOffset, yOffset;
        private ScrollPane taskPane;
        @FXML
        private HBox categoriesHBox;
        private UIService stc = new UIService();
        private ServiceBook st = new ServiceBook();
        private GridPane taskGrid;
        @FXML
        private ScrollPane bookPane;
        @FXML
        private GridPane bookGrid;
        @FXML
        private Pane item;
        @FXML
        private Label bookTitle;
        @FXML
        private Label bookAuthor;
        @FXML
        private Label bookViews;
        @FXML
        private ImageView bookimg;
        @FXML
        private JFXTextField recherchetf;
        @FXML
        private FontAwesomeIconView Appelbookadd;
       

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                  if(UserSession.getRole().equals("therapist"))
            Appelbookadd.setVisible(true);
                new ZoomIn(bookPane).play();
                List<BookCategory> catBooks = stc.topThreeBookCatg();
                for (int i = 0; i < catBooks.size(); i++) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/book/BookCategoryItem.fxml"));
                        try {
                                AnchorPane pane = loader.load();
                                BookCategoryItemController c = loader.getController();
                                c.setData(catBooks.get(i));
                                categoriesHBox.getChildren().add(pane);
                        } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                        }
                }
                int y = 0;
                int x = 0;
                List<Book> books;
                try {
                        books = st.AfficherBook2();
                        for (int i = 0; i < books.size(); i++) {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/book/BookItem.fxml"));
                                try {
                                        Pane pane = loader.load();
                                        BookItemController c = loader.getController();
                                        c.setData(books.get(i));
                                        if (x > 2) {
                                                y++;
                                                x = 0;
                                        }
                                        bookGrid.add(pane, x, y);
                                        x++;
                                } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                }
                        }
                } catch (SQLException ex) {
                        Logger.getLogger(BookPageController.class.getName()).log(Level.SEVERE, null, ex);
                }

        }

        @FXML
        private void Appelbookadd(MouseEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/book/addbook.fxml"));
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
        private void searchaction(MouseEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/book/rechercheA.fxml"));
                Parent root = loader.load();
                RechercheAController r = loader.getController();
                r.function(recherchetf.getText());
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
        private void allcat(MouseEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/book/allCat.fxml"));
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
        private void refresh(MouseEvent event) {
                 int y = 0;
                int x = 0;
                List<Book> books;
                try {
                        books = st.AfficherBook2();
                        for (int i = 0; i < books.size(); i++) {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/book/BookItem.fxml"));
                                try {
                                        Pane pane = loader.load();
                                        BookItemController c = loader.getController();
                                        c.setData(books.get(i));
                                        if (x > 2) {
                                                y++;
                                                x = 0;
                                        }
                                        bookGrid.add(pane, x, y);
                                        x++;
                                } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                }
                        }
                } catch (SQLException ex) {
                        Logger.getLogger(BookPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

}
