package coheal.entities.user;

import java.sql.Date;

/**
 *
 * @author wajdi's pc
 */
public class Therapist extends User{  
    
    //Therapist var
    private String speciality;
    private boolean canCreateSession;
    private boolean canCreateTask;
    private boolean canUploadBook;
    
    
    //getters & setters

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public boolean isCanCreateSession() {
        return canCreateSession;
    }

    public void setCanCreateSession(boolean canCreateSession) {
        this.canCreateSession = canCreateSession;
    }

    public boolean isCanCreateTask() {
        return canCreateTask;
    }

    public void setCanCreateTask(boolean canCreateTask) {
        this.canCreateTask = canCreateTask;
    }

    public boolean isCanUploadBook() {
        return canUploadBook;
    }

    public void setCanUploadBook(boolean canUploadBook) {
        this.canUploadBook = canUploadBook;
    }

    @Override
    public String toString() {
        return "Therapist{" + "speciality=" + speciality + ", canCreateSession=" + canCreateSession + ", canCreateTask=" + canCreateTask + ", canUploadBook=" + canUploadBook + '}';
    }
    
    
    
  
}
