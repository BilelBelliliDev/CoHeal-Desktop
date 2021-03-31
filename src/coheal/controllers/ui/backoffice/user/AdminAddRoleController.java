package coheal.controllers.ui.backoffice.user;

import coheal.entities.user.Role;
import coheal.entities.user.User;
import coheal.services.user.ServiceAdmin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author wajdi's pc
 */
public class AdminAddRoleController implements Initializable {

    @FXML
    private TableView<User> TVListUser;
    @FXML
    private TableColumn<User, Integer> UidCol;
    @FXML
    private TableColumn<User, String> UFNCol;
    @FXML
    private TableColumn<User, String> ULNCol;
    @FXML
    private TableColumn<User, String> UECol;
    @FXML
    private JFXCheckBox CHKTherapist;
    @FXML
    private JFXCheckBox CHKModerator;
    @FXML
    private JFXCheckBox CHKNutritionist;
    @FXML
    private JFXCheckBox CHKActiveUser;
    @FXML
    private JFXButton BTNAddRole;

    /**
     * Initializes the controller class.
     */
    ServiceAdmin sa = new ServiceAdmin();
    @FXML
    private PieChart pieChart;
    @FXML
    private FontAwesomeIconView close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherListPersonnes();
        //----------------------------------

        //----------------PieChart----------
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("Therapist", 10),
                new PieChart.Data("Moderator", 1),
                new PieChart.Data("Nutritionist", 2),
                new PieChart.Data("Active user", 3));
        pieChart.setTitle("User par role");
        pieChart.setData(valueList);
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
    }

    //-------------------------------------------------------------------
    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) TVListUser.getScene().getWindow();
        stage.close();
    }

    //-------------------------------------------------------------------
    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) TVListUser.getScene().getWindow();
        stage.setIconified(true);
    }

    //------------------------------------------------------------------ 
    @FXML
    private void GetUserRow_FromList_CheckRoles(MouseEvent event) {
        ServiceAdmin sa = new ServiceAdmin();
        User UserSelected = TVListUser.getSelectionModel().getSelectedItem();

        sa.GetUserRoles(UserSelected);
        System.out.println(sa.GetUserRoles(UserSelected));
        List<Role> LR = sa.GetUserRoles(UserSelected);

        CHKTherapist.setSelected(false);
        CHKNutritionist.setSelected(false);
        CHKModerator.setSelected(false);
        CHKActiveUser.setSelected(false);

        for (int i = 0; i < LR.size(); i++) {
            if (LR.get(i).getRoleName().equals("therapist")) {
                CHKTherapist.setSelected(true);
            } else if (LR.get(i).getRoleName().equals("nutritionist")) {
                CHKNutritionist.setSelected(true);
            } else if (LR.get(i).getRoleName().equals("moderator")) {
                CHKModerator.setSelected(true);
            } else if (LR.get(i).getRoleName().equals("active_user")) {
                CHKActiveUser.setSelected(true);
            }

        }
    }

    //------------------------------------------------------------------ 
    public void AfficherListPersonnes() {
        ObservableList<User> list = sa.GetListPersonnes();

        UidCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        UECol.setCellValueFactory(new PropertyValueFactory<>("email"));
        UFNCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ULNCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TVListUser.setItems(list);
    }

    //------------------------------------------------------------------ 
    @FXML
    private void ModifyRoleAction(ActionEvent event) {
        ServiceAdmin sa = new ServiceAdmin();
        User UserSelected = TVListUser.getSelectionModel().getSelectedItem();

        System.out.println("selected user id's : " + UserSelected.getUserId());
        boolean therapist = CHKTherapist.isSelected();
        boolean nutritionist = CHKNutritionist.isSelected();
        boolean moderator = CHKModerator.isSelected();
        boolean active_user = CHKActiveUser.isSelected();

        if (therapist) {
            if (sa.SearchUser_Role(UserSelected, 1) == false) {
                sa.AddRole(UserSelected, 1);
                //-------------notification--------------------------------------------
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle("Role Therapist Added");
                tray.setMessage("Role added successfully");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
                //Notification.Notifier.INSTANCE.notifySuccess("Role Therapist Added", "Role added successfully");
            }

        } else {
            if (sa.SearchUser_Role(UserSelected, 1) == true) {
                sa.DeleteRole(UserSelected, 1);
                //-------------notification--------------------------------------------
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle("Role Therapist Deleted");
                tray.setMessage("Role Deleted successfully");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
                //Notification.Notifier.INSTANCE.notifySuccess("Role Therapist Deleted", "Role Deleted successfully");
            }
        }

        if (moderator) {
            if (sa.SearchUser_Role(UserSelected, 2) == false) {
                sa.AddRole(UserSelected, 2);
                //-------------notification--------------------------------------------
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle("Role Moderator Added");
                tray.setMessage("Role added successfully");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
                // Notification.Notifier.INSTANCE.notifySuccess("Role Moderator Added", "Role added successfully");
            }

        } else {
            if (sa.SearchUser_Role(UserSelected, 2) == true) {
                sa.DeleteRole(UserSelected, 2);
                //-------------notification--------------------------------------------
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle("Role Moderator Deleted");
                tray.setMessage("Role Deleted successfully");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
                //Notification.Notifier.INSTANCE.notifySuccess("Role Moderator Deleted", "Role Deleted successfully");
            }
        }

        if (nutritionist) {
            if (sa.SearchUser_Role(UserSelected, 3) == false) {
                sa.AddRole(UserSelected, 3);
                //-------------notification--------------------------------------------
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle("Role Nutritionist Added");
                tray.setMessage("Role added successfully");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
                // Notification.Notifier.INSTANCE.notifySuccess("Role Nutritionist Added", "Role added successfully");
            }

        } else {
            if (sa.SearchUser_Role(UserSelected, 3) == true) {
                sa.DeleteRole(UserSelected, 3);
                //-------------notification--------------------------------------------
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle("Role Nutritionist Deleted");
                tray.setMessage("Role Deleted successfully");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
                // Notification.Notifier.INSTANCE.notifySuccess("Role Nutritionist Deleted", "Role Deleted successfully");
            }
        }

        if (active_user) {
            if (sa.SearchUser_Role(UserSelected, 5) == false) {
                sa.AddRole(UserSelected, 5);
                //-------------notification--------------------------------------------
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle("Role Active User Added");
                tray.setMessage("Role added successfully");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
                //Notification.Notifier.INSTANCE.notifySuccess("Role Active User Added", "Role added successfully");
            }

        } else {
            if (sa.SearchUser_Role(UserSelected, 5) == true) {
                sa.DeleteRole(UserSelected, 5);
                //-------------notification--------------------------------------------
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle("Role Active User Deleted");
                tray.setMessage("Role Deleted successfully");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                //---------------------------------------------------------------------
                //Notification.Notifier.INSTANCE.notifySuccess("Role Active User Deleted", "Role Deleted successfully");
            }
        }

    }

}
