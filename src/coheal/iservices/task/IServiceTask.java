/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.Task;


import coheal.entities.Task.Task;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IServiceTask {
    public void createTask(int therapistId,String title,Task t);
    public List<Task> ListTask();
    public void updateTask(Task t);
    public void deleteTask(int idt);
    public Task searchTask(int idT);
}
