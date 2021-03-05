/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;

import coheal.entities.task.Task;
import coheal.entities.task.TaskActions;
import coheal.iservices.task.IServiceTaskActions;
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
public class ServiceTaskActions implements IServiceTaskActions {

    Connection con;

    public ServiceTaskActions() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void createTaskActions(String titre, TaskActions t) {
        try {
            Statement st = con.createStatement();
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());

            Task task = new Task();
            String selecttaskId = "select * from task where title='" + titre + "';";
            ResultSet rs = st.executeQuery(selecttaskId);
            while (rs.next()) {

                task.setTaskId(rs.getInt("task_id"));

                System.out.println(task.getTaskId());
            }
            String query = "INSERT INTO task_actions( task_id, title, description) "
                    + "VALUES ('" + task.getTaskId() + "','" + t.getTitle() + "','" + t.getDescription() + "');";
            System.out.println(query);
            st.executeUpdate(query);
            System.out.println("Task actions ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout " + ex);
        }
    }

    @Override
    public List<TaskActions> ListTaskActions() {
        ArrayList<TaskActions> t = new ArrayList();
        TaskActions taskAction = new TaskActions();
        try {

            Statement st = con.createStatement();
            String query = "SELECT action_id, task_id, title, description FROM task_actions";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
//                taskAction.setActionId(rs.getInt("action_id"));
//                taskAction.setTitle(rs.getString("title"));
//                //taskAction.setTask(rs.getInt("task_id"));
//                taskAction.setTitle(rs.getString("description"));

                t.add(new TaskActions(rs.getInt("action_id"), rs.getString("title"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return t;
    }

    @Override
    public void updateTaskActions(TaskActions t) {
        try {

            String query = "UPDATE  task_actions set title=" + t.getTitle() + "',' description=" + t.getDescription() + ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("modification avec succes");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTaskActions(int idTA) {
        try {

            String query = "DELETE FROM task_actions WHERE action_id=" + idTA + ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("suppression avec succes");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public TaskActions searchTaskActions(int idTA) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TaskActions> ListTaskActionsByTaskId(int id) {
        ArrayList<TaskActions> t = new ArrayList();
        TaskActions taskAction = new TaskActions();
        try {

            Statement st = con.createStatement();
            String query = "SELECT action_id, task_id, title, description FROM task_actions where task_id="+id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                t.add(new TaskActions(rs.getInt("action_id"), rs.getString("title"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return t;
    }
}
