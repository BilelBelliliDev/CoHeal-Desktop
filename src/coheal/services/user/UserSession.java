package coheal.services.user;

/**
 *
 * @author wajdi's pc
 */
public final class UserSession {

    private static UserSession instance;

    private static int user_id;

    private UserSession(int user_id) {
        this.user_id = user_id;
    }

    public static UserSession getInstace(int user_id ) {
        if(instance == null) {
            instance = new UserSession(user_id);
        }
        return instance;
    }

    public static int getUser_id() {
        return user_id;
    }
    
    public void cleanUserSession() {
        user_id = -1;       
    }

    @Override
    public String toString() {
        return "UserSession{" + "userName='" + user_id +'}';
    }
}