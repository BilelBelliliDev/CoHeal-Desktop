package coheal.controllers.user;

import coheal.entities.user.Role;
import coheal.entities.user.User;
import coheal.services.user.ServiceAdmin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author wajdi's pc
 */
public class AdminRoleBadgeController implements Initializable {

    @FXML
    private TableView<Role> TVListRole;
    @FXML
    private TableView<User> TVListUser;
    @FXML
    private TextField TFRoleId;
    @FXML
    private TextField TFUserId;
    @FXML
    private TextField TFRoleName;
    @FXML
    private TextField TFUserName;
    @FXML
    private TableColumn<Role, Integer> RidCol;
    @FXML
    private TableColumn<Role, String> RnameCol;
    @FXML
    private TableColumn<User, Integer> UidCol;
    @FXML
    private TableColumn<User, String> UFNCol;
    @FXML
    private TableColumn<User, String> ULNCol;
    @FXML
    private TableColumn<User, String> UECol;

    /**
     * Initializes the controller class.
     */
    ServiceAdmin sa = new ServiceAdmin();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherListPersonnes();
        AfficherListRoles();
    }    

    @FXML
    private void GetRoleRow(MouseEvent event) {
        
        
    }

    @FXML
    private void GetUserList() {
        
    }
    @FXML
    private void AddRoleBadge(ActionEvent event) {
        
    }
    
     //------------------------------------------------------------------ 
     public void AfficherListPersonnes(){
         ObservableList <User> list=sa.GetListPersonnes();
         
         UidCol.setCellValueFactory(new PropertyValueFactory<User,Integer>("userId"));
         UECol.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
         UFNCol.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
         ULNCol.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
         
         TVListUser.setItems(list);
     }
     
     //------------------------------------------------------------------ 
     public void AfficherListRoles(){
         ObservableList <Role> list =sa.GetListRoles();
         
         RidCol.setCellValueFactory(new PropertyValueFactory<Role,Integer>("roleId"));
         RnameCol.setCellValueFactory(new PropertyValueFactory<Role,String>("roleName"));
         
         TVListRole.setItems(list);
     }
     
    
}
