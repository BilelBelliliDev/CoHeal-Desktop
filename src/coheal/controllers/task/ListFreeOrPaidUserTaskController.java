/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ListFreeOrPaidUserTaskController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void freeTaskAction(ActionEvent event) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/task/ListeUserTask.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void paidTask(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/coheal/views/task/ListPaidUserTask.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
