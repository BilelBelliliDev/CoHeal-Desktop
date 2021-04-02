/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.report;

import coheal.entities.report.Report;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class UrgentReportItemController implements Initializable {

    @FXML
    private FontAwesomeIconView icon;
    @FXML
    private Label text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Report r){
        switch (r.getType()) {
            case "task":
                icon.setGlyphName("TASKS");
                text.setText("\""+r.getTitle()+"\" reported "+r.getTotal()+" times");
                break;
            case "book":
                icon.setGlyphName("BOOK");
                text.setText("\""+r.getTitle()+"\" reported "+r.getTotal()+" times");
                break;
            case "event":
                icon.setGlyphName("CALENDAR");
                text.setText("\""+r.getTitle()+"\" reported "+r.getTotal()+" times");
                break;
            case "session":
                icon.setGlyphName("USER_MD");
                text.setText("\""+r.getTitle()+"\" reported "+r.getTotal()+" times");
                break;
            case "recipe":
                icon.setGlyphName("STICKY_NOTE");
                text.setText("\""+r.getTitle()+"\" reported "+r.getTotal()+" times");
                break;
        }
    }
}