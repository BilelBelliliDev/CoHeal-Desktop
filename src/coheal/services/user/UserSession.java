package coheal.services.user;

import java.sql.Date;

/**
 *
 * @author wajdi's pc
 */
public final class UserSession {

    private static UserSession instance;

    private static int user_id;
    private static String email;
    private static String password;
    private static String first_name;
    private static String last_name;    
    private static Date date_of_birth;
    private static String role;
    
    private UserSession(int user_id ,String email ,String password ,String first_name ,String last_name,Date date_of_birth,String role) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth=date_of_birth;
        this.role=role;
    }

    public static UserSession getInstace(int user_id ,String email,String password,String first_name,String last_name,Date date_of_birth,String role) {
        if(instance == null) {
            instance = new UserSession(user_id,email, password, first_name,last_name,date_of_birth,role);
        }
        return instance;
    }

    public static int getUser_id() {
        return user_id;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static String getFirst_name() {
        return first_name;
    }

    public static String getLast_name() {
        return last_name;
    }

    public static Date getDate_of_birth() {
        return date_of_birth;
    }

    public static String getRole() {
        return role;
    }
    
    
    public static void cleanUserSession() {
        instance = null;       
    }
}