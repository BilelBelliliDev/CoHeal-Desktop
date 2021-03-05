package coheal.services.user;

import coheal.entities.user.Role;
import coheal.entities.user.User;
import coheal.iservices.user.IServiceAdmin;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author wajdi's pc
 */
public class ServiceAdmin implements IServiceAdmin{
    Connection cnx;
    
    
    public ServiceAdmin(){
        cnx=MyConnection.getInstance().getConnection();
    }
    
    
  //------------------------------------------------------------------
     public ObservableList<User> GetListPersonnes() {          
        ObservableList<User> Users = FXCollections.observableArrayList();
        try {
            String query = "SELECT user_id,email,first_name,last_name FROM `user` ";
            Statement stm=cnx.createStatement();             
            ResultSet rst=stm.executeQuery(query);
            User user;
             while (rst.next()){                 
                 user = new User(rst.getInt("user_id"),rst.getString("email"),rst.getString("first_name"),rst.getString("last_name"));
                 Users.add(user);
             }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Users;       
    }

//------------------------------------------------------------------

    public ObservableList<Role> GetListRoles() {
      ObservableList<Role> Roles = FXCollections.observableArrayList();
        try {
            String query = "SELECT role_id,role_name FROM `role` ";
            Statement stm=cnx.createStatement();             
            ResultSet rst=stm.executeQuery(query);
            Role role;
             while (rst.next()){                 
                 role = new Role(rst.getInt("role_id"),rst.getString("role_name"));
                 Roles.add(role);
             }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Roles;  
    }
//------------------------------------------------------------------

    public void AddRole(User u,Role r){           
        try {            
            Statement stm=cnx.createStatement();
            String query = "INSERT INTO user_role (user_id,role_id) VALUES('"+u.getUserId()+"','"+r.getRoleId()+"')";
            stm.executeUpdate(query);   
            System.out.println("Role added to user");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
  //------------------------------------------------------------------

    public void DeleteRole(User u,Role r){           
        try {            
            Statement stm=cnx.createStatement();
            String query = "DELETE FROM `user_role` WHERE `user_id`= '"+u.getUserId()+ "' and `role_id`='"+r.getRoleId()+"' " ;
            System.out.println(query);
            stm.executeUpdate(query);   
            System.out.println("Role Deleted to user");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }  
    
    
}
