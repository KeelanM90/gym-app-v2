package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * The ScannerInput class is used as a middle ground between the console menu and
 * the Java Scanner in order to ensure correct input and to eradicate some of the
 * bugs associated with Java's Scanner class.
 *
 * @author Keelan Murphy
 * @version 2017.05.21
 */
public class ScannerInput {
    
    /**
     * Returns a String of text, forcing user input of any kind
     * @param prompt The prompt to display to the user
     * @return The string retrieved from the user
     */
    public static String retrieveText(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextLine();
    }
    
    /**
     * Retrieves text that is suitable for menu input the initial input is set
     * to lowercase and returned to where the method was called from
     * @param prompt The prompt to display to the user
     * @return A string suitable for menu comparison
     */
    public static String retrieveMenuText(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextLine().toLowerCase();
    }
    
    /**
     * Returns a valid Integer from the users input. The method will keep asking for input
     * until the user enters an integer value. Aside from validation the method prevents crashes
     * when a different variable type is entered.
     * @param prompt The prompt to display to the user
     * @return a valid integer
     */
    public static int validNextInt(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println(prompt);
                return input.nextInt();
            } catch (Exception e) {
                input.nextLine();//swallows the buffer contents
                System.err.println("\tEnter a number please ");
            }
        } while (true);
    }
    
    /**
     * Returns a valid double from the users input. The method will keep asking for input
     * until the user enters a double value. Aside from validation the method prevents crashes
     * when a different variable type is entered.
     * @param prompt
     * @return
     */
    public static double validNextDouble(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println(prompt);
                return Double.parseDouble(input.next());
            } catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        } while (true);
    }
    
    /**
     * http://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
     *
     * This method insists on a valid email address using regex
     *
     * @return a valid email address
     */
    public static String retrieveValidEmail(String prompt) {
        String email = retrieveText(prompt);
        
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        
        while (!m.matches()) {
            email = retrieveText("Invalid email format, please try again:");
            m = p.matcher(email);
        }
        
        return email;
    }
    
    /**
     * This method converts the users inputted date of type string to a java date, the
     * method will request the user to enter a date until the desired format is matched.
     * @return a date from the users input
     */
    public static Date getDate(String prompt) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = retrieveText(prompt);
        Date date = null;
        while (date == null) {
            try {
                date = formatter.parse(dateString);
            } catch (ParseException e) {
                dateString = retrieveText("Invalid date, please enter in the following format (dd-MM-yyyy)");
            }
        }
        return date;
    }
}




















