/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;


import coheal.controllers.ui.frontoffice.task.*;
import coheal.entities.book.BookCategory;
import coheal.entities.task.TaskCategory;
import static coheal.services.book.Constants.projectPath;
import coheal.services.task.ServiceTaskCategory;
import coheal.services.ui.UIService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class BookCategoryItemController implements Initializable {


    @FXML
    private ImageView bookCatgImg;
    @FXML
    private Label bookCatgTitle;
    @FXML
    private Label bookCatgTotalTasks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(BookCategory tc){
        UIService stc=new UIService();
        bookCatgTitle.setText(tc.getName());
        bookCatgTotalTasks.setText(String.valueOf(stc.ListerBooksByIdCatg(tc.getName()).size())+" Books");
        Image imgC= new Image("file:///" + projectPath + "\\src\\coheal\\resources\\images\\bookCat\\" + tc.getImgUrl());
        bookCatgImg.setImage(imgC);
        
    }

}
