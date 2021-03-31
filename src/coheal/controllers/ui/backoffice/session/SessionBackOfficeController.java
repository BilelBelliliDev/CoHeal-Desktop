/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.session;

import coheal.entities.session.Session;
import coheal.services.session.ServiceSession;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class SessionBackOfficeController implements Initializable {

    @FXML
    private Label SessionList;
    @FXML
    private TableColumn<Session, Integer> col1Id;
    @FXML
    private TableColumn<Session, Integer> col2Id;
    @FXML
    private TableColumn<Session, String> col3Id;
    @FXML
    private TableColumn<Session, String> col4Id;
    @FXML
    private TableColumn<Session, Integer> col5Id;
    @FXML
    private TableView<Session> tableId;
    private ServiceSession ss = new ServiceSession();
    @FXML
    private TableColumn<Session, Integer> col4Id2;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private JFXTextField session;
    public boolean canModify=false;
        private ServiceSession se = new ServiceSession();
            private int selectedId;
    @FXML
    private PieChart pieChart;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         col1Id.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        col2Id.setCellValueFactory(new PropertyValueFactory<>("therpId"));
        col3Id.setCellValueFactory(new PropertyValueFactory<>("title"));
        col4Id.setCellValueFactory(new PropertyValueFactory<>("description"));
        col5Id.setCellValueFactory(new PropertyValueFactory<>("numOfDays"));
        col4Id2.setCellValueFactory(new PropertyValueFactory<>("price") );
        tableId.setItems((ObservableList<Session>) se.listSesion());
      tableId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableId.getSelectionModel().getSelectedItem() != null) {
                    Session selectedSession = tableId.getSelectionModel().getSelectedItem();
                                       selectedId = selectedSession.getSessionId();

                    canModify = true;
                }
            }
        });
      //----------------PieChart----------
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("Session", se.ListSessionByTherp(UserSession.getUser_id()).size()),
                new PieChart.Data("session participer", se.ListSessionBySessionID(UserSession.getUser_id()).size()));
        pieChart.setTitle("Session");
        pieChart.setData(valueList);
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
    }
               

    @FXML
    private void closeAction(MouseEvent event) {
         Stage stage = new Stage();
        stage = (Stage) tableId.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
         Stage stage = new Stage();
        stage = (Stage) tableId.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void supprimer(MouseEvent event) {
         if (canModify) {
            se.SupprimerSession(selectedId);
            tableId.setItems((ObservableList<Session>) se.listSesion());
        } else {
            System.out.println("can't delete please select an item form the table");
        }
        
    }

    @FXML
    private void search(MouseEvent event) {
        ServiceSession sb = new ServiceSession();
        col2Id.setCellValueFactory(new PropertyValueFactory<Session, Integer>("therpId"));
        col3Id.setCellValueFactory(new PropertyValueFactory<Session, String>("title"));
        col4Id.setCellValueFactory(new PropertyValueFactory<Session, String>("description"));
        col5Id.setCellValueFactory(new PropertyValueFactory<Session, Integer>("numOfDays"));
        col4Id2.setCellValueFactory(new PropertyValueFactory<Session, Integer>("price"));
        int t = Integer.valueOf(session.getText());
        tableId.setItems(sb.ListSessionBySessionID(t));

    }
    
    
}
