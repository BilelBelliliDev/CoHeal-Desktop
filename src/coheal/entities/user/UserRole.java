package coheal.entities.user;

import java.sql.Timestamp;

/**
 *
 * @author wajdi's pc
 */
public class UserRole {
    private User user;
    private Role role;
    private Timestamp createdAt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
    
}
