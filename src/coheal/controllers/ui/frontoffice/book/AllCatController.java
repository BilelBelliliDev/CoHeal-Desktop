/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import coheal.entities.book.Book;
import coheal.entities.book.BookCategory;
import coheal.services.book.ServiceBookCategory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marwen
 */
public class AllCatController implements Initializable {

        @FXML
        private ScrollPane bookPane;
        @FXML
        private GridPane bookGrid;
            private ServiceBookCategory sbc = new ServiceBookCategory();

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                 int y = 0;
                int x = 0;
                List<BookCategory> books;
                try {
                        books = sbc.AfficherBookCat();
                        for (int i = 0; i < books.size(); i++) {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/book/BookCategoryItem.fxml"));
                                try {
                                        Pane pane = loader.load();
                                        BookCategoryItemController c = loader.getController();
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
        private void close(MouseEvent event) {
                Stage stage = new Stage();
                stage = (Stage) bookGrid.getScene().getWindow();
                stage.close();
        }
        
}
