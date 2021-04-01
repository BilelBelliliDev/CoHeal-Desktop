/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.session;

import coheal.entities.session.SessionMessage;
import java.util.List;

/**
 *
 * @author acer
 */
public interface IServiceMsgInterface {
            public void createSessionMesage(SessionMessage s);
            public List<SessionMessage> listMessage(int i);
           

}
