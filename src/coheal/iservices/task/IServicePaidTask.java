/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.task;

import coheal.entities.task.PaidTask;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IServicePaidTask {
    public void addPaidTask(int idTherapist,String title,PaidTask p);
    public List<PaidTask> listPaidTask();
}
