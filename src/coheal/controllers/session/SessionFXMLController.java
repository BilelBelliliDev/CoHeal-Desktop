/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.session;

import coheal.controllers.report.RateAlertUIController;
import coheal.controllers.report.RatePopupUIController;
import coheal.controllers.report.ReportPopupUIController;
import coheal.entities.event.Event;
import coheal.entities.event.EventCategory;
import coheal.entities.session.Session;
import coheal.services.event.ServiceEvent;
import coheal.services.report.RateService;
import coheal.services.session.ServiceSession;
import coheal.services.session.ServiceSessionChat;
import coheal.services.user.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class SessionFXMLController implements Initializable {

    //Report/Rate Module (Bilel Bellili)
    private ServiceUser su = new ServiceUser();
    private RateService sr = new RateService();

    @FXML
    private TextField titleId;
    @FXML
    private Text descriptionId;
    @FXML
    private TextArea d;
    @FXML
    private Label SessionList;
    @FXML
    private TextField numberofdaysId;
    private ServiceSession se = new ServiceSession();
    private int selectedId;
    boolean canModify = false;

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
    private ComboBox<Integer> userIdBox;

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
        tableId.setItems((ObservableList<Session>) ss.listSesion());

        tableId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableId.getSelectionModel().getSelectedItem() != null) {
                    Session selectedSession = tableId.getSelectionModel().getSelectedItem();
                    titleId.setText(selectedSession.getTitle());
                    d.setText(selectedSession.getDescription());
                    numberofdaysId.setText(String.valueOf(selectedSession.getNumOfDays()));
                    selectedId = selectedSession.getSessionId();
                    canModify = true;
                }
            }
        });
        
        //Report/Rate Module (Bilel Bellili)  
        int num = su.AfficherPersonne().size();
        for (int i = 0; i < num; i++) {
            userIdBox.getItems().add(su.AfficherPersonne().get(i).getUserId());
        }
    }

    @FXML
    private void ajouterSession(ActionEvent event) {

        ServiceSessionChat sc = new ServiceSessionChat();

        Session s = new Session();

        s.setTitle(titleId.getText());
        s.setDescription(d.getText());
        int n = Integer.parseInt(numberofdaysId.getText());
        s.setNumOfDays(n);
        s.setTherpId(1);
        ss.createSession(s);
        tableId.setItems((ObservableList<Session>) ss.listSesion());

    }

    @FXML
    private void AfficherSession(ActionEvent event) {
        tableId.setItems((ObservableList<Session>) ss.listSesion());

    }

    @FXML
    private void MessageShow(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/session/SessionMessageFXML.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void ModifierAction(ActionEvent event) {
        if (canModify) {
            Session e = new Session();
            e.setTitle(titleId.getText());
            e.setDescription(d.getText());
            e.setNumOfDays(Integer.valueOf(numberofdaysId.getText()));
            se.modifierSession(e, selectedId);
            tableId.setItems((ObservableList<Session>) ss.listSesion());
        } else {
            System.out.println("can't modify please select an item form the table");
        }
    }

    @FXML
    private void supprimerSessionAction(ActionEvent event) {

        if (canModify) {
            se.SupprimerSession(selectedId);
            tableId.setItems((ObservableList<Session>) se.listSesion());
        } else {
            System.out.println("can't delete please select an item form the table");
        }
    }

    @FXML
    private void ratePopupAction(ActionEvent event) throws IOException {
        if (sr.isRated(selectedId, userIdBox.getValue(), "Session")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/RateAlertUI.fxml"));
            Parent root = loader.load();
            RateAlertUIController c = loader.getController();
            c.setData(selectedId, userIdBox.getValue(), "Session");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/RatePopupUI.fxml"));
            Parent root = loader.load();
            RatePopupUIController c = loader.getController();
            c.setData(selectedId, userIdBox.getValue(), "Session");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void reportPopupAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/report/ReportPopupUI.fxml"));
        Parent root = loader.load();
        ReportPopupUIController c = loader.getController();
        c.setData(selectedId, userIdBox.getValue(), "Session");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
