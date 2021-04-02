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
public class RecentReportItemController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private FontAwesomeIconView icon;

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
                label.setText("\""+r.getTitle()+"\" reported because of: \""+r.getNote()+"\"");
                break;
            case "book":
                icon.setGlyphName("BOOK");
                label.setText("\""+r.getTitle()+"\" reported because of: \""+r.getNote()+"\"");
                break;
            case "event":
                icon.setGlyphName("CALENDAR");
                label.setText("\""+r.getTitle()+"\" reported because of: \""+r.getNote()+"\"");
                break;
            case "session":
                icon.setGlyphName("USER_MD");
                label.setText("\""+r.getTitle()+"\" reported because of: \""+r.getNote()+"\"");
                break;
            case "recipe":
                icon.setGlyphName("STICKY_NOTE");
                label.setText("\""+r.getTitle()+"\" reported because of: \""+r.getNote()+"\"");
                break;
        }
    }
}
