/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.session;

import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.controllers.ui.frontoffice.task.TaskHolder;
import coheal.entities.session.Session;
import coheal.services.session.ServiceSession;
import coheal.services.session.ServiceSessionChat;
import coheal.services.ui.UIService;
import coheal.services.user.UserSession;
import static com.google.zxing.client.result.ParsedResultType.SMS;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class SessionItemController implements Initializable {

    @FXML
    private Label therpName;
    @FXML
    private Label sessionTitle;
    @FXML
    private Label sessionDesc;
    double xOffset, yOffset;
    public int id;
    ServiceSession ss = new ServiceSession();
    private int sessionId, therpId;
    int id1 = 0;
    private FontAwesomeIconView icon;
    @FXML
    private Label price;
    @FXML
    private Button update;
    @FXML
    private FontAwesomeIconView icon1;
    ServiceSessionChat scm = new ServiceSessionChat();
    @FXML
    private Button participer;

    Session session;
    SessionHolder tah = SessionHolder.getINSTANCE();
    @FXML
    private FontAwesomeIconView suprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //session = ss.searchSession(tah.getId());
//        Session s = new Session();
//s.setUserId(UserSession.getUser_id());

        if (UserSession.getRole().equals("therapist") && UserSession.getUser_id() != therpId) {
            update.setVisible(true);
            icon1.setVisible(true);
            participer.setVisible(true);
            suprimer.setVisible(true);

        }

    }

    public void setData(Session s,boolean icon) {


    icon1.setVisible(icon);

        UIService us = new UIService();
        sessionTitle.setText(s.getTitle());
        sessionDesc.setText(s.getDescription());
        price.setText(s.getPrice() + "dt");

        sessionId = s.getSessionId();
        therpId = s.getTherpId();

        try {
            therpName.setText(us.therpSession(s.getTherpId()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//         if (UserSession.getRole().equals("therapist") ) {
//            update.setVisible(true);
//            icon1.setVisible(false);

        // }
    }

    public FontAwesomeIconView getIcon() {
        return icon;
    }

    public void setIcon(FontAwesomeIconView icon) {
        this.icon = icon;
    }

//    private void msgEvent(MouseEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/session/SessionMessage.fxml"));
//        Parent root = loader.load();
//        Stage stage = new Stage();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.TRANSPARENT);
//        scene.setFill(Color.TRANSPARENT);
//        HomePageHolderController hpc = new HomePageHolderController();
//        hpc.setStage(stage);
//        stage.show();
//        root.setOnMousePressed((MouseEvent mouseEvent) -> {
//            xOffset = mouseEvent.getSceneX();
//            yOffset = mouseEvent.getSceneY();
//        });
//        root.setOnMouseDragged((MouseEvent mouseEvent) -> {
//            stage.setX(mouseEvent.getScreenX() - xOffset);
//            stage.setY(mouseEvent.getScreenY() - yOffset);
//            stage.setOpacity(0.85f);
//        });
//        root.setOnMouseReleased((MouseEvent mouseEvent) -> {
//            stage.setOpacity(1.0f);
//        });
//        ss.modifierV(id);
//        
//    }
    @FXML
    private void UpdateEvent(MouseEvent event) throws IOException {
       Session s = new Session();

        s.setUserId(UserSession.getUser_id());
        if (UserSession.getUser_id() != therpId) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Suppression");
            alert.setHeaderText(null);
            alert.setContentText("vous ne pouvez pas modifier cette session");

            alert.showAndWait();
            System.out.println("error");
        }
        else{
        SessionHolder th = SessionHolder.getINSTANCE();
        th.setId(sessionId);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/session/UpdateSessoio.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        HomePageHolderController hpc = new HomePageHolderController();
        hpc.setStage(stage);
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
        });}

    }

    @FXML
    private void participate(MouseEvent event) {

        Session s = new Session();

        s.setUserId(UserSession.getUser_id());
        if (UserSession.getUser_id() != therpId) {
            ss.participate(s, sessionId);
            scm.createSessionChat(sessionId);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Suppression");
            alert.setHeaderText(null);
            alert.setContentText("vous ne pouvez pas participer cette session");

            alert.showAndWait();
            System.out.println("error");
        }
//        SMS sms=new SMS();
//        sms.SendSMS("haitham124", "othmani@123", "ss", "21653731850", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
    }

    @FXML
    private void msgEvent(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/session/SessionMessage.fxml"));
        Parent root = loader.load();
        SessionMessageController msg=loader.getController();
        msg.setdata(sessionId);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        HomePageHolderController hpc = new HomePageHolderController();
        hpc.setStage(stage);
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
//     @FXML
//    private void SupprierSession(MouseEvent event) {
//       s.SupprimerSession(id);
//    }

    @FXML
    private void SupprierSession(MouseEvent event) {
        Session s = new Session();

        s.setUserId(UserSession.getUser_id());
        if (UserSession.getUser_id() == therpId) {
            ss.SupprimerSession(sessionId);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Suppression");
            alert.setHeaderText(null);
            alert.setContentText("vous ne pouvez pas supprimer cette session");

            alert.showAndWait();
            System.out.println("error");
        }

    }

}
