/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.task;

import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public interface IServiceUserTask {

    public void participer(int idUser, int idTask);

    public ObservableList<Task> ListerTasksByIdUser(int id);

    public ObservableList<PaidTask> ListerPaidTasksByIdUser(int id);
}
