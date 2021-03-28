/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.task;

import coheal.entities.task.TaskActions;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IServiceTaskActions {
     public void createTaskActions(int id,TaskActions t);
    public List<TaskActions> ListTaskActions();
    public void updateTaskActions(int id,TaskActions t);
    public void deleteTaskActions(int idTA);
    public TaskActions searchTaskActions(int idTA);
    public List<TaskActions> ListTaskActionsByTaskId(int id);

}
