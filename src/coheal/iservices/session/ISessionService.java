/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.session;

import coheal.entities.session.Session;
import java.util.List;

/**
 *
 * @author acer
 */
public interface ISessionService {
     public void createSession(Session s);
        public void modifierSession(Session s,int i);
        public void SupprimerSession(Session s,int i);
        public List<Session> listSesion();
    
    
}
