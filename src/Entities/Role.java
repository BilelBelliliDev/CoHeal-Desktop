package Entities;

import java.util.ArrayList;

/**
 *
 * @author wajdi's pc
 */
public class Role {
    //Role var
    private ArrayList<User> listUsers = new ArrayList<>();
    private String roleName;
    
    //constructor

    public Role(String roleName) {
        this.roleName = roleName;
    }


    
    //getters & setters

    public ArrayList<User> getRoleId() {
        return listUsers;
    }

    public void setRoleId(ArrayList<User> listUsers) {
        this.listUsers = listUsers;
    }



    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    //toString

    @Override
    public String toString() {
        return "Role{" + "listUsers=" + listUsers + ", roleName=" + roleName + '}'+"/n";
    }
    
    
}
