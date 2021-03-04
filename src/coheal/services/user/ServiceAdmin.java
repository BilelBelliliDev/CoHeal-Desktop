package coheal.services.user;

import coheal.entities.user.Role;
import coheal.entities.user.User;
import coheal.iservices.user.IServiceAdmin;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wajdi's pc
 */
public class ServiceAdmin implements IServiceAdmin{
    Connection cnx;
    List<User> Users = new ArrayList<>();
    
    public ServiceAdmin(){
        cnx=MyConnection.getInstance().getConnection();
    }
    
     public List<User> AfficherListPersonnes() {        
        try {
            Statement stm=cnx.createStatement();
             String query = "SELECT * FROM `user` ";
             ResultSet rst=stm.executeQuery(query);
             
             while (rst.next()){
                 User u=new User();
                 
                 u.setEmail(rst.getString("email"));
                 u.setPassword(rst.getString("password"));
                 
                 Users.add(u);
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Users;
       
    }
 //---------------------------------------------

    @Override
    public List<Role> AfficherListRoles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 //---------------------------------------------
    
    
    
}
