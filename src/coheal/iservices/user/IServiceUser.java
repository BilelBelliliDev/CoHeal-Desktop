package coheal.iservices.user;

import coheal.entities.user.User;
import java.util.List;

/**
 *
 * @author wajdi's pc
 */
public interface IServiceUser {
    public void AddUser(User u);           
    public boolean Validate_Login(String email , String password);
    public List<User> AfficherPersonne();
    public void ModifierUser(User u ,int id);
    public void DeleteUser(int idU);
}
