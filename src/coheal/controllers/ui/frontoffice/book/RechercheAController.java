/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import animatefx.animation.ZoomIn;
import coheal.entities.book.Book;
import coheal.services.book.ServiceBook;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
public class RechercheAController implements Initializable {

        @FXML
        private ScrollPane bookPane;
        @FXML
        private GridPane bookGrid;
        ServiceBook sb = new ServiceBook();

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                // TODO
        }    
        public void function(String t){
                  int y = 0;
                int x = 0;

                new ZoomIn(bookPane).play();
                List<Book> books = new ArrayList<>();

                try {
                    int id=1;
                        books = sb.RechercheAvance(t);
                } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                }
                for (int i = 0; i < books.size(); i++) {
                        FXMLLoader loader = new FXMLLoader();

                        loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/book/BookItem.fxml"));

                        try {
                                System.out.print(books.get(i).getTitle());

                                Pane pane = loader.load();
                                BookItemController c = loader.getController();

                                c.setData(books.get(i));

                                if (x > 3) {
                                        y++;
                                        x = 0;
                                }
                                bookGrid.add(pane, x, y);

                                x++;
                        } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                        }

                }
        }

        @FXML
        private void close(MouseEvent event) {
                Stage stage = new Stage();
                stage = (Stage) bookGrid.getScene().getWindow();
                stage.close();
        }
        
}
