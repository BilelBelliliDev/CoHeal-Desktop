/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.recipe;

/**
 *
 * @author HP
 */
public class RecipeCategoryHolder {

    private String name;
    private final static RecipeCategoryHolder INSTANCE = new RecipeCategoryHolder();

    public static RecipeCategoryHolder getINSTANCE() {
        return INSTANCE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
