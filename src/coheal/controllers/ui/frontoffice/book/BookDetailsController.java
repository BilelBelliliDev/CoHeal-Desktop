/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.book;

import static coheal.services.book.Constants.projectPath;
import coheal.services.book.ServiceBook;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Marwen
 */
public class BookDetailsController implements Initializable {
        String nomf=null;
        File file = null;
        Desktop desktop = Desktop.getDesktop();
        int n=0;
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

       
        public void display(String title,String auth,String desc ,String img,String f,int id ){
                Image imageB =new Image("file:///" + projectPath + "\\src\\coheal\\resources\\images\\books\\" + img);
                 Image img2 =new Image("file:///" + projectPath + "\\src\\coheal\\resources\\images\\QRBook\\" + title+ ".jpg");
                imgbookv.setImage(imageB);
                qrv.setImage(img2);
                ttt.setText(title);
                aaa.setText(auth);
                dess.setText(desc);
                nomf=f;   
                n=id;
        }
         @FXML
        private void open(ActionEvent event) {
                        String url = projectPath + "\\src\\coheal\\resources\\images\\bookfiles\\" +nomf;
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
        
}
