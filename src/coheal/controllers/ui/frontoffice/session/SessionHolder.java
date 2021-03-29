/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.controllers.ui.frontoffice.session;

/**
 *
 * @author acer
 */
public class SessionHolder {
      private int id;
    private final static SessionHolder INSTANCE = new SessionHolder();

    public static SessionHolder getINSTANCE() {
        return INSTANCE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
