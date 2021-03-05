package coheal.controllers.task;


import coheal.entities.task.Task;
import coheal.entities.task.TaskActions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public final class TaskActionsHolder {

    private int tc;
    private final static TaskActionsHolder INSTANCE = new TaskActionsHolder();

    public TaskActionsHolder() {
    }


    public static TaskActionsHolder getINSTANCE() {
        return INSTANCE;
    }

    
    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }

}
