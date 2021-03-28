/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;

import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.entities.user.User;
import coheal.iservices.task.IServiceTask;
import coheal.utils.MyConnection;
import java.sql.Connection;
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
import javafx.scene.image.ImageView;

/**
 *
 * @author Admin
 */
public class ServiceTask implements IServiceTask {

    private Connection con;
    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    private ServiceTaskCategory stc = new ServiceTaskCategory();

    public ServiceTask() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void createTask(int therapistId, String title, Task t) {
        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            Statement st = con.createStatement();
            String selectCategoryId = "select * from task_category where name='" + title + "';";
            ResultSet rs = st.executeQuery(selectCategoryId);
            while (rs.next()) {
                TaskCategory tc = new TaskCategory();
                tc.setCatgid(rs.getInt("cat_id"));
                t.setCategory(tc);
            }
            String query = "INSERT INTO task(user_id,cat_id, img_url, title, description, num_of_days, min_users, max_users, created_at) "
                    + "VALUES ('" + therapistId + "','" + t.getCategory().getCatgid() + "','" + t.getImgUrl() + "','"
                    + t.getTitle() + "','" + t.getDescription() + "','" + t.getNumOfDays() + "','" + t.getMinUsers() + "','"
                    + t.getMaxUsers() + "','" + d + "');";

            st.executeUpdate(query);

            System.out.println("Task ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout " + ex);
        }
    }

    @Override
    public List<Task> ListTask() {

        ArrayList<Task> t = new ArrayList();
        ImageView img = null;
        try {

            Statement st = con.createStatement();
            String query = "select task_id,cat_id,img_url,title,description,num_of_days,min_users,max_users from task  where is_deleted=0 and task_id not in(select t.task_id from paid_task t natural join task );";
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
                t.add(task);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return t;
    }

    @Override
    public void updateTask(Task t, int idt) {
        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            String query = "UPDATE  task set cat_id=" + t.getCategory().getCatgid() + ", img_url='" + t.getImgUrl() + "', title='" + t.getTitle() + "', description='" + t.getDescription() + "', num_of_days=" + t.getNumOfDays() + ", min_users=" + t.getMinUsers() + ", max_users=" + t.getMaxUsers() + " where task_id=" + idt + ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("modification avec succes");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTask(int idt) {

        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            String query = "UPDATE  task set  is_deleted=" + 1 + ",deleted_at='" + d + "' where task_id=" + idt;
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Task getTask(int idT) {
        Task t = null;
        ImageView img = null;
        try {

            Statement st = con.createStatement();
            String query = "select task_id,user_id,cat_id,img_url,title,description,num_of_days,min_users,max_users,created_at from task  where task_id=" + idT + " and is_deleted=0 ;";
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
                task.setImgUrl(rs.getString("img_url"));
                User u=getUserById(rs.getInt("user_id"));
                task.setUser(u);
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
                img = new ImageView(url);
                task.setImg(img);
                task.setCreatedAt(rs.getTimestamp("created_at"));
                t = task;
            }

        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return t;
    }
    
    public User getUserById(int idU){
        User u = null;
        String query="select * from user where user_id="+idU;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User user=new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                u=user;
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return u;
    }
    
    public List<Task> ListTaskByIdUser(int idU) {

        ArrayList<Task> t = new ArrayList();
        ImageView img = null;
        try {

            Statement st = con.createStatement();
            String query = "select task_id,cat_id,img_url,title,description,num_of_days,min_users,max_users from task  where is_deleted=0 and user_id="+idU+" and task_id not in(select t.task_id from paid_task t natural join task );";
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
                t.add(task);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return t;
    }

    @Override
    public int getCountTask() {
    
        int nbr=0;
        try {

            Statement st = con.createStatement();
            String query = "select COUNT(task_id) as n from task where is_deleted=0 and task_id not in(select t.task_id from paid_task t natural join task );";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int t=rs.getInt("n");
                nbr=t;
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return nbr;
    }
    
    public Task getLastTask(){
        Task task =null;
        ImageView img = null;
        try {

            Statement st = con.createStatement();
            String query = "SELECT * FROM `task` where is_deleted=0 ORDER BY created_at DESC limit 1";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Task t = new Task();
                t.setTaskId(rs.getInt("task_id"));
                t.setTitle(rs.getString("title"));
                t.setUser(getUserById(rs.getInt("user_id")));
                t.setCreatedAt(rs.getTimestamp("created_at"));
               task=t;
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return task;
    }
}
