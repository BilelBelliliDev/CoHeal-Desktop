/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

import coheal.entities.task.TaskCategory;
import coheal.services.task.ServiceTaskCategory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class GridTaskCategoryController implements Initializable {

    @FXML
    private GridPane CategoryGrid;
    public int pageCount,currentPage;
    private ServiceTaskCategory st=new ServiceTaskCategory();
    private final int NUM=9;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
         public void setData(int index){
                     List<TaskCategory> categories=st.ListTaskCategory();
        currentPage=index;
        int y = 0;
        int x = 0;
        pageCount=categories.size()/NUM;
        if(categories.size()%NUM>0){
            pageCount++;
        }
        int a,b;
        a=currentPage*NUM;
        if(currentPage==(pageCount-1))
            b=categories.size();
        else
            b=a+NUM;

        for (int i = a; i < b; i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/coheal/views/ui/frontoffice/task/TaskCategoryItem.fxml"));
             try {
                AnchorPane pane = loader.load();
                TaskCategoryItemController c = loader.getController();

                c.setData(categories.get(i));
                if (x > 2) {
                    y++;
                    x = 0;
                }
                CategoryGrid.add(pane, x, y);
                x++;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
