package Entities;

import java.util.ArrayList;

/**
 *
 * @author wajdi's pc
 */
public class Role {
    //Role var
    private ArrayList<User> listUsers = new ArrayList<>();
    private int roleId;
    private String roleName;
    
    //constructor

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public ArrayList<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(ArrayList<User> listUsers) {
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
