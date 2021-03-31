/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.services.task.ServicePaidTask;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceTaskCategory;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class AddTaskFController implements Initializable {
    Task task = null;
    ServiceTask st = new ServiceTask();
    Task t = new Task();
    ServiceTaskCategory stc = new ServiceTaskCategory();
    String cat = "";
    File f = null;
    private static String projectPath = System.getProperty("user.dir").replace("/", "\\");
    private ToggleGroup group = new ToggleGroup();
    ServicePaidTask spt = new ServicePaidTask();
    PaidTask pt = new PaidTask();
    @FXML
    private ToggleGroup type1;
    @FXML
    private FontAwesomeIconView close1;
    @FXML
    private JFXTextField price;
    @FXML
    private JFXRadioButton free;
    @FXML
    private JFXTextField Titre;
    @FXML
    private JFXTextArea Description;
    @FXML
    private JFXComboBox<String> comboCatg;
    @FXML
    private JFXTextField numOfDays;
    @FXML
    private JFXRadioButton paid;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TaskCategory tc = new TaskCategory();
        for (int i = 0; i < stc.ListTaskCategory().size(); i++) {
            comboCatg.getItems().add(stc.ListTaskCategory().get(i).getName());
        }
        paid.setToggleGroup(group);
        free.setToggleGroup(group);
        free.setSelected(true);
        price.disableProperty().bind(free.selectedProperty());
    }    

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close1.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void paidAction(ActionEvent event) {
    }

    @FXML
    private void listCategoryCombo(ActionEvent event) {
        cat = comboCatg.getValue();
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        f = fileChooser.showOpenDialog(null);
        Image img = new Image("file:///"+f);
        image.setImage(img);
        //imageTxt.setText(f.getName());
        t.setImgUrl(f.getName());
        pt.setImgUrl(f.getName());
    }

    @FXML
    private void addTaskAction(MouseEvent event) {
        if (paid.isSelected() && price != null) {
            pt.setTitle(Titre.getText());
            pt.setDescription(Description.getText());
            pt.setNumOfDays(Integer.valueOf(numOfDays.getText()));
            pt.setMaxUsers(1);
            pt.setMinUsers(1);
            pt.setPrice(Double.valueOf(price.getText()));
            File dest = null;
            if (f != null) {
                dest = new File(projectPath + "/src/coheal/resources/images/tasks/" + f.getName());
            }
            try {
                if (dest != null) {
                    if (FileUtils.contentEquals(f, dest)) {
                        System.out.println("existe");
                    } else {
                        FileUtils.copyFile(f, dest);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            spt.addPaidTask(UserSession.getUser_id(), cat, pt);
        } else {

            t.setTitle(Titre.getText());
            t.setDescription(Description.getText());
            t.setNumOfDays(Integer.valueOf(numOfDays.getText()));
            t.setMaxUsers(1);
            t.setMinUsers(1);
            File dest = null;
            if (f != null) {
                dest = new File(projectPath + "/src/coheal/resources/images/tasks/" + f.getName());
            }
            try {
                if (dest != null) {
                    if (FileUtils.contentEquals(f, dest)) {
                        System.out.println("existe");
                    } else {
                        FileUtils.copyFile(f, dest);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            st.createTask(UserSession.getUser_id(), cat, t);
        }
    }
    private static void AlertBox(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
}
