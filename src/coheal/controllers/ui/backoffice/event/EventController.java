/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.event;

import coheal.entities.event.Event;
import coheal.services.event.ServiceEvent;
import coheal.services.event.ServiceUserEvent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static org.omg.CORBA.ORB.init;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class EventController implements Initializable {

    @FXML
    private AnchorPane moderationPane;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private TableColumn<?, ?> descriptionCol;
    @FXML
    private TableColumn<?, ?> priceCol;
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<?, ?> lineChart;
     private ServiceUserEvent sut=new ServiceUserEvent();
    @FXML
    private TableView<Event> eventTable;
    @FXML
    private TableColumn<Event, java.sql.Date> startDayCol;
    @FXML
    private TableColumn<Event, java.sql.Date> endDateCol;
    @FXML
    private TableColumn<?, ?> locationCol;
    @FXML
    private TableColumn<?, ?> categoryCol;
    double xOffset, yOffset;
    private ServiceEvent se=new ServiceEvent();
ObservableList<Event> l;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //----------------LineChart----------
         lineChart.setTitle("Number of participations by date");
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("test");
        for(int i=0;i<sut.getNbrParticipateByDate().size();i++)
        dataSeries.getData().add(new XYChart.Data(String.valueOf(sut.getNbrParticipateByDate().get(i).getCreatedAt()),sut.getNbrParticipateByDate().get(i).getNbr()));
        lineChart.getData().add(dataSeries);
              //------------------------------------------//
         l = se.AfficherEvent();
        System.out.println(l);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        startDayCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("cat"));
        eventTable.setItems(l);
    }

    @FXML
    private void closeAction(MouseEvent event) {

        Stage stage = new Stage();
        stage = (Stage) eventTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) eventTable.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void CategoryAction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/backoffice/event/EventCategory.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
//        HomePageHolderController hpc = new HomePageHolderController();
//        hpc.setStage(stage);
        stage.show();
        root.setOnMousePressed((MouseEvent mouseEvent) -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent mouseEvent) -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
            stage.setOpacity(0.85f);
        });
        root.setOnMouseReleased((MouseEvent mouseEvent) -> {
            stage.setOpacity(1.0f);
        });
    }

    @FXML
    private void deleteEventAction(MouseEvent event) {
        Event ev = eventTable.getSelectionModel().getSelectedItem();
        se.deleteEvent(ev.getEventId());
        l = se.AfficherEvent();
        System.out.println(se.AfficherEvent());
        eventTable.setItems(l);;
    }

    @FXML
    private void generatePDF(ActionEvent event) {
        
    }



}
