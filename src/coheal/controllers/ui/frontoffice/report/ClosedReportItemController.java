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
public class ClosedReportItemController implements Initializable {

    @FXML
    private FontAwesomeIconView icon;
    @FXML
    private Label title;
    @FXML
    private Label note;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setDate(Report r) {
        switch (r.getType()) {
            case "task":
                icon.setGlyphName("TASKS");
                note.setText(r.getNote());
                title.setText(r.getTitle());
                break;
            case "book":
                icon.setGlyphName("BOOK");
                note.setText(r.getNote());
                title.setText(r.getTitle());
                break;
            case "event":
                icon.setGlyphName("CALENDAR");
                note.setText(r.getNote());
                title.setText(r.getTitle());
                break;
            case "session":
                icon.setGlyphName("USER_MD");
                note.setText(r.getNote());
                title.setText(r.getTitle());
                break;
            case "recipe":
                icon.setGlyphName("STICKY_NOTE");
                note.setText(r.getNote());
                title.setText(r.getTitle());
                break;
        }
    }
}
