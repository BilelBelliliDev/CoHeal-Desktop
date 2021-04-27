/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.HomePageHolderController;
import coheal.entities.task.Notification;
import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.entities.task.TaskActions;
import coheal.entities.task.UserTask;
import coheal.entities.user.User;
import coheal.services.report.RateService;
import coheal.services.task.ServiceNotification;
import coheal.services.task.ServicePaidTask;
import coheal.services.task.ServiceTask;
import coheal.services.task.ServiceTaskActions;
import coheal.services.task.ServiceUserTask;
import coheal.services.user.ServiceUser;
import coheal.services.user.UserSession;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TaskDetailsController implements Initializable {

    @FXML
    private ImageView taskImg;
    @FXML
    private Label taskTitle;
    @FXML
    private Label taskDescription;
    @FXML
    private Label taskDate;
    @FXML
    private Label TaskNumDays;
    @FXML
    private GridPane ActionGrid;
    @FXML
    private ScrollPane taskActionsPane;
    private ServiceTask st = new ServiceTask();
    private ServicePaidTask spt = new ServicePaidTask();
    private ServiceTaskActions sta = new ServiceTaskActions();
    double xOffset, yOffset;
    Task task;
    PaidTask pt;
    TaskHolder th = TaskHolder.getINSTANCE();
    @FXML
    private JFXButton addActionBtn;
    @FXML
    private FontAwesomeIconView updateIcon;
    @FXML
    private FontAwesomeIconView deleteIcon;
    ServiceUserTask sut = new ServiceUserTask();
    @FXML
    private JFXButton participateButton;
    @FXML
    private Label price;
    private ServiceUser su=new ServiceUser();
    @FXML
    private Rating ratingId;
    @FXML
    private Label rateLabel;
     RateService rateService = new RateService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(rateService.ratesListById("task", th.getId())==0){
            rateLabel.setVisible(true);
            ratingId.setVisible(false);
        }else {        
            System.out.println(rateService.ratesListById("task", th.getId()));
            ratingId.setRating(rateService.ratesListById("task", th.getId()));
            rateLabel.setVisible(false);
            ratingId.setVisible(true);
        }
        
        taskActionsPane.setVvalue(0);
        new ZoomIn(taskActionsPane).play();
        task = st.getTask(th.getId());
        pt = spt.getPaidTask(th.getId());
        UserTask u = sut.getUserTask(UserSession.getUser_id(), task.getTaskId());
        if (u.getTask() == null && u.getUser() == null) {
            participateButton.setVisible(true);
        }
        if (UserSession.getRole().equals("ROLE_Therapist") && task.getUser().getUserId() == UserSession.getUser_id()) {
            addActionBtn.setVisible(true);
            updateIcon.setVisible(true);
            deleteIcon.setVisible(true);
            participateButton.setVisible(false);
        }
        if (UserSession.getRole().equals("ROLE_Moderator")) {
            deleteIcon.setVisible(true);
        }

        int y = 0;
        int x = 0;
        List<TaskActions> taskActions;
        if (pt != null) {
            taskTitle.setText(pt.getTitle());
            taskImg.setImage(pt.getImg().getImage());
            taskDescription.setText(pt.getDescription());
            TaskNumDays.setText(String.valueOf(pt.getNumOfDays()));
//            taskMinUsers.setText(String.valueOf(pt.getMinUsers()));
//            TaskMaxUsers.setText(String.valueOf(pt.getMaxUsers()));
            String date = pt.getCreatedAt().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd")) + " "
                    + pt.getCreatedAt().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("MMM")) + " 20"
                    + pt.getCreatedAt().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("YY"));
            taskDate.setText(date);
            price.setText(String.valueOf(pt.getPrice()) + "DT");
            taskActions = sta.ListTaskActionsByTaskId(pt.getTaskId());
        } else {
            taskTitle.setText(task.getTitle());
            taskImg.setImage(task.getImg().getImage());
            taskDescription.setText(task.getDescription());
            TaskNumDays.setText(String.valueOf(task.getNumOfDays()));
//            taskMinUsers.setText(String.valueOf(task.getMinUsers()));
//            TaskMaxUsers.setText(String.valueOf(task.getMaxUsers()));
            String date = task.getCreatedAt().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd")) + " "
                    + task.getCreatedAt().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("MMM")) + " 20"
                    + task.getCreatedAt().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("YY"));
            taskDate.setText(date);
            taskActions = sta.ListTaskActionsByTaskId(task.getTaskId());
        }

        for (int i = 0; i < taskActions.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskActionItem.fxml"));
            try {
                Pane pane = loader.load();
                TaskActionItemController c = loader.getController();
                c.setData(taskActions.get(i));
                if (x > 1) {
                    y++;
                    x = 0;
                }
                if (u.getUser() != null) {
                    System.out.println(u.getUser().getUserId());
                    if (UserSession.getUser_id() == u.getUser().getUserId()) {
                        ActionGrid.add(pane, x, y);
                    }
                } else if (UserSession.getRole().equals("ROLE_Therapist") && task.getUser().getUserId() == UserSession.getUser_id()) {
                    ActionGrid.add(pane, x, y);
                }

                x++;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void participateAction(ActionEvent event) {
         Window owner = taskTitle.getScene().getWindow();
        User user = st.getUserById(UserSession.getUser_id());
        if (pt != null) {
            Double balance=user.getBalance()-pt.getPrice();
            if (balance>=0) {
                sut.participer(UserSession.getUser_id(), th.getId());
                su.UpdateBalance(balance, user);
                ServiceNotification service = new ServiceNotification();
                Notification n = new Notification();
                n.setId(task.getUser().getUserId());
                n.setMessage(UserSession.getFirst_name() + " " + UserSession.getLast_name() + " a participer a votre tache " + task.getTitle());
                service.addNotification(n);
                AlertBox(Alert.AlertType.CONFIRMATION, owner, "Confirmation",
                    "Participate!");
            } else {
                AlertBox(Alert.AlertType.ERROR, owner, "Erreur",
                    "Task not added !");
                System.out.println("insufficient balance");
            }
        }else{
              sut.participer(UserSession.getUser_id(), th.getId());
                ServiceNotification service = new ServiceNotification();
                Notification n = new Notification();
                n.setId(task.getUser().getUserId());
                n.setMessage(UserSession.getFirst_name() + " " + UserSession.getLast_name() + " a participer a votre tache " + task.getTitle());
                service.addNotification(n);
                AlertBox(Alert.AlertType.CONFIRMATION, owner, "Confirmation",
                    "Participate!");
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
     
    @FXML
    private void addTaskAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/task/AddTaskActions.fxml"));
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
        });
    }

    @FXML
    private void updateTask(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coheal/views/ui/frontoffice/task/UpdateTaskF.fxml"));
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
        });
    }

    @FXML
    private void deleteTask(MouseEvent event) {
        st.deleteTask(th.getId());
    }

    @FXML
    private void backAction(MouseEvent event) throws IOException {
        AnchorPane pageHolder = (AnchorPane) taskActionsPane.getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskPage.fxml")));

    }

}
