/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.report;

import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class FilterItemController implements Initializable {

    @FXML
    private Label filterName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clearFilterAction(MouseEvent event) {
        
        AnchorPane p = (AnchorPane) filterName.getParent().getParent().getParent().getParent().getChildrenUnmodifiable().get(6);
        JFXCheckBox tasksCheckBox = (JFXCheckBox) p.getChildren().get(1);
        JFXCheckBox booksCheckBox = (JFXCheckBox) p.getChildren().get(2);
        JFXCheckBox eventsCheckBox = (JFXCheckBox) p.getChildren().get(3);
        JFXCheckBox sessionsCheckBox = (JFXCheckBox) p.getChildren().get(4);
        JFXCheckBox recipesCheckBox = (JFXCheckBox) p.getChildren().get(5);
        JFXCheckBox openCheckBox = (JFXCheckBox) p.getChildren().get(8);
        JFXCheckBox closedCheckBox = (JFXCheckBox) p.getChildren().get(9);
        
        switch (filterName.getText()) {
            case "Tasks":
                tasksCheckBox.setSelected(false);
                break;
            case "Events":
                eventsCheckBox.setSelected(false);
                break;
            case "Books":
                booksCheckBox.setSelected(false);
                break;
            case "Sessions":
                sessionsCheckBox.setSelected(false);
                break;
            case "Recipes":
                recipesCheckBox.setSelected(false);
                break;
            case "Open":
                openCheckBox.setSelected(false);
                break;
            case "Closed":
                closedCheckBox.setSelected(false);
                break;
        }

    }

    public void setData(String filterName) {
        this.filterName.setText(filterName);
    }

}
