/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;

import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.iservices.task.IServicePaidTask;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;

/**
 *
 * @author Admin
 */
public class ServicePaidTask implements IServicePaidTask {

    Connection con;
    ServiceTask servicet = new ServiceTask();
    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    ServiceTaskCategory stc = new ServiceTaskCategory();

    public ServicePaidTask() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void addPaidTask(int idTherapist, String title, PaidTask p) {
        servicet.createTask(idTherapist, title, p);
        Task t = new Task();
        try {
            Statement st = con.createStatement();
            String id = "select task_id from task where title='" + p.getTitle() + "' and description='" + p.getDescription() + "'";
            ResultSet rs = st.executeQuery(id);
            while (rs.next()) {
                t.setTaskId(Integer.valueOf(rs.getInt("task_id")));
            }
            String query = "insert into paid_task(task_id,price) values('" + t.getTaskId() + "','" + p.getPrice() + "');";
            st.executeUpdate(query);
            System.out.println("Paid Task ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout " + ex);
        }
    }

    @Override
    public List<PaidTask> listPaidTask() {
        List<PaidTask> pt = new ArrayList<PaidTask>();
        ImageView img = null;
        try {
            String query = "SELECT * FROM `paid_task` p join task t on p.task_id=t.task_id where t.is_deleted=0 order by t.created_at DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                PaidTask p = new PaidTask();
                p.setPrice(rs.getDouble("price"));
                p.setTaskId(rs.getInt("task_id"));
                p.setTitle(rs.getString("title"));
                p.setMaxUsers(rs.getInt("max_users"));
                p.setDescription(rs.getString("description"));
                p.setMinUsers(rs.getInt("min_users"));
                p.setNumOfDays(rs.getInt("num_of_days"));
                p.setCategory(stc.searchTaskCategoryById(rs.getInt("cat_id")));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
                img = new ImageView(url);
                p.setImg(img);

                pt.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage " + ex);
        }
        return pt;
    }

    @Override
    public void updatePaidTask(PaidTask t, int idpt) {
        try {
           // servicet.updateTask((Task)t, idpt);
            String query = "UPDATE  paid_task set price=" + t.getPrice() + " where task_id=" + idpt + ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deletePaidTask(int idpt) {
        try {
            String query = "delete from  paid_task  where task_id=" + idpt;
            Statement st = con.createStatement();
            st.executeUpdate(query);
            servicet.deleteTask(idpt);
            System.out.println("paid task supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public PaidTask getPaidTask(int idPT) {
        PaidTask t = null;
        ImageView img = null;
        try {

            Statement st = con.createStatement();
            String query = "select task_id,cat_id,img_url,title,description,num_of_days,min_users,max_users,created_at,price from task NATURAL JOIN paid_task where task_id=" + idPT + " and is_deleted=0 ;";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                PaidTask task = new PaidTask();
                task.setTaskId(rs.getInt("task_id"));
                task.setDescription(rs.getString("description"));
                task.setTitle(rs.getString("title"));
                task.setNumOfDays(rs.getInt("num_of_days"));
                task.setMinUsers(rs.getInt("min_users"));
                task.setMaxUsers(rs.getInt("max_users"));
                task.setImgUrl(rs.getString("img_url"));
                task.setPrice(rs.getDouble("price"));
                task.setCategory(stc.searchTaskCategoryById(rs.getInt("cat_id")));
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

    @Override
    public void makeFreeTask(int idPT) {
        try {
            String query = "delete from  paid_task  where task_id=" + idPT;
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void makePaidTask(int idPT, double price) {
        try {
            String query = "insert into paid_task(task_id,price) values('" + idPT + "','" + price + "');";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Paid Task ajouter ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<PaidTask> listPaidTaskByIdUser(int idU) {
        List<PaidTask> pt = new ArrayList<PaidTask>();
        ImageView img = null;
        try {
            String query = "SELECT * FROM `paid_task` p join task t on p.task_id=t.task_id where t.user_id="+idU +" and  t.is_deleted=0  order by t.created_at DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                PaidTask p = new PaidTask();
                p.setPrice(rs.getDouble("price"));
                p.setTaskId(rs.getInt("task_id"));
                p.setTitle(rs.getString("title"));
                p.setMaxUsers(rs.getInt("max_users"));
                p.setDescription(rs.getString("description"));
                p.setMinUsers(rs.getInt("min_users"));
                p.setNumOfDays(rs.getInt("num_of_days"));
                p.setCategory(stc.searchTaskCategoryById(rs.getInt("cat_id")));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
                img = new ImageView(url);
                p.setImg(img);

                pt.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage " + ex);
        }
        return pt;
    }

    @Override
    public int getCountPaidTask() {
    int nbr=0;
        try {
            String query = "SELECT COUNT(p.task_id) as n FROM `paid_task` p join task t on p.task_id=t.task_id where t.is_deleted=0";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int p=rs.getInt("n");
                nbr=p;
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage " + ex);
        }
        return nbr;   
    }

    @Override
    public List<PaidTask> searchPaidTaskByName(String title) {
        List<PaidTask> pt = new ArrayList<PaidTask>();
        ImageView img = null;
        try {
            String query = "SELECT * FROM `paid_task` p join task t on p.task_id=t.task_id where t.is_deleted=0 and t.title like '"+title+"%'  order by t.created_at DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                PaidTask p = new PaidTask();
                p.setPrice(rs.getDouble("price"));
                p.setTaskId(rs.getInt("task_id"));
                p.setTitle(rs.getString("title"));
                p.setMaxUsers(rs.getInt("max_users"));
                p.setDescription(rs.getString("description"));
                p.setMinUsers(rs.getInt("min_users"));
                p.setNumOfDays(rs.getInt("num_of_days"));
                p.setCategory(stc.searchTaskCategoryById(rs.getInt("cat_id")));
                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
                img = new ImageView(url);
                p.setImg(img);

                pt.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage " + ex);
        }
        return pt;
    }
}
