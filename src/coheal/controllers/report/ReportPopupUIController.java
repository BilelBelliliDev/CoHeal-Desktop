/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.report;

import coheal.entities.report.BookReport;
import coheal.entities.report.Report;
import coheal.services.report.ReportService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private String s;
    @FXML
    private Label dataId;
    ReportService rs = new ReportService();
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
        Report r = new BookReport();
        r.setReporterId(userId);
        r.setNote(noteId.getText());
        rs.addReport(r, id);
        Stage stage = (Stage) reportBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public int getId() {
        return id;
    }

    public void setData(int id, int userId, String s) {
        this.id = id;
        this.userId=userId;
        this.s=s;
        dataId.setText(s+" id: "+id+", User id: "+userId);
    }

}
