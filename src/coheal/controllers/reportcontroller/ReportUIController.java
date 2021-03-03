/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.reportcontroller;

import coheal.entities.report.BookReport;
import coheal.entities.report.EventReport;
import coheal.entities.report.RecipeReport;
import coheal.entities.report.Report;
import coheal.entities.report.SessionReport;
import coheal.entities.report.TaskReport;
import coheal.entities.report.UserReport;
import coheal.services.report.ReportService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class ReportUIController implements Initializable {

    ReportService rs = new ReportService();
    @FXML
    private TextField reportId;
    @FXML
    private TextField userId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void reportBookAction(ActionEvent event) {
        Report r = new BookReport();
        r.setReporterId(1);
        r.setNote("First book report");
        rs.addReport(r, 2);
    }

    @FXML
    private void reportEventAction(ActionEvent event) {
        Report r = new EventReport();
        r.setReporterId(1);
        r.setNote("First event report");
        rs.addReport(r, 1);
    }

    @FXML
    private void reportUserAction(ActionEvent event) {
        Report r = new UserReport();
        r.setReporterId(2);
        r.setNote("First user report");
        rs.addReport(r, 1);
    }

    @FXML
    private void reportTaskAction(ActionEvent event) {
        Report r = new TaskReport();
        r.setReporterId(2);
        r.setNote("First task report");
        rs.addReport(r, 1);
    }

    @FXML
    private void reportSessionAction(ActionEvent event) {
        Report r = new SessionReport();
        r.setReporterId(2);
        r.setNote("First session report");
        rs.addReport(r, 1);
    }

    @FXML
    private void reportRecipeAction(ActionEvent event) {
        Report r = new RecipeReport();
        r.setReporterId(2);
        r.setNote("First recipe report");
        rs.addReport(r, 1);
    }

    @FXML
    private void closeReportAction(ActionEvent event) {
        rs.closeReport(Integer.parseInt(reportId.getText()));
    }

    @FXML
    private void unbanAction(ActionEvent event) {
        rs.unbanUser(Integer.parseInt(userId.getText()));
    }

    @FXML
    private void banAction(ActionEvent event) {
        rs.banUser(Integer.parseInt(userId.getText()));
    }
}
