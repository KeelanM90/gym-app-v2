package controllers;

import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by keela on 22/05/2017.
 */
public class GymApiTest {
    GymApi gym = new GymApi();
    private Member studentMember, premiumMember;
    private Trainer trainer;
   
    @Before
    public void setUp() throws Exception {
        premiumMember = new PremiumMember("apu@kwikemart.com", "Apu KwikEMa Nahasapeemapetilon", "Springfield", "m", 1.75, 120, "Package 2");
        studentMember = new StudentMember("lisa@simpson.com", "Lisa Simpson", "Springfield", "f", 1.55, 48, "WIT", "abc123", "WIT");
        
        trainer = new Trainer("ned@flanders.com", "Ned Flanders", "Springfield", "m", "Skiing");
    
        gym.addMember(premiumMember);
        gym.addMember(studentMember);
        
        gym.addTrainer(trainer);
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void addMember() throws Exception {
        gym.addMember(studentMember);
        assertEquals(gym.getMembers().get(2), studentMember);
    }
    
    @Test
    public void addTrainer() throws Exception {
        gym.addTrainer(trainer);
        assertEquals(gym.getTrainers().get(1), trainer);
    }
    
    @Test
    public void numberOfMembers() throws Exception {
        assertEquals(2, gym.numberOfMembers());
    }
    
    @Test
    public void numberOfTrainers() throws Exception {
        assertEquals(1, gym.numberOfTrainers());
    }
    
    @Test
    public void getMembers() throws Exception {
        assertEquals(2, gym.getMembers().size());
    }
    
    @Test
    public void getTrainers() throws Exception {
        assertEquals(1, gym.getTrainers().size());
    }
    
    @Test
    public void isValidMemberIndex() throws Exception {
        assertEquals(true, gym.isValidMemberIndex(0));
        assertEquals(false, gym.isValidMemberIndex(2));
    }
    
    @Test
    public void isValidTrainerIndex() throws Exception {
        assertEquals(true, gym.isValidTrainerIndex(0));
        assertEquals(false, gym.isValidTrainerIndex(2));
    }
    
    @Test
    public void searchMembersByEmail() throws Exception {
        assertEquals(premiumMember, gym.searchMembersByEmail("apu@kwikemart.com"));
    }
    
    @Test
    public void searchMembersByName() throws Exception {
        assertEquals(premiumMember.toString() + "\n", gym.searchMembersByName("Apu"));
    }
    
    @Test
    public void searchTrainersByEmail() throws Exception {
        assertEquals(trainer, gym.searchTrainersByEmail("ned@flanders.com"));
    }
    
    @Test
    public void listMembers() throws Exception {
        assertEquals(premiumMember.toString() + "\n" + studentMember.toString() + "\n", gym.listMembers());
        
        gym.members.clear();
        assertEquals("There are no members in the gym", gym.listMembers());
    }
    
    @Test
    public void listMembersWithIdealWeight() throws Exception {
        assertEquals(studentMember.toString() + "\n", gym.listMembersWithIdealWeight());
        
        gym.members.clear();
        assertEquals("There are no members in the gym\n", gym.listMembersWithIdealWeight());
    
        gym.members.add(premiumMember);
        assertEquals("No members in the gym with an ideal weight\n", gym.listMembersWithIdealWeight());
    
        
    }
    
    @Test
    public void listBySpecificBMICategory() throws Exception {
        assertEquals("There are no members in the gym in this BMI category\n", gym.listBySpecificBMICategory("UNDERWEIGHT"));
        assertEquals("There are no members in the gym in this BMI category\n", gym.listBySpecificBMICategory("underweight"));
    
        assertEquals(studentMember.toString() + "\n", gym.listBySpecificBMICategory("NORMAL"));
        assertEquals(studentMember.toString() + "\n", gym.listBySpecificBMICategory("normal"));
        
        gym.members.clear();
        assertEquals("There are no members in the gym\n", gym.listBySpecificBMICategory("NORMAL"));
        assertEquals("There are no members in the gym\n", gym.listBySpecificBMICategory("normal"));
    }
    
    @Test
    public void listMemberDetailsImperialAndMetric() throws Exception {
        assertEquals("Apu KwikEMa Nahasapeemapetilon     120.0 kg  (264.0lbs) 1.75metres (68.89 inches )\n" +
            "Lisa Simpson     48.0 kg  (105.6lbs) 1.55metres (61.02 inches )\n",
            gym.listMemberDetailsImperialAndMetric());
        
        gym.members.clear();
        assertEquals("There are no members in the gym\n", gym.listMemberDetailsImperialAndMetric());
    }
}