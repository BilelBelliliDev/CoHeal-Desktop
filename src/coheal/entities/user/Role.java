package coheal.entities.user;

import java.util.ArrayList;

/**
 *
 * @author wajdi's pc
 */
public class Role {
    //Role var
    private ArrayList<UserRole> listUsers = new ArrayList<>();
    private int roleId;
    private String roleName;

    public ArrayList<UserRole> getListUsers() {
        return listUsers;
    }

    public void setListUsers(ArrayList<UserRole> listUsers) {
        this.listUsers = listUsers;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    //getters & setters
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    //toString

    @Override
    public String toString() {
        return "Role{" + "listUsers=" + listUsers + ", roleId=" + roleId + ", roleName=" + roleName + '}';
    }
    
    
    
}
