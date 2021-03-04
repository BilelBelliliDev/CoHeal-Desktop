/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;
import coheal.entities.task.TaskChat;
import coheal.iservices.task.IServiceTaskChat;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ServiceTaskChat implements IServiceTaskChat{
     private Connection con;

    public ServiceTaskChat() {
        con = MyConnection.getInstance().getConnection();
    }

     
    @Override
    public void createTaskChat(TaskChat tc) {
        try {
            String query = "INSERT INTO task_chat( task_id) "
                    + "VALUES ('" + tc.getPaidTask()  + "');";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Task chat ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout " + ex);
        }  
    }

    @Override
    public List<TaskChat> ListTaskChat() {
    ArrayList<TaskChat> t = new ArrayList();
        TaskChat taskChat = new TaskChat();
        try {

            Statement st = con.createStatement();
            String query = "select * from task_chat;";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                taskChat.setChatId(rs.getInt("chat_id"));
                
                //taskChat.setPaidTask(rs.getInt("task_id"));
               
                t.add(taskChat);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return t;    
    }

    @Override
    public void updateTaskChat(TaskChat tc) {
     try {
            
            String query = "UPDATE  task_chat set chat_id=" + tc.getChatId()+ "',' task_id=" + tc.getPaidTask().getTaskId() +  ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("modification avec succes");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }       
    }

    @Override
    public void deleteTaskChat(int idTCh) {
     
        try {
          
            String query = " DELETE FROM task_chat WHERE chat_id="+ idTCh + ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public TaskChat searchTaskChat(int idTCh) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
