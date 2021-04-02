/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.report;

import coheal.entities.report.Report;
import coheal.services.report.ReportService;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class ReportDashboardUIController implements Initializable {

    ReportService rs = new ReportService();
    @FXML
    private TableView<Report> allReportsTable;
    @FXML
    private TableColumn<Report, Integer> col1Id;
    @FXML
    private TableColumn<Report, Integer> col2Id;
    @FXML
    private TableColumn<Report, String> col3Id;
    @FXML
    private TableColumn<Report, String> col4Id;
    @FXML
    private TableColumn<Report, Boolean> col5Id;
    @FXML
    private TableColumn<Report, Timestamp> col6Id;
    @FXML
    private Label labelId;
    @FXML
    private TableColumn<Report, Integer> col1Id1;
    @FXML
    private TableColumn<Report, Integer> col2Id1;
    @FXML
    private TableColumn<Report, String> col3Id1;
    @FXML
    private TableColumn<Report, Boolean> col4Id1;
    @FXML
    private TableColumn<Report, Timestamp> col5Id1;
    @FXML
    private TableView<Report> reportsTable;
    private Timer timer = new Timer();
    private boolean isSelected = false;
    private int selectedId;
    @FXML
    private AnchorPane ancId;
    @FXML
    private CheckBox chk1;
    @FXML
    private CheckBox chk2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col1Id.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        col2Id.setCellValueFactory(new PropertyValueFactory<>("reporterId"));
        col3Id.setCellValueFactory(new PropertyValueFactory<>("type"));
        col4Id.setCellValueFactory(new PropertyValueFactory<>("note"));
        col5Id.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        col6Id.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        allReportsTable.setItems((ObservableList<Report>) rs.allReportsList());

    }

    @FXML
    private void allReportsAction(ActionEvent event) {
        labelId.setText("All Reports");
        allReportsTable.setVisible(true);
        reportsTable.setVisible(false);
        col1Id.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        col2Id.setCellValueFactory(new PropertyValueFactory<>("reporterId"));
        col3Id.setCellValueFactory(new PropertyValueFactory<>("type"));
        col4Id.setCellValueFactory(new PropertyValueFactory<>("note"));
        col5Id.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        col6Id.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        allReportsTable.setItems((ObservableList<Report>) rs.allReportsList());
        Stage stage = (Stage) ancId.getScene().getWindow();
        stage.setOnCloseRequest(e -> {
            timer.cancel();
            System.exit(0);
        });
    }

    @FXML
    private void bookReportsAction(ActionEvent event) {
        labelId.setText("Book Reports");
        allReportsTable.setVisible(false);
        reportsTable.setVisible(true);
        col1Id1.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        col2Id1.setCellValueFactory(new PropertyValueFactory<>("reporterId"));
        col3Id1.setCellValueFactory(new PropertyValueFactory<>("note"));
        col4Id1.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        col5Id1.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        reportsTable.setItems((ObservableList<Report>) rs.reportsList("book_report"));

        reportsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (reportsTable.getSelectionModel().getSelectedItem() != null) {
                    Report selectedReport = reportsTable.getSelectionModel().getSelectedItem();
                    selectedId = selectedReport.getReportId();
                    isSelected = true;
                }
            }
        });

        chk1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                rs.closedReportsList();
            }
        });
        

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (rs.reportsList("book_report").size() != reportsTable.getItems().size()) {
                    reportsTable.setItems((ObservableList<Report>) rs.reportsList("book_report"));
                }
            }
        }, 0, 1000);
        Stage stage = (Stage) ancId.getScene().getWindow();
        stage.setOnCloseRequest(e -> {
            timer.cancel();
            System.exit(0);
        });
    }

    @FXML
    private void recipeReportsAction(ActionEvent event) {
        labelId.setText("Recipe Reports");
        allReportsTable.setVisible(false);
        reportsTable.setVisible(true);
        col1Id1.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        col2Id1.setCellValueFactory(new PropertyValueFactory<>("reporterId"));
        col3Id1.setCellValueFactory(new PropertyValueFactory<>("note"));
        col4Id1.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        col5Id1.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        reportsTable.setItems((ObservableList<Report>) rs.reportsList("recipe_report"));
    }

    @FXML
    private void taskReportsAction(ActionEvent event) {
        labelId.setText("Task Reports");
        allReportsTable.setVisible(false);
        reportsTable.setVisible(true);
        col1Id1.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        col2Id1.setCellValueFactory(new PropertyValueFactory<>("reporterId"));
        col3Id1.setCellValueFactory(new PropertyValueFactory<>("note"));
        col4Id1.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        col5Id1.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        reportsTable.setItems((ObservableList<Report>) rs.reportsList("task_report"));
    }

    @FXML
    private void eventReportsAction(ActionEvent event) {
        labelId.setText("Event Reports");
        allReportsTable.setVisible(false);
        reportsTable.setVisible(true);
        col1Id1.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        col2Id1.setCellValueFactory(new PropertyValueFactory<>("reporterId"));
        col3Id1.setCellValueFactory(new PropertyValueFactory<>("note"));
        col4Id1.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        col5Id1.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        reportsTable.setItems((ObservableList<Report>) rs.reportsList("event_report"));
    }

    @FXML
    private void sessionReportsAction(ActionEvent event) {
        labelId.setText("Session Reports");
        allReportsTable.setVisible(false);
        reportsTable.setVisible(true);
        col1Id1.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        col2Id1.setCellValueFactory(new PropertyValueFactory<>("reporterId"));
        col3Id1.setCellValueFactory(new PropertyValueFactory<>("note"));
        col4Id1.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        col5Id1.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        reportsTable.setItems((ObservableList<Report>) rs.reportsList("session_report"));
    }

    @FXML
    private void closeReportAction(ActionEvent event) {
        if (isSelected) {
            rs.closeReport("");
            reportsTable.setItems((ObservableList<Report>) rs.reportsList("book_report"));
        }

    }

}
