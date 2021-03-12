/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.session;

import coheal.entities.session.Session;
import coheal.entities.session.SessionChat;
import coheal.services.session.ServiceSession;
import coheal.services.session.ServiceSessionChat;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class ChatSessioFXMLController implements Initializable {

    @FXML
    private TableColumn<SessionChat, Integer> Col1Id;
    @FXML
    private TableColumn<SessionChat, Integer> Col2Id;
    @FXML
    private TableView<SessionChat> tableId;
private int selectedId;
    boolean canModify = false;
    private ServiceSessionChat ss = new ServiceSessionChat();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Col1Id.setCellValueFactory(new PropertyValueFactory<>("ChatId"));
        Col2Id.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
                //tableId.setItems((ObservableList<Session>) ss.listSesion());
                 tableId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableId.getSelectionModel().getSelectedItem() != null) {
                    SessionChat selectedSession = tableId.getSelectionModel().getSelectedItem();
//                    titleId.setText(selectedSession.getChatId());
//                    d.setText(selectedSession.getDescription());
//                    numberofdaysId.setText(String.valueOf(selectedSession.getNumOfDays()));
                    selectedId = selectedSession.getSessionId();
                    canModify = true;
                }
            }
        });

        
    }   

    @FXML
    private void joindreSession(ActionEvent event) {
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
    private void listechat(ActionEvent event) {
        tableId.setItems((ObservableList<SessionChat>) ss.listechat());

    }
    
}
