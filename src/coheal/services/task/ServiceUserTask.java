/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;

import coheal.entities.task.PaidTask;
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
public class ServiceUserTask implements IServiceUserTask {

    Connection con;

    public ServiceUserTask() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void participer(int idUser, int idTask) {
        try {
            Statement st = con.createStatement();
            String query = "INSERT INTO user_task( user_id,task_id) "
                    + "VALUES ('" + idUser + "','" + idTask + "');";
            System.out.println(query);
            st.executeUpdate(query);
            System.out.println("Task actions ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout " + ex);
        }
    }

    @Override
    public ObservableList<Task> ListerTasksByIdUser(int id) {
        //select * from task t join user_task ut on t.task_id=ut.task_id where ut.user_id=1 and t.task_id not in (select tt.task_id from task tt natural join paid_task pt join user_task ut on pt.task_id=ut.task_id where ut.user_id=1)
        ObservableList<Task> l = FXCollections.observableArrayList();
        String query = "select * from task t join user_task ut on t.task_id=ut.task_id where ut.user_id=" + id+" and t.task_id not in (select tt.task_id from task tt natural join paid_task pt join user_task ut on pt.task_id=ut.task_id where ut.user_id="+id+")";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                l.add(new Task(rs.getInt("task_id"), rs.getInt("cat_id"), rs.getString("title"), rs.getString("description"), rs.getInt("num_of_days"), rs.getInt("min_users"), rs.getInt("max_users")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    @Override
    public ObservableList<PaidTask> ListerPaidTasksByIdUser(int id) {

       
        ObservableList<PaidTask> l = FXCollections.observableArrayList();
        String query = "select * from task natural join paid_task pt join user_task ut on pt.task_id=ut.task_id where ut.user_id=" + id;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                PaidTask pt = new PaidTask();
                pt.setTaskId(rs.getInt("task_id"));
                //pt.setCategory(rs.getInt("cat_id"));
                pt.setTitle(rs.getString("title"));
                pt.setDescription(rs.getString("description"));
                pt.setNumOfDays(rs.getInt("num_of_days"));
                pt.setMinUsers(rs.getInt("min_users"));
                pt.setMaxUsers(rs.getInt("max_users"));
                pt.setPrice(rs.getDouble("price"));
                l.add(pt);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

}
