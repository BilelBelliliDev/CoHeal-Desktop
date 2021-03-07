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

/**
 *
 * @author Admin
 */
public class ServicePaidTask implements IServicePaidTask {

    Connection con;
    ServiceTask st = new ServiceTask();

    public ServicePaidTask() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void addPaidTask(int idTherapist, String title, PaidTask p) {
        st.createTask(idTherapist, title, p);
        Task t = new Task();
        try {
            Statement st = con.createStatement();
            String id = "select task_id from task where title='" + p.getTitle() + "' and description='" + p.getDescription() + "'";
            ResultSet rs = st.executeQuery(id);
            while (rs.next()) {
                t.setTaskId(Integer.valueOf(rs.getInt("task_id")));;
                System.out.println(t.getTaskId());
            }
            String query = "insert into paid_task(task_id,price) values('" + t.getTaskId() + "','" + p.getPrice() + "');";
            System.out.println(query);
             st.executeUpdate(query);
            System.out.println("Paid Task ajouter ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'ajout " + ex);
        }
    }

    @Override
    public List<PaidTask> listPaidTask() {
        List<PaidTask> pt = new ArrayList<PaidTask>();
       
        try {
            String query = "SELECT * FROM `paid_task` p join task t on p.task_id=t.task_id";
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

                pt.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage " + ex);
        }
        return pt;
    }

}
