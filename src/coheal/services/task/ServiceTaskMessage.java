/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.task;

import coheal.entities.task.TaskMessage;
import coheal.iservices.task.IServiceTaskMessage;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.util.List;
/**
 *
 * @author Admin
 */
public class ServiceTaskMessage implements IServiceTaskMessage{
     Connection con;

    public ServiceTaskMessage() {
        con = MyConnection.getInstance().getConnection();
    }
     
   @Override
    public void createTaskMessage(TaskMessage tm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TaskMessage> ListTaskMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTaskMessage(TaskMessage tm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTaskMessage(int idTM) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TaskMessage searchTaskMessage(int idTCM) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}
