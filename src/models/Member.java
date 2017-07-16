package models;

import java.util.Date;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A member is a subclass of type person and the superclass of Premium Member and Student Member.
 * This class deals with all of the commonalities between members of both Student and Premium type.
 *
 * @author Keelan Murphy
 * @version 2017.05.21
 */
public abstract class Member extends Person {
    
    private double height, startingWeight;
    public String chosenPackage;
    private HashMap<Date, Assessment> assessments = new HashMap<>();
    
    /**
     * Default constructor for a member
     *
     * @param email          email of the member
     * @param name           name of the member
     * @param address        address of the member
     * @param gender         gender of the member
     * @param height         height of the member
     * @param startingWeight starting weight of the member
     * @param chosenPackage  package chosen for the member
     */
    public Member(
        String email,
        String name,
        String address,
        String gender,
        double height,
        double startingWeight,
        String chosenPackage) {
        
        super(email, name, address, gender);
        
        setHeight(height);
        setStartingWeight(startingWeight);
    }
    
    /**
     * Method to return the height of a member
     *
     * @return the member's height
     */
    public double getHeight() {
        return height;
    }
    
    /**
     * Sets the height for a member
     *
     * @param height the height for the member
     */
    public void setHeight(double height) {
        this.height = height;
    }
    
    /**
     * Method to return the starting weight of a member
     *
     * @return the member's starting weight
     */
    public double getStartingWeight() {
        return startingWeight;
    }
    
    /**
     * Returns the starting weight for the member
     *
     * @param startingWeight the member's starting weight
     */
    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }
    
    /**
     * Method to return the assessments associated with a member
     *
     * @return the member's assessments
     */
    public HashMap<Date, Assessment> getAssessments() {
        return assessments;
    }
    
    /**
     * Sets the members assessments
     *
     * @param assessments The assessments to be set in the hashmap
     */
    public void setAssessments(HashMap<Date, Assessment> assessments) {
        this.assessments = assessments;
    }
    
    /**
     * Method to return the latest assessment for a member
     *
     * @return the member's latest assessment
     */
    public Assessment latestAssessment() {
        Assessment assessment = null;
        if (!assessments.isEmpty()) {
            assessment = assessments.get(sortedAssessmentDates().last());
        }
        return assessment;
    }
    
    /**
     * Method to a TreeSet of the date keys for the assessments in sorted order
     *
     * @return a treeset of sorted dates
     */
    public SortedSet<Date> sortedAssessmentDates() {
        return new TreeSet<>(assessments.keySet());
    }
    
    /**
     * An abstract void to be implemented in subclasses
     *
     * @param chosenPackage the members chosen package
     */
    public abstract void chosenPackage(String chosenPackage);
    
    /**
     * Get's the member's chosen package
     * @return the member's chosen package
     */
    public String getChosenPackage() {
        return chosenPackage;
    }
    
    /**
     * Method to return the hashmap of assessments
     *
     * @return hashmap of assessments
     */
    public HashMap<Date, Assessment> getHashMap() {
        return assessments;
    }
    
    /**
     * Returns a human readable string showing a members details
     *
     * @return the members details
     */
    @Override
    public String toString() {
        return super.toString() +
            ", Height= " + height +
            ", StartingWeight= " + startingWeight +
            ", ChosenPackage= " + chosenPackage;
    }
}
