/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.task;

import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public interface IServiceTaskCategory {
    public void createTaskCategory(TaskCategory t);
    public List<TaskCategory> ListTaskCategory();
    public void updateTaskCategory(TaskCategory t);
    public void deleteTaskCategory(int idTC);
    public TaskCategory searchTaskCategory(int idTC);
     public ObservableList<Task> ListerTasksByIdCatg(String title);
}
