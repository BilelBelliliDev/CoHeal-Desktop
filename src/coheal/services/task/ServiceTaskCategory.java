/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class ServiceTaskCategory implements IServiceTaskCategory {

    Connection con;

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
        TaskCategory taskCategory = new TaskCategory();
        try {

            Statement st = con.createStatement();
            String query = "select cat_id,name,img_url from task_category where is_deleted=0";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                t.add(new TaskCategory(rs.getInt("cat_id"), rs.getString("name"), rs.getString("img_url")));
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return t;
    }

    @Override
    public void updateTaskCategory(TaskCategory t) {
        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            String query = "UPDATE  task_category set name='" + t.getName() + "', img_url='" + t.getImgUrl() + "' where cat_id=" + t.getCatgid() + ";";
            System.out.println(query);
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
            System.out.println(query);
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public TaskCategory searchTaskCategory(int idTC) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Task> ListerTasksByIdCatg(String title) {
        ObservableList<Task> l = FXCollections.observableArrayList();
        try {
            Statement st = con.createStatement();
            String selectCategoryId = "select * from task_category where name='" + title + "';";
            ResultSet rs = st.executeQuery(selectCategoryId);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("cat_id");
            }

            String query = "select task_id,cat_id,img_url,title,description,num_of_days,min_users,max_users from task where is_deleted=0 and cat_id=" + id + ";";
            System.out.println(query);
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {
                l.add(new Task(rst.getInt("task_id"), rst.getInt("cat_id"), rst.getString("title"), rst.getString("description"), rst.getInt("num_of_days"), rst.getInt("min_users"), rst.getInt("max_users")));
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return l;

    }
}
