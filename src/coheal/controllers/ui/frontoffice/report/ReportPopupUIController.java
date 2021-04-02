/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.report;

import coheal.controllers.report.*;
import coheal.entities.report.BookReport;
import coheal.entities.report.EventReport;
import coheal.entities.report.RecipeReport;
import coheal.entities.report.Report;
import coheal.entities.report.SessionReport;
import coheal.entities.report.TaskReport;
import coheal.services.report.ReportService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class ReportPopupUIController implements Initializable {

    private int id, userId;
    private String s,bookTitle;
    @FXML
    private Label dataId;

    @FXML
    private TextArea noteId;
    @FXML
    private Button reportBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void reportAction(ActionEvent event) {
        Report r;
        Stage stage;
        ReportService rs = new ReportService();
        switch (s) {
            case "Book":
                r = new BookReport();
                r.setReporterId(userId);
                r.setNote(noteId.getText());
                r.setTitle(bookTitle);
                rs.addReport(r, id);
                stage = (Stage) reportBtn.getScene().getWindow();
                stage.close();
                break;
            case "Recipe":
                r = new RecipeReport();
                r.setReporterId(userId);
                r.setNote(noteId.getText());
                r.setTitle(bookTitle);
                rs.addReport(r, id);
                stage = (Stage) reportBtn.getScene().getWindow();
                stage.close();
                break;
            case "Task":
                r = new TaskReport();
                r.setReporterId(userId);
                r.setNote(noteId.getText());
                r.setTitle(bookTitle);
                rs.addReport(r, id);
                stage = (Stage) reportBtn.getScene().getWindow();
                stage.close();
                break;
            case "Event":
                r = new EventReport();
                r.setReporterId(userId);
                r.setNote(noteId.getText());
                r.setTitle(bookTitle);
                rs.addReport(r, id);
                stage = (Stage) reportBtn.getScene().getWindow();
                stage.close();
                break;
            case "Session":
                r = new SessionReport();
                r.setReporterId(userId);
                r.setNote(noteId.getText());
                r.setTitle(bookTitle);
                rs.addReport(r, id);
                stage = (Stage) reportBtn.getScene().getWindow();
                stage.close();
                break;
        }

    }

    @FXML
    private void cancelAction(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public int getId() {
        return id;
    }

    public void setData(int id, int userId, String s, String bookTitle) {
        this.id = id;
        this.userId = userId;
        this.s = s;
        this.bookTitle=bookTitle;
        dataId.setText(s + " id: " + id + ", User id: " + userId);
    }

}
