/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.event;

/**
 *
 * @author Admin
 */
public class EventCategoryHolder {
     private String name;
    private final static EventCategoryHolder INSTANCE = new EventCategoryHolder();

    public static EventCategoryHolder getINSTANCE() {
        return INSTANCE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
