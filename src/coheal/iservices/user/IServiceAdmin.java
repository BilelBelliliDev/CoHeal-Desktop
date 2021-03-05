/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.user;

import coheal.entities.user.Role;
import coheal.entities.user.User;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author wajdi's pc
 */
public interface IServiceAdmin {
    public ObservableList<User> GetListPersonnes();
    
    public ObservableList<Role> GetListRoles();
    
}
