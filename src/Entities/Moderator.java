package Entities;

import java.sql.Date;

/**
 *
 * @author wajdi's pc
 */
public class Moderator extends User{

    //constructor
    public Moderator(int userId, String email, String password, String firstName, String lastName, Date dateOfBirth) {
        super(userId, email, password, firstName, lastName, dateOfBirth);
    }
   

    
}
