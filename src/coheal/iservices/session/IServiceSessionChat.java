/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.session;

import coheal.entities.session.SessionChat;
import java.util.List;

/**
 *
 * @author acer
 */
public interface IServiceSessionChat {
    public void createSessionChat(SessionChat s);
    public void joindreSession(SessionChat s);
    public List<SessionChat> listechat();
    
}
