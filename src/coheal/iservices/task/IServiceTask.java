/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.task;

import coheal.entities.task.Task;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IServiceTask {
    public void createTask(int therapistId,String title,Task t);
    public List<Task> ListTask();
    public void updateTask(Task t,int idt);
    public void deleteTask(int idt);
    public Task getTask(int idT);
    public int getCountTask();
    public List<Task> searchTaskByName(String title);
}
