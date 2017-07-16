package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Created by keela on 22/05/2017.
 */
public class PremiumMemberTest {
    
    private PremiumMember premiumMember1, premiumMember2, premiumMember3;
    private Assessment assessment1, assessment2;
    private Trainer trainer1, trainer2;
    
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    @Before
    public void setUp() throws Exception {
        premiumMember1= new PremiumMember("HOMER@simpson.com", "Apu KwikEM Nahasapeemapetilon", "Springfield", "male", 1.83, 130, "Package 1");
        premiumMember2= new PremiumMember("apu@kwikemart.com", "Apu KwikEMa Nahasapeemapetilon", "Springfield", "m", 1.75, 120, "Package 2");
        premiumMember3= new PremiumMember("marge@simpson.com", "Apu KwikEMar Nahasapeemapetilon", "Springfield", "f", 1.64, 110, "Package 3");
        
        trainer1 = new Trainer("ned@flanders.com", "Ned Flanders", "Springfield", "M", "Skiing");
        trainer2 = new Trainer("rainier@wolfcastle.com", "Rainier Wolfcastle", "Shelbyville", "M", "Weights");
        
        assessment1 = new Assessment(16, 30, 20, 21, 28, 30, "", trainer1);
        assessment2 = new Assessment(17, 31, 21, 22, 29, 31, "Well done", trainer2);
        
        premiumMember1.getAssessments().put(formatter.parse("20-05-2017"), assessment2);
        premiumMember1.getAssessments().put(formatter.parse("05-05-2017"), assessment1);
        premiumMember3.getAssessments().put(formatter.parse("08-05-2017"), assessment1);
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testConstructor(){
        assertEquals("homer@simpson.com", premiumMember1.getEmail());
        assertEquals("Apu KwikEM Nahasapeemapetilon", premiumMember1.getName());
        assertEquals("Springfield", premiumMember1.getAddress());
        assertEquals("Unspecified", premiumMember1.getGender());
        assertEquals(1.83, premiumMember1.getHeight(), 0);
        assertEquals(130, premiumMember1.getStartingWeight(), 0);
        assertEquals("Package 1", premiumMember1.getChosenPackage());
        
        assertEquals("apu@kwikemart.com", premiumMember2.getEmail());
        assertEquals("Apu KwikEMa Nahasapeemapetilon", premiumMember2.getName());
        assertEquals("Springfield", premiumMember2.getAddress());
        assertEquals("M", premiumMember2.getGender());
        assertEquals(1.75, premiumMember2.getHeight(), 0);
        assertEquals(120, premiumMember2.getStartingWeight(), 0);
        assertEquals("Package 2", premiumMember2.getChosenPackage());
        
        assertEquals("marge@simpson.com", premiumMember3.getEmail());
        assertEquals("Apu KwikEMar Nahasapeemapetilo", premiumMember3.getName());
        assertEquals("Springfield", premiumMember3.getAddress());
        assertEquals("F", premiumMember3.getGender());
        assertEquals(1.64, premiumMember3.getHeight(), 0);
        assertEquals(110, premiumMember3.getStartingWeight(), 0);
        assertEquals("Package 3", premiumMember3.getChosenPackage());
    }
    
    @Test
    public void getEmail() throws Exception {
        assertEquals("homer@simpson.com", premiumMember1.getEmail());
        assertEquals("apu@kwikemart.com", premiumMember2.getEmail());
        assertEquals("marge@simpson.com", premiumMember3.getEmail());
    }
    
    @Test
    public void setEmail() throws Exception {
        premiumMember1.setEmail("123@test.com");
        premiumMember2.setEmail("lisa@SIMPSON.com");
        premiumMember3.setEmail("Test@Test.com");
        assertEquals("123@test.com", premiumMember1.getEmail());
        assertEquals("lisa@simpson.com", premiumMember2.getEmail());
        assertEquals("test@test.com", premiumMember3.getEmail());
    }
    
    @Test
    public void getName() throws Exception {
        assertEquals("Apu KwikEM Nahasapeemapetilon", premiumMember1.getName());
        assertEquals("Apu KwikEMa Nahasapeemapetilon", premiumMember2.getName());
        assertEquals("Apu KwikEMar Nahasapeemapetilo", premiumMember3.getName());
    }
    
    @Test
    public void setName() throws Exception {
        premiumMember1.setName("Lisa Simpson");
        premiumMember2.setName("Spiro Papadapaconstantikasgianopolopodopotopolis");
        premiumMember3.setName("bart");
        assertEquals("Lisa Simpson", premiumMember1.getName());
        assertEquals("Spiro Papadapaconstantikasgian", premiumMember2.getName());
        assertEquals("bart", premiumMember3.getName());
    }
    
    @Test
    public void getGender() throws Exception {
        assertEquals("Unspecified", premiumMember1.getGender());
        assertEquals("M", premiumMember2.getGender());
        assertEquals("F", premiumMember3.getGender());
    }
    
    @Test
    public void setGender() throws Exception {
        premiumMember1.setGender("f");
        premiumMember3.setGender("Female");
        premiumMember2.setGender("M");
        assertEquals("F", premiumMember1.getGender());
        assertEquals("M", premiumMember2.getGender());
        assertEquals("Unspecified", premiumMember3.getGender());
    }
    
    @Test
    public void latestAssessment() throws Exception {
        assertEquals(assessment2, premiumMember1.latestAssessment());
        assertEquals(null, premiumMember2.latestAssessment());
        assertEquals(assessment1, premiumMember3.latestAssessment());
    
    }
    
    
    @Test
    public void chosenPackage() throws Exception {
        assertEquals("Email= homer@simpson.com, Name= Apu KwikEM Nahasapeemapetilon, Address= Springfield," +
            " Gender= Unspecified, Height= 1.83, StartingWeight= 130.0, ChosenPackage= Package 1", premiumMember1.toString());
    
        assertEquals("Email= apu@kwikemart.com, Name= Apu KwikEMa Nahasapeemapetilon, Address= Springfield, " +
            "Gender= M, Height= 1.75, StartingWeight= 120.0, ChosenPackage= Package 2", premiumMember2.toString());
    
        assertEquals("Email= marge@simpson.com, Name= Apu KwikEMar Nahasapeemapetilo, Address= Springfield," +
            " Gender= F, Height= 1.64, StartingWeight= 110.0, ChosenPackage= Package 3", premiumMember3.toString());
    }
    
}