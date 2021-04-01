/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.task;

import coheal.entities.task.PaidTask;
import coheal.entities.task.Task;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IServicePaidTask {

    public void addPaidTask(int idTherapist, String title, PaidTask p);

    public List<PaidTask> listPaidTask();

    public void updatePaidTask(PaidTask t, int idpt);

    public void deletePaidTask(int idpt);

    public PaidTask getPaidTask(int idPT);

    public void makeFreeTask(int idPT);

    public void makePaidTask(int idPT, double price);

    public int getCountPaidTask();
     public List<PaidTask> searchPaidTaskByName(String title);
}
