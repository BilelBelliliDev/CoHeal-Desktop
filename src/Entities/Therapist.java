package Entities;

import java.sql.Date;

/**
 *
 * @author wajdi's pc
 */
public class Therapist extends User{  
    
    //Therapist var
    private String speciality;
    private short canCreateSession;
    private short canCreateTask;
    private short canCreateBook;
    
    //Therapist constructor
    public Therapist(String speciality, short canCreateSession, short canCreateTask, short canCreateBook, int userId, String email, String password, String firstName, String lastName, Date dateOfBirth) {
        super(userId, email, password, firstName, lastName, dateOfBirth);
        this.speciality = speciality;
        this.canCreateSession = canCreateSession;
        this.canCreateTask = canCreateTask;
        this.canCreateBook = canCreateBook;
    }

    //getters & setters

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public short getCanCreateSession() {
        return canCreateSession;
    }

    public void setCanCreateSession(short canCreateSession) {
        this.canCreateSession = canCreateSession;
    }

    public short getCanCreateTask() {
        return canCreateTask;
    }

    public void setCanCreateTask(short canCreateTask) {
        this.canCreateTask = canCreateTask;
    }

    public short getCanCreateBook() {
        return canCreateBook;
    }

    public void setCanCreateBook(short canCreateBook) {
        this.canCreateBook = canCreateBook;
    }
    
    //to string

    @Override
    public String toString() {
        return "Therapist{" + "speciality=" + speciality + ", canCreateSession=" + canCreateSession + ", canCreateTask=" + canCreateTask + ", canCreateBook=" + canCreateBook + '}'+"/n";
    }
    
}
