/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;


import coheal.entities.task.Task;
import coheal.iservices.task.IServiceUserTask;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    @Override
    public ObservableList<Task> ListerTasksByIdUser(int id) {
         ObservableList<Task> l=FXCollections.observableArrayList();
         String query="select * from task natural join user_task where user_id="+id;
        try {
            Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
              
                l.add(new Task(rs.getInt("task_id"),rs.getInt("cat_id"),rs.getString("title"),rs.getString("description"),rs.getInt("num_of_days"),rs.getInt("min_users"),rs.getInt("max_users")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return l;
    }


    
}
