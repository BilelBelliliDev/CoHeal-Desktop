package coheal.entities.user;

import java.sql.Timestamp;

/**
 *
 * @author wajdi's pc
 */
public class Admin {
    private int admin_id ;	
    private String first_name ;	
    private String last_name ;
    private String email ;	
    private String password ;	
    private Timestamp created_at ;	
    private Timestamp modified_at;

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    @Override
    public String toString() {
        return "Admin{" + "admin_id=" + admin_id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", password=" + password + ", created_at=" + created_at + ", modified_at=" + modified_at + '}';
    }
    
    
    
    
}
