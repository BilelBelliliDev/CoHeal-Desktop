/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.task;

import coheal.entities.task.TaskChat;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IServiceTaskChat {
     public void createTaskChat(TaskChat tc);

    public List<TaskChat> ListTaskChat();

    public void updateTaskChat(TaskChat tc);

    public void deleteTaskChat(int idTCh);

    public TaskChat searchTaskChat(int idTCh);
}
