/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.task;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.entities.task.UserTask;
import coheal.services.task.ServicePaidTask;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceUserTask;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TaskController implements Initializable {

    @FXML
    private AnchorPane moderationPane;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private TableColumn<?, String> titleCol;
    @FXML
    private TableColumn<?, String> descriptionCol;
    @FXML
    private TableColumn<?, String> DaysCol;
    @FXML
    private TableColumn<TaskCategory, String> catgCol;
    @FXML
    private TableColumn<?, String> priceCol;
    @FXML
    private TableView<Task> taskTable;
    ServiceTask st = new ServiceTask();
    Task t = new Task();
    ServicePaidTask spt = new ServicePaidTask();
    PaidTask pt = new PaidTask();
    double xOffset, yOffset;
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<?, ?> lineChart;
    private ServiceUserTask sut=new ServiceUserTask();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        init();
        //----------------PieChart----------
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("Free Tasks", st.ListTask().size()),
                new PieChart.Data("Paid Tasks", spt.listPaidTask().size()));
        pieChart.setTitle("Tasks");
        pieChart.setData(valueList);
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
        //----------------LineChart----------
        lineChart.setTitle("Number of participations by date");
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("test");
        for(int i=0;i<sut.getNbrParticipateByDate().size();i++)
        dataSeries.getData().add(new XYChart.Data(String.valueOf(sut.getNbrParticipateByDate().get(i).getCreatedAt()),sut.getNbrParticipateByDate().get(i).getNbr()));
        lineChart.getData().add(dataSeries);



    }
    public void init(){
       List<?> tasks;
        tasks = Stream.concat(st.ListTask().stream(), spt.listPaidTask().stream())
                .collect(Collectors.toList());
        ObservableList<Task> l = FXCollections.observableList((List<Task>) tasks);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        DaysCol.setCellValueFactory(new PropertyValueFactory<>("numOfDays"));
        catgCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        taskTable.setItems(l); 
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) taskTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) taskTable.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void addCategoryAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/backoffice/task/TaskCategory.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
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
    private void deleteTaskAction(MouseEvent event) {
        Task task = taskTable.getSelectionModel().getSelectedItem();
        st.deleteTask(task.getTaskId());
        taskTable.getItems().clear();
        init();
    }

}
