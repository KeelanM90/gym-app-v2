package models;

/**
 * A person is a superclass which can either then be subclassed into Member or Trainer.
 * The person class stores all the commonalities between Members and Trainers.
 *
 * @author Keelan Murphy
 * @version 2017.05.21
 */
public abstract class Person {
    
    private String email, name, address, gender;
    
    /**
     * Default constructor for type Person
     *
     * @param email   The person's email
     * @param name    The person's name
     * @param address The person's address
     * @param gender  The person's gender
     */
    public Person(String email, String name, String address, String gender) {
        setEmail(email);
        setName(name);
        setAddress(address);
        setGender(gender);
    }
    
    /**
     * Method to return the email belonging to the person
     *
     * @return the person's email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the email of a person. The email is set to lowercase
     *
     * @param email The person's email
     */
    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
    
    /**
     * Method to return the name belonging to the person
     *
     * @return the person's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set's the name of a person. If the name is over 30 characters long, the name is shortened
     *
     * @param name the person's name
     */
    public void setName(String name) {
        if (name.length() > 30) {
            this.name = name.substring(0, 30);
        } else {
            this.name = name;
        }
    }
    
    /**
     * Method to return the address belonging to the person
     *
     * @return the person's address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Sets the address for the person
     *
     * @param address The persons address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Method to return the gender belonging to the person
     *
     * @return the person's gender
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * Sets the person's gender. The gender will be accepted as M or F, any other input will be set to unspecified
     *
     * @param gender The person's gender
     */
    public void setGender(String gender) {
        gender = gender.toUpperCase();
        if (gender.equals("M") || gender.equals("F")) {
            this.gender = gender;
        } else {
            this.gender = "Unspecified";
        }
    }
    
    /**
     * Returns a human readable string for the person's details
     *
     * @return the details of the person in String format
     */
    @Override
    public String toString() {
        return "Email= " + email + ", Name= " + name + ", Address= " + address + ", Gender= " + gender;
    }
}
