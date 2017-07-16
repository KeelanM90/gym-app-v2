package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by keela on 22/05/2017.
 */
public class StudentMemberTest {
    //Common code already covered in Premium Member
    
    StudentMember studentMember1;
    
    @Before
    public void setUp() throws Exception {
        studentMember1 = new StudentMember("lisa@simpson.com", "Lisa Simpson", "Springfield", "f", 2, 24, "WIT", "abc123", "WIT");
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void getStudentId() throws Exception {
        assertEquals("abc123", studentMember1.getStudentId());
    }
    
    @Test
    public void setStudentId() throws Exception {
        studentMember1.setStudentId("xyz987");
        assertEquals("xyz987", studentMember1.getStudentId());
        
    }
    
    @Test
    public void getCollegeName() throws Exception {
        assertEquals("WIT", studentMember1.getCollegeName());
    }
    
    @Test
    public void setCollegeName() throws Exception {
        studentMember1.setCollegeName("CIT");
        assertEquals("CIT", studentMember1.getCollegeName());
    }
    
    @Test
    public void getChosenPackage() throws Exception {
        assertEquals("WIT", studentMember1.getChosenPackage());
    }
    
    @Test
    public void testToString() throws Exception {
        assertEquals("Email= lisa@simpson.com, Name= Lisa Simpson, Address= Springfield, Gender= F, Height= 2.0, StartingWeight= 24.0, ChosenPackage= WIT Student Id= abc123, College Name= WIT",studentMember1.toString());
    }
}