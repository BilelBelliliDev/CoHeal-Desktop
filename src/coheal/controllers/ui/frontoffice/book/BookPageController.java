/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import coheal.controllers.ui.frontoffice.task.*;
import animatefx.animation.ZoomIn;

import coheal.entities.book.Book;
import coheal.entities.book.BookCategory;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.services.book.ServiceBook;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceTaskCategory;
import coheal.services.ui.UIService;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class BookPageController implements Initializable {

    private ScrollPane taskPane;
    @FXML
    private HBox categoriesHBox;
    private UIService stc = new UIService();
    
    private ServiceBook st=new ServiceBook();
    private GridPane taskGrid;
    @FXML
    private ScrollPane bookPane;
    @FXML
    private GridPane bookGrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
}
