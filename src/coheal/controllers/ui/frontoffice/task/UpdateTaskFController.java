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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class UpdateTaskFController implements Initializable {

    @FXML
    private JFXTextField price;
    @FXML
    private JFXRadioButton paid;
    @FXML
    private ToggleGroup type1;
    @FXML
    private JFXRadioButton free;
    @FXML
    private FontAwesomeIconView close1;
    @FXML
    private JFXTextField Titre;
    @FXML
    private JFXTextArea Description;
    @FXML
    private JFXComboBox<String> comboCatg;
    @FXML
    private JFXTextField numOfDays;
    @FXML
    private ImageView image;
    private ToggleGroup group = new ToggleGroup();
    ServicePaidTask spt = new ServicePaidTask();
    PaidTask pt;
    File f = null;
    ServiceTaskCategory stc = new ServiceTaskCategory();
    String cat = "";
    TaskCategory tc;
    private static String projectPath = System.getProperty("user.dir").replace("/", "\\");
    Task task;
    TaskHolder th = TaskHolder.getINSTANCE();
    private ServiceTask st = new ServiceTask();

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
        if (spt.getPaidTask(th.getId()) != null) {
            pt = spt.getPaidTask(th.getId());
            Titre.setText(pt.getTitle());
            Description.setText(pt.getDescription());
            numOfDays.setText(String.valueOf(pt.getNumOfDays()));
            image.setImage(pt.getImg().getImage());
            paid.setSelected(true);
            price.setDisable(false);
            price.setText(String.valueOf(pt.getPrice()));
            comboCatg.getSelectionModel().select(pt.getCategory().getName());
            if (free.isSelected()) {
                price.setDisable(true);
            }
        } else {
            task = st.getTask(th.getId());
            Titre.setText(task.getTitle());
            image.setImage(task.getImg().getImage());
            Description.setText(task.getDescription());
            numOfDays.setText(String.valueOf(task.getNumOfDays()));
            free.setSelected(true);
            price.disableProperty().bind(free.selectedProperty());
            comboCatg.getSelectionModel().select(task.getCategory().getName());
        }

    }

    @FXML
    private void paidAction(ActionEvent event) {
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close1.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void listCategoryCombo(ActionEvent event) {
        cat = comboCatg.getValue();
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        f = fileChooser.showOpenDialog(null);
        Image img = new Image("file:///" + f);
        image.setImage(img);
        if (task != null) {
            task.setImgUrl(f.getName());
        } else if (pt != null) {
            pt.setImgUrl(f.getName());
        }
    }

    @FXML
    private void updateTaskAction(MouseEvent event) {
        if (pt != null) {
            if (paid.isSelected() && price.getText() != "") {
                pt.setTitle(Titre.getText());
                pt.setDescription(Description.getText());
                pt.setNumOfDays(Integer.valueOf(numOfDays.getText()));
//                pt.setMaxUsers(Integer.valueOf(maxUsers.getText()));
//                pt.setMinUsers(Integer.valueOf(minUsers.getText()));
                pt.setPrice(Double.valueOf(price.getText()));
                File dest = null;
                if (f != null) {
                    dest = new File(projectPath + "/src/coheal/resources/images/tasks/" + pt.getImgUrl());
                } else {
                    System.out.println("vide");
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
                    System.out.println(e.getMessage());
                }
                if (cat != "") {
                    tc = stc.searchTaskCategory(cat);
                    pt.setCategory(tc);
                }
                st.updateTask(pt, th.getId());
                spt.updatePaidTask(pt, th.getId());

            } else if (free.isSelected()) {
                pt.setTitle(Titre.getText());
                pt.setDescription(Description.getText());
                pt.setNumOfDays(Integer.valueOf(numOfDays.getText()));
//                pt.setMaxUsers(Integer.valueOf(maxUsers.getText()));
//                pt.setMinUsers(Integer.valueOf(minUsers.getText()));
                File dest = null;
                if (f != null) {
                    dest = new File(projectPath + "/src/coheal/resources/images/tasks/" + pt.getImgUrl());
                } else {
                    System.out.println("vide");
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
                    System.out.println(e.getMessage());
                }
                if (cat != "") {
                    tc = stc.searchTaskCategory(cat);
                    pt.setCategory(tc);
                }
                spt.makeFreeTask(th.getId());
                st.updateTask((Task) pt, th.getId());
            }

        } else if (task != null) {
            if (free.isSelected()) {
                task.setTitle(Titre.getText());
                task.setDescription(Description.getText());
                task.setNumOfDays(Integer.valueOf(numOfDays.getText()));
//                task.setMaxUsers(Integer.valueOf(maxUsers.getText()));
//                task.setMinUsers(Integer.valueOf(minUsers.getText()));
                File dest = null;
                if (f != null) {
                    dest = new File(projectPath + "/src/coheal/resources/images/tasks/" + task.getImgUrl());
                } else {
                    System.out.println("vide");
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
                    System.out.println(e.getMessage());
                }
                if (cat != "") {
                    tc = stc.searchTaskCategory(cat);
                    task.setCategory(tc);
                }
                st.updateTask(task, th.getId());
            } else if (paid.isSelected() && price.getText() != "") {
                task.setTitle(Titre.getText());
                task.setDescription(Description.getText());
                task.setNumOfDays(Integer.valueOf(numOfDays.getText()));
//                task.setMaxUsers(Integer.valueOf(maxUsers.getText()));
//                task.setMinUsers(Integer.valueOf(minUsers.getText()));
                File dest = null;
                if (f != null) {
                    dest = new File(projectPath + "/src/coheal/resources/images/tasks/" + task.getImgUrl());
                } else {
                    System.out.println("vide");
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
                    System.out.println(e.getMessage());
                }

                if (cat != "") {
                    tc = stc.searchTaskCategory(cat);
                    task.setCategory(tc);
                }
                st.updateTask(task, th.getId());
                spt.makePaidTask(th.getId(), Double.valueOf(price.getText()));
            }

        }
    }

}
