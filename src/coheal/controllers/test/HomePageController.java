/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class HomePageController implements Initializable {

    @FXML
    private ScrollPane homePane;
    @FXML
    private HBox bookHBox;
    @FXML
    private HBox taskHBox;
    @FXML
    private HBox eventHBox;
    @FXML
    private HBox recipeHBox;
    @FXML
    private HBox sessionHBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
