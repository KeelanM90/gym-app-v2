package models;

/**
 * The trainer class subclasses person and stores all relevant details for a trainer
 *
 * @author Keelan Murphy
 * @version 2017.05.21
 */
public class Trainer extends Person {
    
    String speciality;
    
    /**
     * Default constructor for a trainer
     *
     * @param email      the trainer's email
     * @param name       the trainer's name
     * @param address    the trainer's address
     * @param gender     the trainer's gender
     * @param speciality the trainer's speciality
     */
    public Trainer(String email, String name, String address, String gender, String speciality) {
        
        super(email, name, address, gender);
        this.speciality = speciality;
    }
    
    /**
     * Gets the trainers speciality
     *
     * @return the trainer's speciality
     */
    public String getSpeciality() {
        return speciality;
    }
    
    /**
     * Sets the speciality for the trainer
     *
     * @param speciality the trainer's speciality
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    
    /**
     * Returns a String representation of the trainer's details
     *
     * @return a String showing the trainer's details
     */
    @Override
    public String toString() {
        return super.toString() + ", Speciality= " + speciality;
    }
}
