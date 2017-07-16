package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by keelan on 22/05/17.
 */
public class AssessmentTest {
    
    private Assessment assessment1, assessment2;
    private Trainer trainer1, trainer2;
    
    @Before
    public void setUp() throws Exception {
        trainer1 = new Trainer("ned@flanders.com", "Ned Flanders", "Springfield", "M", "Skiing");
        trainer2 = new Trainer("rainier@wolfcastle.com", "Rainier Wolfcastle", "Shelbyville", "M", "Weights");
    
        assessment1 = new Assessment(16, 30, 20, 21, 28, 30, "", trainer1);
        assessment2 = new Assessment(17, 31, 21, 22, 29, 31, "Well done", trainer2);
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void getWeight() throws Exception {
        assertEquals(16, assessment1.getWeight(), 0);
        assertEquals(17, assessment2.getWeight(), 0);
    }
    
    @Test
    public void setWeight() throws Exception {
        assessment1.setWeight(18);
        assessment2.setWeight(15);
        assertEquals(18, assessment1.getWeight(), 0);
        assertEquals(15, assessment2.getWeight(), 0);
    }
    
    @Test
    public void getChest() throws Exception {
        assertEquals(30, assessment1.getChest(), 0);
        assertEquals(31, assessment2.getChest(), 0);
    }
    
    @Test
    public void setChest() throws Exception {
        assessment1.setChest(18);
        assessment2.setChest(15);
        assertEquals(18, assessment1.getChest(), 0);
        assertEquals(15, assessment2.getChest(), 0);
    }
    
    @Test
    public void getThigh() throws Exception {
        assertEquals(20, assessment1.getThigh(), 0);
        assertEquals(21, assessment2.getThigh(), 0);
    }
    
    @Test
    public void setThigh() throws Exception {
        assessment1.setThigh(18);
        assessment2.setThigh(15);
        assertEquals(18, assessment1.getThigh(), 0);
        assertEquals(15, assessment2.getThigh(), 0);
    }
    
    @Test
    public void getUpperArm() throws Exception {
        assertEquals(21, assessment1.getUpperArm(), 0);
        assertEquals(22, assessment2.getUpperArm(), 0);
    }
    
    @Test
    public void setUpperArm() throws Exception {
        assessment1.setUpperArm(18);
        assessment2.setUpperArm(15);
        assertEquals(18, assessment1.getUpperArm(), 0);
        assertEquals(15, assessment2.getUpperArm(), 0);
    }
    
    @Test
    public void getWaist() throws Exception {
        assertEquals(28, assessment1.getWaist(), 0);
        assertEquals(29, assessment2.getWaist(), 0);
    }
    
    @Test
    public void setWaist() throws Exception {
        assessment1.setWaist(18);
        assessment2.setWaist(15);
        assertEquals(18, assessment1.getWaist(), 0);
        assertEquals(15, assessment2.getWaist(), 0);
    }
    
    @Test
    public void getHips() throws Exception {
        assertEquals(30, assessment1.getHips(), 0);
        assertEquals(31, assessment2.getHips(), 0);
    }
    
    @Test
    public void setHips() throws Exception {
        assessment1.setHips(18);
        assessment2.setHips(15);
        assertEquals(18, assessment1.getHips(), 0);
        assertEquals(15, assessment2.getHips(), 0);
    }
    
    @Test
    public void getComment() throws Exception {
        assertEquals("", assessment1.getComment());
        assertEquals("Well done", assessment2.getComment());
    }
    
    @Test
    public void setComment() throws Exception {
        assessment1.setComment("abcde");
        assessment2.setComment("comment");
        assertEquals("abcde", assessment1.getComment());
        assertEquals("comment", assessment2.getComment());
    }
    
    @Test
    public void getTrainer() throws Exception {
        assertEquals(trainer1, assessment1.getTrainer());
        assertEquals(trainer2, assessment2.getTrainer());
    }
    
    @Test
    public void setTrainer() throws Exception {
        assessment1.setTrainer(trainer2);
        assessment2.setTrainer(trainer1);
        assertEquals(trainer2, assessment1.getTrainer());
        assertEquals(trainer1, assessment2.getTrainer());
    }
    
    @Test
    public void testToString() throws Exception {
        assertEquals("Weight= 16.0, Chest= 30.0, Thigh= 20.0, Upper Arm= 21.0, Waist= 28.0, Hips= 30.0, Comment= , Trainer= Ned Flanders", assessment1.toString());
        assertEquals("Weight= 17.0, Chest= 31.0, Thigh= 21.0, Upper Arm= 22.0, Waist= 29.0, Hips= 31.0, Comment= Well done, Trainer= Rainier Wolfcastle", assessment2.toString());
    }
}