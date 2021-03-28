/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;

import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.iservices.task.IServiceTaskCategory;
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
public class ServiceTaskCategory implements IServiceTaskCategory {

    Connection con;
    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");

    public ServiceTaskCategory() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void createTaskCategory(TaskCategory t) {
        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            String query = "INSERT INTO task_category( name, img_url,created_at) "
                    + "VALUES ('" + t.getName() + "','" + t.getImgUrl() + "','" + d + "');";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Task category ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout " + ex);
        }
    }

    @Override
    public List<TaskCategory> ListTaskCategory() {
        List<TaskCategory> t = new ArrayList();

        ImageView img = null;
        try {

            Statement st = con.createStatement();
            String query = "select cat_id,name,img_url from task_category where is_deleted=0";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                TaskCategory taskCategory = new TaskCategory();
                taskCategory.setCatgid(rs.getInt("cat_id"));
                taskCategory.setName(rs.getString("name"));
                taskCategory.setImgUrl(rs.getString("img_url"));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
                img = new ImageView(url);
                taskCategory.setImg(img);
                t.add(taskCategory);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage" + ex.getMessage());
        }
        return t;
    }

    @Override
    public void updateTaskCategory(TaskCategory t) {
        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            String query = "UPDATE  task_category set name='" + t.getName() + "', img_url='" + t.getImgUrl() + "' where cat_id=" + t.getCatgid() + ";";
           
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("modification avec succes");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTaskCategory(int idTC) {
        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            String query = "UPDATE  task_category set  is_deleted=" + 1 + ",deleted_at='" + d + "' where cat_id=" + idTC + ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public TaskCategory searchTaskCategory(String titre) {
        String query = "select * from task_category where name='" + titre + "';";
        TaskCategory categ = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                categ = new TaskCategory();
                categ.setCatgid(rs.getInt("cat_id"));
                categ.setName(rs.getString("name"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categ;
    }

    @Override
    public List<Task> ListerTasksByIdCatg(String title) {
        List<Task> l = new ArrayList<>() ;
        ImageView img = null;
        try {
            Statement st = con.createStatement();
            String selectCategoryId = "select * from task_category where name='" + title + "';";
            ResultSet rs = st.executeQuery(selectCategoryId);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("cat_id");
            }

            String query = "select task_id,cat_id,img_url,title,description,num_of_days,min_users,max_users from task where is_deleted=0 and cat_id=" + id + " and task_id not in(select t.task_id from paid_task t natural join task );";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {
                Task task = new Task();
                task.setTaskId(rst.getInt("task_id"));
                task.setDescription(rst.getString("description"));
                task.setTitle(rst.getString("title"));
                task.setNumOfDays(rst.getInt("num_of_days"));
                task.setMinUsers(rst.getInt("min_users"));
                task.setMaxUsers(rst.getInt("max_users"));
                
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rst.getString("img_url");
                img = new ImageView(url);
                System.out.println(url);
                task.setImg(img);
                l.add(task);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage " + ex.getMessage());
        }
        return l;

    }

    
    public TaskCategory searchTaskCategoryById(int id) {
        String query = "select * from task_category where cat_id=" + id + ";";
        TaskCategory categ = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                categ = new TaskCategory();
                categ.setCatgid(rs.getInt("cat_id"));
                categ.setName(rs.getString("name"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categ;
    }

    @Override
    public List<PaidTask> ListerPaidTasksByIdCatg(String title) {
         List<PaidTask> l = new ArrayList<>();
        ImageView img = null;
        try {
            Statement st = con.createStatement();
            String selectCategoryId = "select * from task_category where name='" + title + "';";
            ResultSet rs = st.executeQuery(selectCategoryId);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("cat_id");
            }

            String query = "select task_id,cat_id,img_url,title,description,num_of_days,min_users,max_users,price from task natural join paid_task where is_deleted=0 and cat_id=" + id + ";";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {
                PaidTask task = new PaidTask();
                task.setTaskId(rst.getInt("task_id"));
                task.setDescription(rst.getString("description"));
                task.setTitle(rst.getString("title"));
                task.setNumOfDays(rst.getInt("num_of_days"));
                task.setMinUsers(rst.getInt("min_users"));
                task.setMaxUsers(rst.getInt("max_users"));
                task.setPrice(rst.getDouble("price"));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rst.getString("img_url");
                img = new ImageView(url);
                task.setImg(img);
                l.add(task);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage " + ex.getMessage());
        }
        return l;
    }
}
