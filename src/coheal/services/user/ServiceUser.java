package coheal.services.user;

import coheal.entities.user.User;
import coheal.utils.MyConnection;
import coheal.iservices.user.IServiceUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceUser implements IServiceUser{
    Connection cnx;
    List<User> Users = new ArrayList<>();
    
    public ServiceUser(){
        cnx=MyConnection.getInstance().getConnection();
    }
    
    //ajouter un utilisateur "SingUp"
    @Override
    public void AddUser(User u){           
        try {            
            Statement stm=cnx.createStatement();
            String query = "INSERT INTO user(email,password,first_name,last_name,date_of_birth) VALUES('"+u.getEmail()+"','"+u.getPassword()+"','"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getDateOfBirth()+"')";
            stm.executeUpdate(query);   
            System.out.println("user ajouter");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
  
    //---------login----------------------------------------------------
    @Override
    public boolean Validate_Login(String email , String password){           
        try {     
            String query = "SELECT * FROM user WHERE email = ? and password = ? and is_deleted=0 ";
            // Step 2:Create a statement using connection object
            PreparedStatement pS = cnx.prepareStatement(query);
            pS.setString(1, email);
            pS.setString(2, password);
            System.out.println(pS);
            
            ResultSet resultSet = pS.executeQuery();
            
            if (resultSet.next()) {
                //recuperation d'id de user loged in
                int id = resultSet.getInt("user_id") ;
                System.out.println(id);
                //user session created with user id when logging in
                UserSession.getInstace(id);
                return true;
            }

        } catch (SQLException ex) {
            // print SQL exception information
            printSQLException(ex);
        }
        return false;
    }
    
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

//---------------------------------------------
    @Override
    public List<User> AfficherPersonne() {        
        try {
            Statement stm=cnx.createStatement();
             String query = "SELECT * FROM `user` ";
             ResultSet rst=stm.executeQuery(query);
             
             while (rst.next()){
                 User p=new User();
                 p.setUserId(rst.getInt("user_id"));
                 p.setEmail(rst.getString("email"));
                 p.setPassword(rst.getString("password"));
                 
                 Users.add(p);
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Users;
       
    }
 //---------------------------------------------
    @Override
    public void ModifierUser(User u ,int id) {
        try {
            String query = "UPDATE `user` SET `email`= '"+u.getEmail()+"' ,`password`= '"
                +u.getPassword()+"' ,`first_name`= '"+u.getFirstName()+"' ,`last_name`= '"
                +u.getLastName()+"' ,`date_of_birth`= '"+u.getDateOfBirth()+"'"
                + " WHERE user_id = "+id+" ";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("User updated succesfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }   
    
 //---------------------------------------------   
    @Override
    public void DeleteUser(int idU) {
        try {
            String query = "UPDATE  user set  is_deleted=1,deleted_at=CURRENT_TIMESTAMP()  where user_id=" + idU +" ";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("User deleted succesfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  //---------------------------------------------

}
