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
public class TaskCategoryHolder {

    private String name;
    private final static TaskCategoryHolder INSTANCE = new TaskCategoryHolder();

    public static TaskCategoryHolder getINSTANCE() {
        return INSTANCE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
