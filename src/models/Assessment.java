package models;

/**
 * The Assessment class models an Assessment record, an assessment is a members details at a given moment in time
 *
 * @author Keelan Murphy
 * @version 2017.05.21
 */
public class Assessment {
    
    private double weight, chest, thigh, upperArm, waist, hips;
    private String comment;
    private Trainer trainer;
    
    /**
     * Default constructor for an assessment
     *
     * @param weight   the members weight at time of assessment
     * @param chest    the members chest measurement at time of assessment
     * @param thigh    the members thigh measurement at time of assessment
     * @param upperArm the members upper arm measurement at time of assessment
     * @param waist    the members waist measurement at time of assessment
     * @param hips     the members hip measurement at time of assessment
     * @param comment  the trainers comment on the assessment
     * @param trainer  the trainer that added the assessment
     */
    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips, String comment, Trainer trainer) {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.comment = comment;
        this.trainer = trainer;
    }
    
    /**
     * Method to return the weight from the assessment
     *
     * @return the members weight at time of assessment
     */
    public double getWeight() {
        return weight;
    }
    
    /**
     * Sets the weight for an assessment
     *
     * @param weight the members weight at time of assessment
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    /**
     * Method to return the members chest measurement from the assessment
     *
     * @return the members chest measurement at time of assessment
     */
    public double getChest() {
        return chest;
    }
    
    /**
     * Sets the chest measurement for an assessment
     *
     * @param chest the members chest measurement at time of assessment
     */
    public void setChest(double chest) {
        this.chest = chest;
    }
    
    /**
     * Method to return the members thigh measurement from the assessment
     *
     * @return the members thigh measurement at time of assessment
     */
    public double getThigh() {
        return thigh;
    }
    
    /**
     * Sets the thigh measurement for an assessment
     *
     * @param thigh the members thigh measurement at time of assessment
     */
    public void setThigh(double thigh) {
        this.thigh = thigh;
    }
    
    /**
     * Method to return the members upper arm measurement from the assessment
     *
     * @return the members upper arm measurement at time of assessment
     */
    public double getUpperArm() {
        return upperArm;
    }
    
    /**
     * Sets the upper arm measurement for an assessment
     *
     * @param upperArm the members upper arm measurement at time of assessment
     */
    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }
    
    /**
     * Method to return the members waist measurement from the assessment
     *
     * @return the members waist measurement at time of assessment
     */
    public double getWaist() {
        return waist;
    }
    
    /**
     * Sets the waist measurement for an assessment
     *
     * @param waist the members waist measurement at time of assessment
     */
    public void setWaist(double waist) {
        this.waist = waist;
    }
    
    /**
     * Method to return the members hip measurement from the assessment
     *
     * @return the members hip measurement at time of assessment
     */
    public double getHips() {
        return hips;
    }
    
    /**
     * Sets the hip measurement for an assessment
     *
     * @param hips the members hip measurement at time of assessment
     */
    public void setHips(double hips) {
        this.hips = hips;
    }
    
    /**
     * Method to return the trainers comment to the member
     *
     * @return the trainers comment to the member
     */
    public String getComment() {
        return comment;
    }
    
    /**
     * Sets the comment for an assessment
     *
     * @param comment the comment for the assessment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    /**
     * Method to return the trainer that added the assessment
     *
     * @return the trainer that added the assessment
     */
    public Trainer getTrainer() {
        return trainer;
    }
    
    /**
     * Sets the trainer that added an assessment
     *
     * @param trainer the trainer that added the assessment
     */
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    
    /**
     * Provides a human readable view of an assessment
     *
     * @return A string representation of the assessment
     */
    @Override
    public String toString() {
        return "Weight= " + weight +
            ", Chest= " + chest +
            ", Thigh= " + thigh +
            ", Upper Arm= " + upperArm +
            ", Waist= " + waist +
            ", Hips= " + hips +
            ", Comment= " + comment +
            ", Trainer= " + trainer.getName();
    }
}
