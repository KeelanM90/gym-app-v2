package models;

/**
 * The student member class models a member that signs up as a student member and inherits details from the member superclass
 *
 * @author Keelan Murphy
 * @version 2017.05.21
 */
public class StudentMember extends Member {
    private String studentId;
    String collegeName;
    
    /**
     * Default constructor for student member
     *
     * @param email          Student member's email
     * @param name           Student member's name
     * @param address        Student member's address
     * @param gender         Student member's gender
     * @param height         Student member's height
     * @param startingWeight Student member's starting weight
     * @param chosenPackage  Student member's chosen package
     * @param collegeName    Student member's college
     * @param studentId      Student member's id
     */
    public StudentMember(
        String email,
        String name,
        String address,
        String gender,
        double height,
        double startingWeight,
        String chosenPackage,
        String studentId,
        String collegeName) {
        
        super(email, name, address, gender, height, startingWeight, chosenPackage);
        
        this.studentId = studentId;
        
        this.collegeName = collegeName;
        
        chosenPackage(chosenPackage);
    }
    
    /**
     * Returns the student ID for the student member
     *
     * @return the students id
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * Sets the student member's id
     *
     * @param studentId the student id of the member
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    /**
     * Returns the student member's college name
     *
     * @return the student's college name
     */
    public String getCollegeName() {
        return collegeName;
    }
    
    /**
     * Set's the student member's college name
     *
     * @param collegeName the student's college name
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
    
    /**
     * Sets the Student member's package choice
     *
     * @param packageChoice the Student member's package choice
     */
    public void chosenPackage(String packageChoice) {
        this.chosenPackage = packageChoice;
    }
    
    /**
     * Returns a human readable string of the student member's details
     *
     * @return The student member's details as a string
     */
    @Override
    public String toString() {
        return super.toString() + " Student Id= " + studentId +
            ", College Name= " + collegeName;
    }
}
