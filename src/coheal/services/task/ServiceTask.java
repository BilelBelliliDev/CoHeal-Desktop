/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
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
/**
 *
 * @author Admin
 */
public class ServiceTask implements IServiceTask {
    
     Connection con;

    public ServiceTask() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void createTask(int therapistId,String title,Task t) {
        try {
             Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            Statement st = con.createStatement();
             String selectCategoryId="select * from task_category where name='"+title+"';" ;
              ResultSet rs = st.executeQuery(selectCategoryId);
             while (rs.next()) {
          
           TaskCategory tc=new TaskCategory();
           tc.setCatgid(rs.getInt("cat_id"));
           t.setCategory(tc);
           System.out.println(tc.getCatgid());
             }
            
            
            String query = "INSERT INTO task(user_id,cat_id, img_url, title, description, num_of_days, min_users, max_users, created_at) "
                    + "VALUES ('"+therapistId + "','" + t.getCategory().getCatgid() + "','" + t.getImgUrl() + "','" 
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
        Task task = new Task();
        try {

            Statement st = con.createStatement();
            String query = "select task_id,cat_id,img_url,title,description,num_of_days,min_users,max_users from task where is_deleted=0;";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
              
                t.add(new Task(rs.getInt("task_id"),rs.getInt("cat_id"),rs.getString("title"),rs.getString("description"),rs.getInt("num_of_days"),rs.getInt("min_users"),rs.getInt("max_users")));
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return t;
    }

    @Override
    public void updateTask(Task t,int idt) {
        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            String query = "UPDATE  task set cat_id=" + t.getCategory().getCatgid() + ", img_url='" + t.getImgUrl() + "', title='" + t.getTitle() + "', description='" + t.getDescription() + "', num_of_days=" + t.getNumOfDays() + ", min_users=" + t.getMinUsers() + ", max_users=" + t.getMaxUsers() +" where task_id="+idt+";";
            System.out.println(query);
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
            String query = "UPDATE  task set  is_deleted=" + 1 +",deleted_at='"+d +"' where task_id=" + idt ;
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Task searchTask(int idT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
