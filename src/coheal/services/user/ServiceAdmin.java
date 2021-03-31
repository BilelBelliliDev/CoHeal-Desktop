package coheal.services.user;

import coheal.entities.user.Role;
import coheal.entities.user.User;
import coheal.entities.user.UserRole;
import coheal.iservices.user.IServiceAdmin;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author wajdi's pc
 */
public class ServiceAdmin implements IServiceAdmin {

    Connection cnx;

    public ServiceAdmin() {
        cnx = MyConnection.getInstance().getConnection();
    }

    //------------------------------------------------------------------
    @Override
    public ObservableList<Role> GetListRoles() {
        ObservableList<Role> Roles = FXCollections.observableArrayList();
        try {
            String query = "SELECT role_id,role_name FROM `role` ";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(query);
            Role role;
            while (rst.next()) {
                role = new Role(rst.getInt("role_id"), rst.getString("role_name"));
                Roles.add(role);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Roles;
    }

    //------------------------------------------------------------------
    //------------------------------------------------------------------
    @Override
    public ObservableList<User> GetListPersonnes() {
        ObservableList<User> Users = FXCollections.observableArrayList();
        try {
            String query = "SELECT user_id,email,first_name,last_name FROM `user` ";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(query);
            User user;
            while (rst.next()) {
                user = new User(rst.getInt("user_id"), rst.getString("email"), rst.getString("first_name"), rst.getString("last_name"));
                Users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Users;
    }

    //------------------------------------------------------------------
    //------------------------------------------------------------------
    public List<Role> GetUserRoles(User U) {
        List<Role> Roles = new ArrayList<>();
        try {
            String query = "select r.role_id, r.role_name from role r ,user_role ur where r.role_id = ur.role_id and ur.user_id= '" + U.getUserId() + "' ";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Role R = new Role();
                R.setRoleId(rst.getInt("role_id"));
                R.setRoleName(rst.getString("role_name"));

                Roles.add(R);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Roles;
    }

    //------------------------------------------------------------------
    //------------------------------------------------------------------
    public void AddRole(User u, int r) {
        try {
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO user_role (user_id,role_id) VALUES('" + u.getUserId() + "','" + r + "')";
            stm.executeUpdate(query);
            System.out.println("Role added to user");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //------------------------------------------------------------------
    //------------------------------------------------------------------
    public void DeleteRole(User u, int r) {
        try {
            Statement stm = cnx.createStatement();
            String query = "DELETE FROM `user_role` WHERE `user_id`= '" + u.getUserId() + "' and `role_id`='" + r + "' ";
            stm.executeUpdate(query);
            System.out.println("Role Deleted to user");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //------------------------------------------------------------------
    //------------------------------------------------------------------
    public boolean SearchUser_Role(User u, int r) {
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM `user_role` WHERE `user_id`= '" + u.getUserId() + "' and `role_id`='" + r + "' ";
            ResultSet rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    //--------------------------------------------------------------------
    public List<Role> UserByRole() {
        ArrayList<Role> R = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT role.role_name , COUNT(user_role.role_id) AS nbruserbytype FROM role LEFT JOIN user_role ON user_role.role_id=role.role_id GROUP BY role_name";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Role role = new Role();

                role = new Role(rs.getInt("nbruserbytype"), rs.getString("role_name"));
                R.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return R;
    }

}
