/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;

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
/**
 *
 * @author Admin
 */
public class ServiceTaskCategory implements IServiceTaskCategory{
    
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
               
                t.add(new TaskCategory(rs.getInt("cat_id"),rs.getString("name"),rs.getString("img_url")));
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
            String query = "UPDATE  task_category set  is_deleted=" + 1 +",deleted_at='"+d +"' where cat_id=" + idTC + ";";
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
}
