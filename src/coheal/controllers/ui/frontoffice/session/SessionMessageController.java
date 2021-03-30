/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.session;

import coheal.entities.session.SessionMessage;
import coheal.services.session.ServiceSessionMessage;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class SessionMessageController implements Initializable {

    @FXML
    private Pane textId;
    @FXML
    private TextField msgId;
    private Timer timer = new Timer();
    ServiceSessionMessage smg = new ServiceSessionMessage();
    @FXML
    private JFXListView<String> chatArea;
    ObservableList<String> s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s = FXCollections.observableArrayList();
        for (int i = 0; i < smg.listMessage().size(); i++) {
            s.add(smg.listMessage().get(i).getMsg());
        }
        chatArea.setItems(s);
    }

    @FXML
    private void envoyerMessageAction(ActionEvent event) {
        ServiceSessionMessage sem = new ServiceSessionMessage();

        SessionMessage sm = new SessionMessage();

        sm.setMsg(msgId.getText());
        sm.setUserId(UserSession.getUser_id());
        sem.createSessionMesage(sm);
        s = FXCollections.observableArrayList();
        for (int i = 0; i < smg.listMessage().size(); i++) {
            s.add(smg.listMessage().get(i).getMsg());

        }
        chatArea.setItems(s);
        msgId.setText("");
    }

}
