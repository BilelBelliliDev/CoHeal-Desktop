/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice.session;

import coheal.entities.session.Session;
import coheal.services.session.ServiceSession;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        tableId.setItems((ObservableList<Session>) ss.listSesion());

       
        
        // TODO
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
    
    
}
