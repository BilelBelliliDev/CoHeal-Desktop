/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.report;

import coheal.controllers.ui.frontoffice.report.UrgentReportItemController;
import coheal.entities.report.Report;
import coheal.services.report.ReportService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private TableColumn<Report, String> typeCol;
    @FXML
    private TableColumn<Report, String> noteCol;
    @FXML
    private TableColumn<Report, Boolean> statusCol;
    @FXML
    private TableColumn<Report, Timestamp> dateAndTimeCol;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private TableView<Report> bookReportstable;
    @FXML
    private TableColumn<?, ?> titleCol1;
    @FXML
    private TableColumn<?, ?> noteCol1;
    @FXML
    private TableColumn<?, ?> statusCol1;
    @FXML
    private TableColumn<?, ?> dateAndTimeCol1;
    @FXML
    private TableView<Report> recipesReportsTable;
    @FXML
    private TableColumn<?, ?> titleCol11;
    @FXML
    private TableColumn<?, ?> noteCol11;
    @FXML
    private TableColumn<?, ?> statusCol11;
    @FXML
    private TableColumn<?, ?> dateAndTimeCol11;
    @FXML
    private TableView<Report> taskReportsTable;
    @FXML
    private TableColumn<?, ?> titleCol12;
    @FXML
    private TableColumn<?, ?> noteCol12;
    @FXML
    private TableColumn<?, ?> statusCol12;
    @FXML
    private TableColumn<?, ?> dateAndTimeCol12;
    @FXML
    private TableView<Report> eventsReportsTable;
    @FXML
    private TableColumn<?, ?> titleCol13;
    @FXML
    private TableColumn<?, ?> noteCol13;
    @FXML
    private TableColumn<?, ?> statusCol13;
    @FXML
    private TableColumn<?, ?> dateAndTimeCol13;
    @FXML
    private TableView<Report> sessionsReportsTable;
    @FXML
    private TableColumn<?, ?> titleCol14;
    @FXML
    private TableColumn<?, ?> noteCol14;
    @FXML
    private TableColumn<?, ?> statusCol14;
    @FXML
    private TableColumn<?, ?> dateAndTimeCol14;
    @FXML
    private VBox urgentReportsVBox;
    ReportService reportService = new ReportService();
    @FXML
    private Label noUrgReportsLabel;
    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        dateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        allReportsTable.setItems((ObservableList<Report>) rs.allReportsListObsv());
        bookTable();
        recipeTable();
        taskTable();
        eventTable();
        sessionTable();
        urgentReports();
        pieChart();
        
    }

    public void pieChart() {
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("Books Reports", rs.reportsList("book_report").size()),
                new PieChart.Data("Recipes Reports", rs.reportsList("recipe_report").size()),
                new PieChart.Data("Tasks Reports", rs.reportsList("task_report").size()),
                new PieChart.Data("Events Reports", rs.reportsList("event_report").size()),
                new PieChart.Data("Sessions Reports", rs.reportsList("session_report").size())
        );
        pieChart.setTitle("Reports");
        pieChart.setData(valueList);
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()) + "%");
                }
            });
        }
    }

    public void urgentReports() {
        List<Report> reportsL = reportService.reportsCount();
        if (reportsL.isEmpty()) {
            noUrgReportsLabel.setVisible(true);
        } else {
            noUrgReportsLabel.setVisible(false);
            for (int i = 0; i < reportsL.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/UrgentReportItem.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    UrgentReportItemController c = loader.getController();
                    c.setData(reportsL.get(i));
                    urgentReportsVBox.getChildren().add(pane);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
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

    public void bookTable() {
        titleCol1.setCellValueFactory(new PropertyValueFactory<>("title"));
        noteCol1.setCellValueFactory(new PropertyValueFactory<>("note"));
        statusCol1.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        dateAndTimeCol1.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        bookReportstable.setItems((ObservableList<Report>) rs.reportsList("book_report"));
    }

    public void recipeTable() {
        titleCol11.setCellValueFactory(new PropertyValueFactory<>("title"));
        noteCol11.setCellValueFactory(new PropertyValueFactory<>("note"));
        statusCol11.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        dateAndTimeCol11.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        recipesReportsTable.setItems((ObservableList<Report>) rs.reportsList("recipe_report"));
    }

    public void taskTable() {
        titleCol12.setCellValueFactory(new PropertyValueFactory<>("title"));
        noteCol12.setCellValueFactory(new PropertyValueFactory<>("note"));
        statusCol12.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        dateAndTimeCol12.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        taskReportsTable.setItems((ObservableList<Report>) rs.reportsList("task_report"));
    }

    public void eventTable() {
        titleCol13.setCellValueFactory(new PropertyValueFactory<>("title"));
        noteCol13.setCellValueFactory(new PropertyValueFactory<>("note"));
        statusCol13.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        dateAndTimeCol13.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        eventsReportsTable.setItems((ObservableList<Report>) rs.reportsList("event_report"));
    }

    public void sessionTable() {
        titleCol14.setCellValueFactory(new PropertyValueFactory<>("title"));
        noteCol14.setCellValueFactory(new PropertyValueFactory<>("note"));
        statusCol14.setCellValueFactory(new PropertyValueFactory<>("isClosed"));
        dateAndTimeCol14.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        sessionsReportsTable.setItems((ObservableList<Report>) rs.reportsList("session_report"));
    }

}
