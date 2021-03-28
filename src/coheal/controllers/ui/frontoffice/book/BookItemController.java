/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import coheal.entities.book.Book;
import static coheal.services.book.Constants.projectPath;
import coheal.services.book.ServiceBook;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class BookItemController implements Initializable {

    Desktop desktop = Desktop.getDesktop();
    File file = null;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Book b) {
        bookTitle.setText(b.getTitle());
        bookAuthor.setText(b.getAuthor());
        bookViews.setText(String.valueOf(b.getViews()));
        id = b.getBookId();
        name = b.getFilePath();
    }

    private void openFile(File file) {
        try {
            desktop.open(file);

        } catch (IOException ex) {

        }
    }

    @FXML
    private void displayAction(MouseEvent event) {
        String url = projectPath + "\\src\\coheal\\controllers\\book\\" + name + ".pptx";
        System.out.println("------------------" + url);
        file = new File(url);
        openFile(file);
        
        sb.modifierV(id);
    }

}
