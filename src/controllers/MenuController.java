package controllers;

import models.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static utils.ScannerInput.*;

/**
 * This class is the main interface that a user will see providing the menu to interact with the
 * GymApi. The Menucontroller is a series of submenus which prompt the user for inputs.
 *
 * @author Keelan Murphy
 * @version 2017.05.21
 */
public class MenuController {
    
    GymApi gym = new GymApi();
    Person person = null;
    HashMap<String, String> packages = new HashMap<>();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    
    public static void main(String[] args) {
        new MenuController();
    }
    
    /**
     * Default constructor for MenuController. Populates the packages hashmap and initiates the load method and menu
     */
    public MenuController() {
        packages.put("Package 1", "Allowed access anytime to gym.\nFree access to all classes." +
            "\nAccess to all changing areas including deluxe changing rooms.");
        packages.put("Package 2", "Allowed access anytime to gym.\n€3 fee for all classes." +
            "\nAccess to all changing areas including deluxe changing rooms.");
        packages.put("Package 3", "Allowed access to gym at off-peak times.\n€5 fee for all " +
            "classes. \nNo access to deluxe changing rooms.");
        packages.put("WIT", "Allowed access to gym during term time.\n€4 fee for all classes." +
            "\nNo access to deluxe changing rooms.");
        try {
            gym.load();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        runMenu();
    }
    
    /**
     * The initial menu the user sees, asking if they want to login or register. In the case that they
     * enter the command to exit, the store method is called.
     */
    private void runMenu() {
        String option = retrieveMenuText("Welcome to the gym app, what do you wish to do:\n Register (r)\n Login (l)\n Exit (exit)");
        while (!option.equals("exit")) {
            switch (option) {
                case "r":
                    registrationMenu(findUserType());
                    break;
                case "l":
                    loginMenu(findUserType());
                    break;
                default:
                    insertLines();
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            option = retrieveMenuText("Welcome to the gym app, what do you wish to do:\n Register (r)\n Login (l)\n Exit (exit)");
        }
        try {
            gym.store();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        //the user chose option exit, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }
    
    /**
     * Finds the type of person the user wants to register/login as
     *
     * @return a one character long string representing the user type
     */
    private String findUserType() {
        String option = retrieveMenuText("Are you a Member (m) or a Trainer (t)").toLowerCase().trim();
        
        while (!option.equals("m") && !option.equals("t")) {
            option = retrieveMenuText("Invalid option please choose: Member (m) or a Trainer (t)").toLowerCase().trim();
        }
        return option;
    }
    
    /**
     * Menu responsible for the registration of members/trainers. This menu takes into account
     * most of the required validation on fields so that the user can be asked again if they
     * are giving invalid inputs
     *
     * @param userType the type of user
     */
    private void registrationMenu(String userType) {
        insertLines();
        
        String email = retrieveValidEmail("Enter Email:");
        while (gym.searchMembersByEmail(email) != null || gym.searchTrainersByEmail(email) != null) {
            email = retrieveValidEmail("Email already exists please try a different email address.");
        }
        
        String name = retrieveText("Enter name:");
        String address = retrieveText("Enter address:");
        String gender = retrieveText("Enter gender (M or F):");
        
        switch (userType) {
            case "t":
                String speciality = retrieveText("Enter speciality:");
                gym.addTrainer(new Trainer(email, name, address, gender, speciality));
                break;
            case "m":
                Double height = retrieveHeight();
                Double startingWeight = retrieveWeight();
                
                String memberType = retrieveMenuText("Member type:  Premium (p)  Student (s)").toLowerCase();
                while (!memberType.equals("s") && !memberType.equals("p")) {
                    memberType = retrieveMenuText("Please select member type:  Premium (p)  Student (s)").toLowerCase();
                }
                switch (memberType) {
                    case "p":
                        gym.addMember(
                            new PremiumMember(email, name, address, gender, height, startingWeight, retrieveChosenPackage())
                        );
                        break;
                    case "s":
                        String collegeName = retrieveText("Enter college name:");
                        String studentId = retrieveText("Enter student ID:");
                        String chosenPackage = "Unspecified";
                        if (packages.containsKey(collegeName)) {
                            chosenPackage = collegeName;
                        }
                        gym.addMember(
                            new StudentMember(email, name, address, gender, height, startingWeight, chosenPackage, studentId, collegeName)
                        );
                        break;
                }
        }
        insertLines();
    }
    
    /**
     * The loginMenu method is responsible for logging a person in to their respective menu.
     * If an inputted email is not found in the correct arraylist then the app exits
     *
     * @param userType the type of user
     */
    private void loginMenu(String userType) {
        insertLines();
        String email = retrieveText("Enter Email:");
        switch (userType) {
            case "m":
                Member member = gym.searchMembersByEmail(email);
                if (member == null) {
                    System.out.println("ACCESS DENIED");
                    System.exit(0);
                }
                person = member;
                memberMenu(member);
                break;
            case "t":
                Person trainer = gym.searchTrainersByEmail(email);
                if (trainer == null) {
                    System.out.println("ACCESS DENIED");
                    System.exit(0);
                }
                person = trainer;
                trainerMenu(trainer);
                break;
        }
    }
    
    /**
     * The trainer menu provides the functions that a trainer can use, it also can call upon it's sub-menus
     *
     * @param person The logged in trainer
     */
    private void trainerMenu(Person person) {
        insertLines();
        String trainerMenu = "1) Add a new member\n" +
            "2) List all members\n" +
            "3) Search for a member by email\n" +
            "4) Search for a member by name\n" +
            "5) List members with ideal body weight\n" +
            "6) List members with a specific BMI category\n" +
            "7) Assessment sub-menu\n" +
            "8) Reports sub-menu\n" +
            "0) Return to main menu";
        int option = validNextInt(trainerMenu);
        
        while (option != 0) {
            switch (option) {
                case 1:
                    registrationMenu("m");
                    break;
                case 2:
                    System.out.print(gym.listMembers());
                    break;
                case 3:
                    String email = retrieveText("Enter email to search:");
                    Member member = gym.searchMembersByEmail(email);
                    if (member == null) {
                        System.out.println("Member does not exist");
                    } else {
                        System.out.println(member.toString());
                    }
                    break;
                case 4:
                    String name = retrieveText("Enter name to search:");
                    System.out.print(gym.searchMembersByName(name));
                    break;
                case 5:
                    System.out.print(gym.listMembersWithIdealWeight());
                    break;
                case 6:
                    System.out.print(gym.listBySpecificBMICategory(retrieveText("BMI Category:")));
                    break;
                case 7:
                    assessmentSubMenu(person);
                    break;
                case 8:
                    reportsMenu();
                    break;
                default:
                    System.out.println("Invalid option entered: " + option + "\n");
                    break;
            }
            option = validNextInt(trainerMenu);
        }
    }
    
    /**
     * The reportsMenu provides the ability for a trainer to check the progress of any member
     */
    private void reportsMenu() {
        insertLines();
        String reportsMenu = "1) View member progress by email\n" +
            "2) View member progress by name\n" +
            "3) View all members progress\n" +
            "0) Return to previous menu";
        int option = validNextInt(reportsMenu);
        
        while (option != 0) {
            switch (option) {
                case 1:
                    String email = retrieveText("Enter email to search:");
                    Member member = gym.searchMembersByEmail(email);
                    if (member == null) {
                        System.out.println("Member does not exist");
                    } else {
                        progressMenu(member);
                    }
                    break;
                case 2:
                    String name = retrieveText("Enter name of member:");
                    for (int i = 0; i < gym.getMembers().size(); i++) {
                        if (gym.getMembers().get(i).getName().toLowerCase().contains(name.toLowerCase().trim())) {
                            System.out.println(i + ": " + gym.getMembers().get(i).getName());
                        }
                    }
                    int index = validNextInt("Enter members index:");
                    if (gym.isValidMemberIndex(index)) {
                        progressMenu(gym.getMembers().get(index));
                    }
                    break;
                case 3:
                    for (Member memb : gym.getMembers()) {
                        if (memb.getAssessments().isEmpty()) {
                            System.out.println("Cannot show progress as there are no assessments!");
                        }
                        System.out.println(memb.getName() + " weight progress:");
                        getWeightProgress(memb);
                    }
                    break;
                default:
                    System.out.println("Invalid option entered: " + option + "\n");
                    break;
            }
            option = validNextInt(reportsMenu);
        }
    }
    
    /**
     * The assessmentSubMenu provides the trainer with the ability to add an assessment or update
     * the comment on an assessment.
     *
     * @param person the person superclass of the trainer
     */
    private void assessmentSubMenu(Person person) {
        insertLines();
        String trainerMenu = "1) Add an assessment for a member\n" +
            "2) Update comment on an assessment for a member\n" +
            "0) Return to previous menu";
        int option = validNextInt(trainerMenu);
        
        while (option != 0) {
            switch (option) {
                case 1:
                    for (int i = 0; i < gym.getMembers().size(); i++) {
                        System.out.println(i + ") " + gym.getMembers().get(i).getName());
                    }
                    int index = validNextInt("Enter the index of the member to add assessment: ");
                    while (!gym.isValidMemberIndex(index)) {
                        index = validNextInt("Not a valid index please try again: ");
                    }
                    
                    double weight = validNextDouble("Weight: ");
                    double chest = validNextDouble("Chest: ");
                    double thigh = validNextDouble("Thigh: ");
                    double upperArm = validNextDouble("Arm: ");
                    double waist = validNextDouble("Waist: ");
                    double hips = validNextDouble("Hips: ");
                    String comment = retrieveText("Comment: ");
                    
                    gym.getMembers().get(index).getAssessments().put(getDate("Enter a date (dd-MM-yyyy)"), new Assessment(weight, chest,
                        thigh, upperArm, waist, hips, comment, (Trainer) person));
                    break;
                case 2:
                    String email = retrieveValidEmail("Enter member's email:");
                    Member member = gym.searchMembersByEmail(email);
                    if (member.sortedAssessmentDates().isEmpty()) {
                        System.out.println("No assessments found for " + member.getName());
                    } else {
                        for (Date date : member.sortedAssessmentDates()) {
                            System.out.println(formatter.format(date) + " : " + member.getAssessments().get(date));
                        }
                        member.getAssessments().get(getDate("Enter date of assessment:")).setComment(retrieveText("Enter new comment:"));
                    }
                    break;
                default:
                    System.out.println("Invalid option entered: " + option + "\n");
                    break;
            }
            option = validNextInt(trainerMenu);
        }
    }
    
    /**
     * Provides the member with the menu to view update their profile
     *
     * @param member the logged in member
     */
    private void memberMenu(Member member) {
        insertLines();
        String memberMenu = "1) View profile\n" +
            "2) Update profile\n" +
            "3) Progress sub-menu\n" +
            "0) Return to main menu";
        int option = validNextInt(memberMenu);
        while (option != 0) {
            switch (option) {
                case 1:
                    System.out.println(member.toString());
                    break;
                case 2:
                    updateProfile(member);
                    break;
                case 3:
                    progressMenu(member);
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            option = validNextInt(memberMenu);
        }
    }
    
    /**
     * Provides a menu to view a members progress based on a number of fields from the assessment
     *
     * @param member the member to view the progress for
     */
    private void progressMenu(Member member) {
        insertLines();
        if (member.latestAssessment() == null) {
            System.out.println("This member has no assessments so progress cannot be established");
        } else {
            member.latestAssessment();
            
            String memberMenu = "1) View progress by weight\n" +
                "2) View progress by chest measurement\n" +
                "3) View progress by thigh measurement\n" +
                "4) View progress by upper arm measurement\n" +
                "5) View progress by waist measurement\n" +
                "6) View progress by hips measurement\n" +
                "0) Return to previous menu";
            int option = validNextInt(memberMenu);
            while (option != 0) {
                switch (option) {
                    case 1:
                        getWeightProgress(member);
                        break;
                    case 2:
                        getChestProgress(member);
                        break;
                    case 3:
                        getThighProgress(member);
                        break;
                    case 4:
                        getUpperArmProgress(member);
                        break;
                    case 5:
                        getWaistProgress(member);
                        break;
                    case 6:
                        getHipsProgress(member);
                        break;
                    default:
                        System.out.println("Invalid option entered: " + option);
                        break;
                }
                option = validNextInt(memberMenu);
            }
        }
    }
    
    /**
     * Provides the facility for a members profile to be updated
     *
     * @param member the member to be edited
     */
    private void updateProfile(Member member) {
        String updateProfileMenu = "1) Update email\n" +
            "2) Update name\n" +
            "3) Update address\n" +
            "4) Update gender\n" +
            "5) Update height\n" +
            "6) Update starting weight\n";
        if (member instanceof StudentMember) {
            updateProfileMenu += "7) Update college name\n" +
                "8) Update student id\n" +
                "9) Update package\n";
        } else {
            updateProfileMenu += "7) Update package\n";
        }
        updateProfileMenu += "0) Back to previous menu";
        
        int option = validNextInt(updateProfileMenu);
        while (option != 0) {
            switch (option) {
                case 1:
                    System.out.print("Previously entered email: \n" + member.getEmail() + "\n");
                    String email = retrieveValidEmail("Enter new email:");
                    while (gym.searchMembersByEmail(email) != null || gym.searchTrainersByEmail(email) != null) {
                        email = retrieveValidEmail("Email already exists please try a different email address.");
                    }
                    member.setEmail(email);
                    break;
                case 2:
                    System.out.print("Previously entered name: \n" + member.getName() + "\n");
                    member.setName(retrieveText("Enter new name:"));
                    break;
                case 3:
                    System.out.print("Previously entered address: \n" + member.getAddress() + "\n");
                    member.setAddress(retrieveText("Enter new address:"));
                    break;
                case 4:
                    System.out.print("Previously entered gender: \n" + member.getGender() + "\n");
                    member.setGender(retrieveText("Enter new gender (M or F) :"));
                    break;
                case 5:
                    System.out.print("Previously entered height: \n" + member.getHeight() + "\n");
                    member.setHeight(retrieveHeight());
                    break;
                case 6:
                    System.out.print("Previously entered starting weight: \n" + member.getStartingWeight() + "\n");
                    member.setStartingWeight(retrieveWeight());
                    break;
                case 7:
                    if (member instanceof StudentMember) {
                        System.out.print("Previously entered college: \n" + ((StudentMember) member).getCollegeName() + "\n");
                        ((StudentMember) member).setCollegeName(retrieveText("New college name:"));
                    } else {
                        System.out.print("Previously entered package: \n" + member.getChosenPackage() + "\n");
                        member.chosenPackage(retrieveChosenPackage());
                    }
                    break;
                case 8:
                    if (member instanceof StudentMember) {
                        System.out.print("Previously entered student id: \n" + ((StudentMember) member).getStudentId() + "\n");
                        ((StudentMember) member).setStudentId(retrieveText("New student id:"));
                    } else {
                        System.out.println("Invalid option entered: " + option + "\n");
                    }
                    break;
                case 9:
                    if (member instanceof StudentMember) {
                        System.out.print("Previously entered package: \n" + member.getChosenPackage() + "\n");
                        member.chosenPackage(retrieveChosenPackage());
                    } else {
                        System.out.println("Invalid option entered: " + option + "\n");
                    }
                    break;
                default:
                    System.out.println("Invalid option entered: " + option + "\n");
                    break;
            }
            option = validNextInt(updateProfileMenu);
        }
    }
    
    /**
     * Inserts lines to improve readability
     */
    private void insertLines() {
        for (int clear = 0; clear < 5; clear++) {
            System.out.println("\n");
        }
    }
    
    /**
     * Method to retrieve a valid height (between 1 and 3 meters)
     * @return height of the member
     */
    private Double retrieveHeight() {
        Double height = validNextDouble("Enter height:");
        while (height < 1 || height > 3) {
            height = validNextDouble("Height must be between 1 and 3, please try again:");
        }
        return height;
    }
    
    /**
     * Method to retrieve a valid weight (between 35 and 250 kg)
     * @return the members weight
     */
    private Double retrieveWeight() {
        Double weight = validNextDouble("Enter weight:");
        while (weight < 35 || weight > 250) {
            weight = validNextDouble("Weight must be between 35 and 250, please try again:");
        }
        return weight;
    }
    
    /**
     * Method to retrieve the members chosen package, showing the corresponding packages to the keys
     * @return the chosen package
     */
    private String retrieveChosenPackage() {
        System.out.println("Please choose the package you wish to avail of:");
        for (Map.Entry<String, String> entry : packages.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        String chosenPackage = retrieveText("Please enter a package name:");
        while (packages.containsKey(chosenPackage)) {
            chosenPackage = retrieveText("Please enter a valid package name:");
        }
        return chosenPackage;
    }
    
    /**
     * Prints the progress a member makes with regards to gaining/losing weight
     * @param member the member to show progress for
     */
    private void getWeightProgress(Member member) {
        Date previousDate = null;
        for (Date date : member.sortedAssessmentDates()) {
            if (previousDate == null) {
                System.out.println("Date: " + formatter.format(date) + " Progress: " + (member.getAssessments().get(date).getWeight() - member.getStartingWeight()) + "kg");
            } else {
                System.out.println("Date: " + formatter.format(date) + " Progress: " + (member.getAssessments().get(date).getWeight() - member.getAssessments().get(previousDate).getWeight()) + "kg");
            }
            previousDate = date;
        }
    }
    
    /**
     * Prints the progress a member makes with regards to growing/shrinking their chest
     * @param member the member to show progress for
     */
    private void getChestProgress(Member member) {
        Date previousDate = null;
        for (Date date : member.sortedAssessmentDates()) {
            if (previousDate == null) {
            } else {
                System.out.println("Date: " + formatter.format(date) + " Progress: " + (member.getAssessments().get(date).getChest() - member.getAssessments().get(previousDate).getChest()) + "cm");
            }
            previousDate = date;
        }
    }
    
    /**
     * Prints the progress a member makes with regards to growing/shrinking their thighs
     * @param member the member to show progress for
     */
    private void getThighProgress(Member member) {
        Date previousDate = null;
        for (Date date : member.sortedAssessmentDates()) {
            if (previousDate == null) {
            } else {
                System.out.println("Date: " + formatter.format(date) + " Progress: " + (member.getAssessments().get(date).getThigh() - member.getAssessments().get(previousDate).getThigh()) + "cm");
            }
            previousDate = date;
        }
    }
    
    /**
     * Prints the progress a member makes with regards to growing/shrinking their upper arms
     * @param member the member to show progress for
     */
    private void getUpperArmProgress(Member member) {
        Date previousDate = null;
        for (Date date : member.sortedAssessmentDates()) {
            if (previousDate == null) {
            } else {
                System.out.println("Date: " + formatter.format(date) + " Progress: " + (member.getAssessments().get(date).getUpperArm() - member.getAssessments().get(previousDate).getUpperArm()) + "cm");
            }
            previousDate = date;
        }
    }
    
    /**
     * Prints the progress a member makes with regards to growing/shrinking their waist
     * @param member the member to show progress for
     */
    private void getWaistProgress(Member member) {
        Date previousDate = null;
        for (Date date : member.sortedAssessmentDates()) {
            if (previousDate == null) {
            } else {
                System.out.println("Date: " + formatter.format(date) + " Progress: " + (member.getAssessments().get(date).getWaist() - member.getAssessments().get(previousDate).getWaist()) + "cm");
            }
            previousDate = date;
        }
    }
    
    /**
     * Prints the progress a member makes with regards to growing/shrinking their hips
     * @param member the member to show progress for
     */
    private void getHipsProgress(Member member) {
        Date previousDate = null;
        for (Date date : member.sortedAssessmentDates()) {
            if (previousDate == null) {
            } else {
                System.out.println("Date: " + formatter.format(date) + " Progress: " + (member.getAssessments().get(date).getHips() - member.getAssessments().get(previousDate).getHips()) + "cm");
            }
            previousDate = date;
        }
    }
}