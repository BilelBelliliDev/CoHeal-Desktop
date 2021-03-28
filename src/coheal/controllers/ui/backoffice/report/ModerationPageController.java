/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.report;

import coheal.entities.report.Report;
import coheal.services.report.ReportService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class ModerationPageController implements Initializable {

    ReportService rs = new ReportService();
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private AnchorPane moderationPane;
    @FXML
    private TableView<Report> allReportsTable;
    @FXML
    private TableColumn<Report, Integer> reportIdCol;
    @FXML
    private TableColumn<Report, Integer> reporterIdCol;
    @FXML
    private TableColumn<Report, String> typeCol;
    @FXML
    private TableColumn<Report, String> noteCol;
    @FXML
    private TableColumn<Report, Boolean> statusCol;
    @FXML
    private TableColumn<Report, Timestamp> dateAndTimeCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reportIdCol.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        reporterIdCol.setCellValueFactory(new PropertyValueFactory<>("reporterId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        dateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        allReportsTable.setItems((ObservableList<Report>) rs.allReportsList());
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.setIconified(true);
    }

}
