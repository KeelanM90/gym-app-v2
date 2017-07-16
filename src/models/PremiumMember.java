package models;

/**
 * The premium member class models a member that signs up as a premium member and inherits details from the member superclass
 *
 * @author Keelan Murphy
 * @version 2017.05.21
 */
public class PremiumMember extends Member {
    
    /**
     * Default constructor for Premium member
     *
     * @param email          Premium member's email
     * @param name           Premium member's name
     * @param address        Premium member's address
     * @param gender         Premium member's gender
     * @param height         Premium member's height
     * @param startingWeight Premium member's starting weight
     * @param chosenPackage  Premium member's chosen package
     */
    public PremiumMember(
        String email,
        String name,
        String address,
        String gender,
        double height,
        double startingWeight,
        String chosenPackage) {
        
        super(email, name, address, gender, height, startingWeight, chosenPackage);
        
        chosenPackage(chosenPackage);
    }
    
    /**
     * Sets the chosen package for the premium member
     *
     * @param packageChoice
     */
    @Override
    public void chosenPackage(String packageChoice) {
        this.chosenPackage = packageChoice;
    }
}
