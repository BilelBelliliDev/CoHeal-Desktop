/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.task;

import coheal.entities.task.TaskMessage;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IServiceTaskMessage {
    public void createTaskMessage(TaskMessage tm);

    public List<TaskMessage> ListTaskMessage();

    public void updateTaskMessage(TaskMessage tm);

    public void deleteTaskMessage(int idTM);

    public TaskMessage searchTaskMessage(int idTCM);
}
