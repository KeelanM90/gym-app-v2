package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrainerTest {
    
    //No validation on speciality and other methods have been tested in PremiumMember
    
    Trainer trainer;
    
    @Before
    public void setUp() throws Exception {
        trainer = new Trainer("ned@flanders.com", "Ned Flanders", "Springfield", "m", "Skiing");
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void getSpeciality() throws Exception {
        assertEquals("Skiing", trainer.getSpeciality());
    }
    
    @Test
    public void setSpeciality() throws Exception {
        trainer.setSpeciality("Tennis");
        assertEquals("Tennis", trainer.getSpeciality());
    }
    
    @Test
    public void testToString() throws Exception {
        assertEquals("Email= ned@flanders.com, Name= Ned Flanders, Address= Springfield, Gender= M, Speciality= Skiing", trainer.toString());
    }
    
}