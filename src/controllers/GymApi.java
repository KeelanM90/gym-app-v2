package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Member;
import models.Person;
import models.Trainer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static utils.Analytics.*;

/**
 * The GymApi handles the functions of the gym keeping track of all related data.
 *
 * @author Keelan Murphy
 * @version 2017.05.21
 */
public class GymApi {
    
    //Both ArrayLists declared as public for testing purposes
    public ArrayList<Member> members = new ArrayList<>();
    public ArrayList<Trainer> trainers = new ArrayList<>();
    
    /**
     * Method to add a member to the members arraylist
     *
     * @param member the member instance to add
     */
    public void addMember(Member member) {
        members.add(member);
    }
    
    /**
     * Method to add a trainer to the trainers arraylist
     *
     * @param trainer the trainer instance to add
     */
    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }
    
    /**
     * Method to return the number of Members in the members arraylist
     *
     * @return the number of members
     */
    public int numberOfMembers() {
        return members.size();
    }
    
    /**
     * Method to return the number of trainers in the trainers arraylist
     *
     * @return the number of trainers
     */
    public int numberOfTrainers() {
        return trainers.size();
    }
    
    /**
     * Method to return the members arraylist for external access
     *
     * @return the members arraylist
     */
    public ArrayList<Member> getMembers() {
        return members;
    }
    
    /**
     * Method to return the trainers arraylist for external access
     *
     * @return the trainers arraylist
     */
    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }
    
    /**
     * Returns a boolean representing whether the inputted index is valid or out of bounds
     *
     * @param index the members index in the arraylist
     * @return if the index is valid for the arraylist
     */
    public boolean isValidMemberIndex(int index) {
        try {
            members.get(index);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a boolean representing whether the inputted index is valid or out of bounds
     *
     * @param index the trainers index in the arraylist
     * @return if the index is valid for the arraylist
     */
    public boolean isValidTrainerIndex(int index) {
        try {
            trainers.get(index);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
    
    /**
     * Finds a member by searching the array for their email address which is fed in by an
     * argument. If the email is not found a null object is returned.
     *
     * @param emailEntered the members email
     * @return the member instance
     */
    public Member searchMembersByEmail(String emailEntered) {
        for (Member member : members) {
            if (member.getEmail().equals(emailEntered.toLowerCase().trim())) {
                return member;
            }
        }
        return null;
    }
    
    /**
     * Finds all members where their name partially matches the input
     *
     * @param nameEntered the name to search for
     * @return the tostring() methods of all matching members
     */
    public String searchMembersByName(String nameEntered) {
        if (members.size() == 0) {
            return "There are no members in the gym";
        } else {
            String matchingMembers = "";
            for (Member member : members) {
                if (member.getName().toLowerCase().contains(nameEntered.toLowerCase().trim())) {
                    matchingMembers += member.toString() + "\n";
                }
            }
            if (matchingMembers.length() == 0) {
                matchingMembers = "No matching members found";
            }
            return matchingMembers;
        }
    }
    
    /**
     * Finds a person by searching the trainer array for their email address which is fed in by an
     * argument. If the email is not found a null object is returned.
     *
     * @param emailEntered the persons email
     * @return the member instance
     */
    public Person searchTrainersByEmail(String emailEntered) {
        for (Trainer trainer : trainers) {
            if (trainer.getEmail().equals(emailEntered.toLowerCase().trim())) {
                return trainer;
            }
        }
        return null;
    }
    
    /**
     * Returns all members in the gym in string format, if there are no members, a string acknowledging
     * that is returned.
     *
     * @return all member's toString() Strings
     */
    public String listMembers() {
        String membersString = "";
        if (numberOfMembers() == 0) {
            membersString = "There are no members in the gym";
        } else {
            for (Member member : members) {
                membersString += member.toString() + "\n";
            }
        }
        return membersString;
    }
    
    /**
     * List all the members that have an ideal starting weight.
     *
     * @return The list of members (i.e. use the toString method here) that have an ideal starting weight based on the devine method.
     * <p>
     * If there are no members with an ideal starting weight, the message
     * "No members in the gym with an ideal weight" should be returned.
     * <p>
     * If there are no members in the gym, the message
     * "There are no members in the gym" should be returned
     */
    
    public String listMembersWithIdealWeight() {
        String membersString = "";
        if (numberOfMembers() == 0) {
            membersString = "There are no members in the gym\n";
        } else {
            for (Member member : members) {
                if (isIdealBodyWeight(member, member.latestAssessment())) {
                    membersString += member.toString() + "\n";
                }
            }
            if (membersString.length() == 0) {
                membersString = "No members in the gym with an ideal weight\n";
            }
        }
        return membersString;
    }
    
    /**
     * List all the members of a specific BMI category.
     *
     * @param category The category you wish to search members by.
     *                 <p>
     *                 The specific categories are:
     *                 "VERY SEVERELY UNDERWEIGHT"
     *                 "SEVERELY UNDERWEIGHT"
     *                 "UNDERWEIGHT"
     *                 "NORMAL"
     *                 "OVERWEIGHT"
     *                 "MODERATELY OBESE"
     *                 "SEVERELY OBESE"
     *                 "VERY SEVERELY OBESE"
     *                 <p>
     *                 <p>
     *                 This method also allows you to search by key words e.g. "OBESE" will return members in the following categories:
     *                 "MODERATELY OBESE"
     *                 "SEVERELY OBESE"
     *                 "VERY SEVERELY OBESE"
     *                 Note: In this situation, the members are NOT sorted by category, they are just displayed as is.
     * @return The list of members whose BMI falls into the category passed as a parameter
     * <p>
     * If there are no members in the BMI category, the message
     * "There are no members in the gym in this BMI category" should be returned.
     * <p>
     * If there are no members in the gym, the message
     * "There are no members in the gym" should be returned.
     */
    public String listBySpecificBMICategory(String category) {
        if (members.size() > 0) {
            String specificBMIList = "";
            for (Member member : members) {
                if (determineBMICategory(calculateBMI(member, member.latestAssessment())).contains(category.toUpperCase())) {
                    specificBMIList = specificBMIList + member.toString() + "\n";
                }
            }
            if (specificBMIList.length() > 0) {
                return specificBMIList;
            } else {
                return "There are no members in the gym in this BMI category\n";
            }
        } else {
            return "There are no members in the gym\n";
        }
    }
    
    /**
     * List all the members' weight and height both imperically and metrically.
     *
     * @return Each member in the gym with the weight and height listed both imperically and metrically.
     * <p>
     * The format of the output is like so:
     * <p>
     * Joe Soap:       xx kg (xxx lbs) x.x metres (xx inches)
     * Joan Soap:      xx kg (xxx lbs) x.x metres (xx inches)
     * <p>
     * If there are no members in the gym, the message
     * "There are no members in the gym" should be returned
     */
    public String listMemberDetailsImperialAndMetric() {
        if (members.size() > 0) {
            String memberDetails = "";
            for (Member member : members) {
                double weight = member.getStartingWeight();
                if (member.latestAssessment() != null) {
                    weight = member.latestAssessment().getWeight();
                }
                memberDetails = memberDetails + member.getName() + "     "
                    + member.getStartingWeight() + " kg  (" + convertWeightKGtoPounds(weight) + "lbs) "
                    + member.getHeight() + "metres (" + convertHeightMetresToInches(member.getHeight()) + " inches )\n";
            }
            return memberDetails;
        } else {
            return "There are no members in the gym\n";
        }
    }
    
    /**
     * Saves the current gym state to xml by writing out the members and trainers arraylists
     *
     * @throws Exception
     */
    public void store() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream
            (new FileWriter("gym.xml"));
        out.writeObject(members);
        out.writeObject(trainers);
        out.close();
    }
    
    /**
     * Loads the gym from the XML file
     *
     * @throws Exception
     */
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
            (new FileReader("gym.xml"));
        members = (ArrayList<Member>) is.readObject();
        trainers = (ArrayList<Trainer>) is.readObject();
        is.close();
    }
}
