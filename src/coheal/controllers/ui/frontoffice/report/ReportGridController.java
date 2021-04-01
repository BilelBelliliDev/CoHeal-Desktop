/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.report;

import coheal.entities.report.Report;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author BilelxOS
 */
public class ReportGridController implements Initializable {

    private int gridSize, columnCount;
    public int pageCount, currentPage;

    @FXML
    private GridPane reportGrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(int index, List<Report> reportsList) {
        gridSize = reportGrid.getRowConstraints().size() * reportGrid.getColumnConstraints().size();
        columnCount = reportGrid.getColumnConstraints().size() - 1;
        currentPage = index;
        int y = 0;
        int x = 0;
        //pagination
        pageCount = reportsList.size() / gridSize;
        if (reportsList.size() % gridSize > 0) {
            pageCount++;
        }
        int a, b;
        a = currentPage * gridSize;
        if (currentPage == (pageCount - 1)) {
            b = reportsList.size();
        } else {
            b = a + gridSize;
        }
        AnchorPane pane = null;
        for (int i = a; i < b; i++) {
            if (!reportsList.isEmpty()) {
                if (!reportsList.get(i).isIsClosed()) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/OpenReportItem.fxml"));
                    try {
                        pane = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportGridController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    OpenReportItemController c = loader.getController();
                    c.setDate(reportsList.get(i));
                    if (x > columnCount) {
                        y++;
                        x = 0;
                    }
                    reportGrid.add(pane, x, y);
                    x++;
                } else {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/report/ClosedReportItem.fxml"));
                    try {
                        pane = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(ReportGridController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ClosedReportItemController c = loader.getController();
                    c.setDate(reportsList.get(i));
                    if (x > columnCount) {
                        y++;
                        x = 0;
                    }
                    reportGrid.add(pane, x, y);
                    x++;
                }
            } else {
                System.out.println("empty");
            }
        } 

    }

}
