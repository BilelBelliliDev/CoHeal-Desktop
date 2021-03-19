/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.report;

import coheal.entities.rate.Rate;
import coheal.services.report.RateService;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class RateDashboardUIController implements Initializable {
    
    RateService rs = new RateService();

    @FXML
    private TableColumn<Rate, Integer> col1Id;
    @FXML
    private TableColumn<Rate, Integer> col2Id;
    @FXML
    private TableColumn<Rate, String> col3Id;
    @FXML
    private TableColumn<Rate, Double> col4Id;
    @FXML
    private TableColumn<Rate, Timestamp> col5Id;
    @FXML
    private TableColumn<Rate, Integer> col1Id1;
    @FXML
    private TableColumn<Rate, Double> col2Id1;
    @FXML
    private TableColumn<Rate, Double> col3Id1;
    @FXML
    private TableColumn<Rate, Timestamp> col4Id1;
    @FXML
    private Label labelId;
    @FXML
    private TableView<Rate> allRatesTable;
    @FXML
    private TableView<Rate> ratesTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelId.setText("All Rates");
        allRatesTable.setVisible(true);
        ratesTable.setVisible(false);
        col1Id.setCellValueFactory(new PropertyValueFactory<>("rateId"));
        col2Id.setCellValueFactory(new PropertyValueFactory<>("userId"));
        col3Id.setCellValueFactory(new PropertyValueFactory<>("type"));
        col4Id.setCellValueFactory(new PropertyValueFactory<>("score"));
        col5Id.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        allRatesTable.setItems((ObservableList<Rate>) rs.allRatesList());
    }    

    @FXML
    private void allRatesAction(ActionEvent event) {
        labelId.setText("All Rates");
        allRatesTable.setVisible(true);
        ratesTable.setVisible(false);
        col1Id.setCellValueFactory(new PropertyValueFactory<>("rateId"));
        col2Id.setCellValueFactory(new PropertyValueFactory<>("userId"));
        col3Id.setCellValueFactory(new PropertyValueFactory<>("type"));
        col4Id.setCellValueFactory(new PropertyValueFactory<>("score"));
        col5Id.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        allRatesTable.setItems((ObservableList<Rate>) rs.allRatesList());
    }

    @FXML
    private void bookRatesAction(ActionEvent event) {
        labelId.setText("Book Rates");
        allRatesTable.setVisible(false);
        ratesTable.setVisible(true);
        col1Id1.setText("Book Id");
        col1Id1.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        col2Id1.setCellValueFactory(new PropertyValueFactory<>("score"));
        ratesTable.setItems((ObservableList<Rate>) rs.ratesList("book"));
    }

    @FXML
    private void recipeRatesAction(ActionEvent event) {
        labelId.setText("Recipe Rates");
        allRatesTable.setVisible(false);
        ratesTable.setVisible(true);
        col1Id1.setText("Recipe Id");
        col1Id1.setCellValueFactory(new PropertyValueFactory<>("recipeId"));
        col2Id1.setCellValueFactory(new PropertyValueFactory<>("score"));
        ratesTable.setItems((ObservableList<Rate>) rs.ratesList("recipe"));
    }

    @FXML
    private void taskRatesAction(ActionEvent event) {
        labelId.setText("Task Rates");
        allRatesTable.setVisible(false);
        ratesTable.setVisible(true);
        col1Id1.setText("Task Id");
        col1Id1.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        col2Id1.setCellValueFactory(new PropertyValueFactory<>("score"));
        ratesTable.setItems((ObservableList<Rate>) rs.ratesList("task"));
    }

    @FXML
    private void eventRatesAction(ActionEvent event) {
        labelId.setText("Event Rates");
        allRatesTable.setVisible(false);
        ratesTable.setVisible(true);
        col1Id1.setText("Event Id");
        col1Id1.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        col2Id1.setCellValueFactory(new PropertyValueFactory<>("score"));
        ratesTable.setItems((ObservableList<Rate>) rs.ratesList("event"));
    }

    @FXML
    private void sessionRatesAction(ActionEvent event) {
        labelId.setText("Session Rates");
        allRatesTable.setVisible(false);
        ratesTable.setVisible(true);
        col1Id1.setText("Session Id");
        col1Id1.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        col2Id1.setCellValueFactory(new PropertyValueFactory<>("score"));
        ratesTable.setItems((ObservableList<Rate>) rs.ratesList("session"));
    }
    
}
