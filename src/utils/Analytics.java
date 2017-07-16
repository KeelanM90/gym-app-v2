package utils;

import models.Assessment;
import models.Member;

/**
 * The Analytics class is a collection of helper methods and calculation methods that can be accessed
 * from multiple classes.
 *
 * @author Keelan Murphy
 * @version 2017.05.21
 */
public class Analytics {
    
    /**
     * This method calculates the BMI for the member with a given assessment. The method allows for an
     * assessment with a value of null where no assessment was given, the members starting weight is used in
     * this circumstance.
     *
     * @param member     the member object to perform the calculation on
     * @param assessment the assessment to calculate the BMI with
     * @return the BMI value for the member. The number returned is truncated to two decimal places.
     */
    public static double calculateBMI(Member member, Assessment assessment) {
        double latestWeight = member.getStartingWeight();
        if (assessment != null) {
            latestWeight = assessment.getWeight();
        }
        return toTwoDecimalPlaces((latestWeight / member.getHeight()) / member.getHeight());
    }
    
    /**
     * This method determines the BMI category that the member belongs to.
     *
     * The category is determined by the magnitude of the members BMI according to the following:
     *
     *  BMI less than    15   (exclusive)                      is "VERY SEVERELY UNDERWEIGHT"
     *  BMI between      15   (inclusive) and 16   (exclusive) is "SEVERELY UNDERWEIGHT"
     *  BMI between      16   (inclusive) and 18.5 (exclusive) is "UNDERWEIGHT"
     *  BMI between      18.5 (inclusive) and 25   (exclusive) is "NORMAL"
     *  BMI between      25   (inclusive) and 30   (exclusive) is "OVERWEIGHT"
     *  BMI between      30   (inclusive) and 35   (exclusive) is "MODERATELY OBESE"
     *  BMI between      35   (inclusive) and 40   (exclusive) is "SEVERELY OBESE"
     *  BMI greater than 40   (inclusive)                      is "VERY SEVERELY OBESE"
     *
     *  @return the BMI category that the member belongs to.
     */
    public static String determineBMICategory(double bmiValue)
    {
        if (bmiValue < 15)
        {
            return "VERY SEVERELY UNDERWEIGHT";
        }
        else if (bmiValue >= 15 && bmiValue < 16)
        {
            return "SEVERELY UNDERWEIGHT";
        }
        else if (bmiValue >= 16 && bmiValue < 18.5)
        {
            return "UNDERWEIGHT";
        }
        else if (bmiValue >= 18.5 && bmiValue < 25)
        {
            return "NORMAL";
        }
        else if (bmiValue >= 25 && bmiValue < 30)
        {
            return "OVERWEIGHT";
        }
        else if (bmiValue >= 30 && bmiValue < 35)
        {
            return "MODERATELY OBESE";
        }
        else if (bmiValue >= 35 && bmiValue < 40)
        {
            return "SEVERELY OBESE";
        }
        else if (bmiValue >= 40)
        {
            return "VERY SEVERELY OBESE";
        }
        
        return "Error in BMI Calculation";
    }
    
    /**
     * This method returns a boolean to indicate if the member has an ideal body weight based on the Devine formula.
     * The method allows for an assessment with a value of null where no assessment was given, the members starting
     * weight is used in this circumstance.
     * For males, an ideal body weight is:     50 kg + 2.3 kg for each inch over 5 feet.
     * For females, an ideal body weight is:   45.5 kg + 2.3 kg for each inch over 5 feet.
     *
     * Note: if no gender is specified, return the result of the female calculation
     *
     * @param member     the member object to perform the calculation on
     * @param assessment the assessment to calculate the BMI with
     * @return Returns true if the result of the devine formula is within 2 kgs (inclusive) of the weight; false if it is outside this range.
     */
    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        double genderWeight = 0;
        
        double heightInInches = convertHeightMetresToInches(member.getHeight());
        if (heightInInches < 60) {
            heightInInches = 60;
        }
        
        if (member.getGender().equals("m")) {
            genderWeight = 50;
        } else {
            genderWeight = 45.5;
        }
        
        double idealBodyWeight = genderWeight + ((heightInInches - 60) * 2.3);
        
        double latestWeight = member.getStartingWeight();
        if (assessment != null) {
            latestWeight = assessment.getWeight();
        }
        
        return (latestWeight >= (idealBodyWeight - 2) && latestWeight <= (idealBodyWeight + 2));
    }
    
    /**
     * This method returns the member height converted from metres to inches.
     *
     * @return member height converted from metres to inches using the formula: metres multipled by 39.37. The number returned is truncated to two decimal places.
     */
    public static double convertHeightMetresToInches(double height) {
        double heightInInches = toTwoDecimalPlaces(height * 39.37);
        return heightInInches;
    }
    
    /**
     * This method returns the member weight converted from KGs to pounds.
     * @return member weight converted from KGs to pounds. The number returned is truncated to two decimal places.
     */
    public static double convertWeightKGtoPounds(Double weight)
    {
        double weightInPounds = toTwoDecimalPlaces(weight * 2.2);
        return weightInPounds;
    }
    
    /**
     * Returns the double sent as an argument truncated to two decimal places.
     *
     * @param num the number to be truncated to two decimal places
     * @return the input truncated to two decimal places
     */
    public static double toTwoDecimalPlaces(double num) {
        return (int) (num * 100) / 100.0;
    }
}
