/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.backoffice;

import animatefx.animation.ZoomIn;
import coheal.controllers.ui.frontoffice.report.UrgentReportItemController;
import coheal.entities.report.Report;
import coheal.services.book.ServiceBook;
import coheal.services.event.ServiceEvent;
import coheal.services.recipe.RecipeService;
import coheal.services.report.ReportService;
import coheal.services.session.ServiceSession;
import coheal.services.task.ServicePaidTask;
import coheal.services.task.ServiceTask;
import coheal.services.user.ServiceUser;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class DashboardPageController implements Initializable {

    @FXML
    private FontAwesomeIconView close;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private Label nbTasks;
    ServicePaidTask spt = new ServicePaidTask();
    ServiceTask st = new ServiceTask();
    ServiceBook sb = new ServiceBook();
    ServiceEvent se = new ServiceEvent();
    ReportService reportService = new ReportService();
    ServiceUser su=new ServiceUser();
    ServiceSession ss=new ServiceSession();
    @FXML
    private Label totalebooks;
    @FXML
    private Label events;
    @FXML
    private Label totalRecipes;
    RecipeService rs = new RecipeService();
    @FXML
    private VBox urgentReportsVBox;
    
    @FXML
    private Label totalUsers;
    @FXML
    private Label totalSessions;
    @FXML
    private Label noUrgReportsLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ZoomIn(dashboardPane).play();
        urgentReports();
        nbTasks.setText(String.valueOf(spt.getCountPaidTask() + st.getCountTask()));
        totalebooks.setText(String.valueOf(sb.getCountBook()));
        events.setText(String.valueOf(se.AfficherEvent().size()));
        totalRecipes.setText(String.valueOf(rs.CountTotalRecipes()));
        totalUsers.setText(String.valueOf(su.userCount()));
        totalSessions.setText(String.valueOf(ss.listSesion1().size()));
    }
    
    public void urgentReports() {
        List<Report> reportsL = reportService.reportsCount();
        if (reportsL.isEmpty()) {
            noUrgReportsLabel.setVisible(true);
        } else {
            noUrgReportsLabel.setVisible(false);
            for (int i = 0; i < reportsL.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/UrgentReportItem.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    UrgentReportItemController c = loader.getController();
                    c.setData(reportsL.get(i));
                    urgentReportsVBox.getChildren().add(pane);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.setIconified(true);
    }

}
