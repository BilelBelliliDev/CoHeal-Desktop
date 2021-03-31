/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.services.task.ServicePaidTask;
import coheal.services.task.ServiceTask;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class GridTaskController implements Initializable {

    @FXML
    private GridPane taskGrid;
    public int pageCount,currentPage;
    ServicePaidTask spt = new ServicePaidTask();
    private ServiceTask st = new ServiceTask();
    private final int NUM=9;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     public void setData(int index,List<?> tasks){
        currentPage=index;
        int y = 0;
        int x = 0;
        pageCount=tasks.size()/NUM;
        if(tasks.size()%NUM>0){
            pageCount++;
        }
        int a,b;
        a=currentPage*NUM;
        if(currentPage==(pageCount-1))
            b=tasks.size();
        else
            b=a+NUM;
        for (int i = a; i < b; i++) {
            if (tasks.get(i) instanceof PaidTask) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskItem.fxml"));
                try {
                    Pane pane = loader.load();
                    TaskItemController c = loader.getController();
                    c.setPaidTaskData((PaidTask) tasks.get(i));
                    if (x > 2) {
                        y++;
                        x = 0;
                    }
                    taskGrid.add(pane, x, y);
                    x++;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskItem.fxml"));
                try {
                    Pane pane = loader.load();
                    TaskItemController c = loader.getController();
                    c.setData((Task) tasks.get(i));
                    if (x > 2) {
                        y++;
                        x = 0;
                    }
                    taskGrid.add(pane, x, y);
                    x++;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    
}
