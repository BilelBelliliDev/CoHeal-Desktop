/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;


import coheal.iservices.task.IServiceUserTask;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author Admin
 */
public class ServiceUserTask implements IServiceUserTask{

    Connection con;

    public ServiceUserTask() {
        con = MyConnection.getInstance().getConnection();
    }
    
    
    @Override
    public void participer(int idUser, int idTask) {
       try {
           Statement st = con.createStatement();
            String query = "INSERT INTO user_task( user_id,task_id) "
                    + "VALUES ('" + idUser + "','" + idTask +  "');";
            System.out.println(query);
            st.executeUpdate(query);
            System.out.println("Task actions ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout " + ex);
        }
    }
    
}
