/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.task;

/**
 *
 * @author Admin
 */
public class TaskHolder {

    private int id;
    private final static TaskHolder INSTANCE = new TaskHolder();

    public static TaskHolder getINSTANCE() {
        return INSTANCE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
