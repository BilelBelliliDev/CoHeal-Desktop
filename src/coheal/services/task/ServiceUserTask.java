/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;

import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.entities.task.UserTask;
import coheal.iservices.task.IServiceUserTask;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

/**
 *
 * @author Admin
 */
public class ServiceUserTask implements IServiceUserTask {

    Connection con;
    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    ServiceTaskCategory stc = new ServiceTaskCategory();

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
        ImageView img = null;
        String query = "select * from task t join user_task ut on t.task_id=ut.task_id where ut.user_id=" + id + " and t.task_id not in (select tt.task_id from task tt natural join paid_task pt join user_task ut on pt.task_id=ut.task_id where ut.user_id=" + id + ")";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setDescription(rs.getString("description"));
                task.setTitle(rs.getString("title"));
                task.setNumOfDays(rs.getInt("num_of_days"));
                task.setMinUsers(rs.getInt("min_users"));
                task.setMaxUsers(rs.getInt("max_users"));
                task.setCategory(stc.searchTaskCategoryById(rs.getInt("cat_id")));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
                img = new ImageView(url);
                task.setImg(img);

                l.add(task);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    @Override
    public ObservableList<PaidTask> ListerPaidTasksByIdUser(int id) {
        ImageView img = null;
        ObservableList<PaidTask> l = FXCollections.observableArrayList();
        String query = "select * from task natural join paid_task pt join user_task ut on pt.task_id=ut.task_id where ut.user_id=" + id;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                PaidTask pt = new PaidTask();
                pt.setTaskId(rs.getInt("task_id"));
                pt.setTitle(rs.getString("title"));
                pt.setDescription(rs.getString("description"));
                pt.setNumOfDays(rs.getInt("num_of_days"));
                pt.setMinUsers(rs.getInt("min_users"));
                pt.setMaxUsers(rs.getInt("max_users"));
                pt.setPrice(rs.getDouble("price"));
                pt.setCategory(stc.searchTaskCategoryById(rs.getInt("cat_id")));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
                img = new ImageView(url);
                pt.setImg(img);
                l.add(pt);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    ServiceTask serviceT = new ServiceTask();

    public UserTask getUserTask(int idU, int idT) {
        UserTask ut = new UserTask();
        try {

            String query = "select * from user_task where user_id=" + idU + " and task_id=" + idT;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                UserTask u = new UserTask();
                u.setTask(serviceT.getTask(rs.getInt("task_id")));
                u.setUser(serviceT.getUserById(rs.getInt("user_id")));
                ut = u;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ut;
    }

    @Override
    public Task ListerOneTasksByIdUser(int id) {
    
        ImageView img = null;
        Task l = new Task();
        String query = "select * from task t join user_task ut on t.task_id=ut.task_id where ut.user_id=" + id + " and t.task_id not in (select tt.task_id from task tt natural join paid_task pt join user_task ut on pt.task_id=ut.task_id where ut.user_id=" + id + ") ORDER by ut.created_at DESC LIMIT 1";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setDescription(rs.getString("description"));
                task.setTitle(rs.getString("title"));
                task.setNumOfDays(rs.getInt("num_of_days"));
                task.setMinUsers(rs.getInt("min_users"));
                task.setMaxUsers(rs.getInt("max_users"));
                task.setCategory(stc.searchTaskCategoryById(rs.getInt("cat_id")));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
                img = new ImageView(url);
                task.setImg(img);

                l=task;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    @Override
    public PaidTask ListerOnePaidTasksByIdUser(int id) {
    
        ImageView img = null;
        PaidTask l = new PaidTask();
        String query = "select * from task natural join paid_task pt join user_task ut on pt.task_id=ut.task_id where ut.user_id=" + id+" ORDER by ut.created_at DESC LIMIT 1";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                PaidTask pt = new PaidTask();
                pt.setTaskId(rs.getInt("task_id"));
                pt.setTitle(rs.getString("title"));
                pt.setDescription(rs.getString("description"));
                pt.setNumOfDays(rs.getInt("num_of_days"));
                pt.setMinUsers(rs.getInt("min_users"));
                pt.setMaxUsers(rs.getInt("max_users"));
                pt.setPrice(rs.getDouble("price"));
                pt.setCategory(stc.searchTaskCategoryById(rs.getInt("cat_id")));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
                img = new ImageView(url);
                pt.setImg(img);
                l=pt;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
    
    public List<UserTask> getNbrParticipateByDate(){
        List<UserTask> listUserTasks=new ArrayList();
        String query="SELECT count(user_id) as nb , Date(created_at) as date FROM `user_task` GROUP BY Date(created_at)";
        try {                            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                UserTask ut=new UserTask();
                ut.setNbr(rs.getInt("nb"));
                ut.setCreatedAt(rs.getDate("date"));
                listUserTasks.add(ut);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUserTasks;
    }
}
